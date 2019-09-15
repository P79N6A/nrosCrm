package com.ztesoft.zsmart.nros.crm.core.server.middleware.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.base.zmq.consumer.AbstractZMQHandler;
import com.ztesoft.zsmart.nros.base.zmq.entity.NrosMQMessage;
import com.ztesoft.zsmart.nros.crm.core.client.api.TraceEventDealService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventMqValueDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceListDTO;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.EventTraceConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingEventTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.domain.eventTrace.EventTraceDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 会员事件轨迹mq消费者
 *
 * @author litao
 * @date 2019/6/14
 */
@Component
public class EventTraceConsumer extends AbstractZMQHandler {

    protected static final Logger logger = LoggerFactory.getLogger(EventTraceConsumer.class);

    @Override
    public Action doBusinesses(NrosMQMessage nrosMQMessage, ConsumeContext consumeContext) {
        logger.info("start to comsumer EventTraceConsumer...");

        RedisTemplate redisTemplate = SpringContextUtils.getBean("redisTemplate");
        Message msg = nrosMQMessage.getMqMessage();
        String msgBody = new String(msg.getBody(), Charset.forName("UTF-8"));
        logger.info("received message,msgBody={}", msgBody);
        JSONObject msgObject = JSON.parseObject(msgBody);
        // 用于唯一标识的消息ID
        // String msgId = "crm:ConsumedNrosMsgId:" + ("E0009".equals(msgObject.getString("eventCode")) ?
        // msgObject.getString("menberId") : msgObject.getString("attributeList"));
        String msgId = "crm:ConsumedNrosMsgId:" + msgObject.getString("__nrosMsgIdInner__");

        if (null == msgObject.getString("attributeList")) {
            logger.warn("EventCampaignConsumer#doBusinesses MQ message attributeList is null, msgId=[{}]!", msgId);
            return Action.CommitMessage;
        }

        if (null != redisTemplate.opsForValue().get(msgId)) {
            logger.warn("EventCampaignConsumer#doBusinesses MQ message repeated, msgId=[{}]!", msgId);
        }
        else {
            // 设置redis锁，避免消息重复消费
            redisTemplate.opsForValue().set(msgId, 1, 1, TimeUnit.MINUTES);
            this.dealMq(msgObject);
        }
        return Action.CommitMessage;
    }

    private void dealMq(JSONObject msgObject) {
        try {
            String eventCode = msgObject.getString("eventCode");
            List<EventMqValueDTO> attributeList = JSONArray.parseArray(msgObject.getString("attributeList"),
                    EventMqValueDTO.class);
            EventTraceDomain eventTraceDomain = SpringContextUtils.getBean(EventTraceDomain.class);
            EventTraceListDTO eventTraceListDTO = eventTraceDomain.traceTemplateByEventCode(eventCode);
           /*if (null == eventTraceListDTO) {
                logger.error("EventTraceConsumer.dealMq  traceTemplateByEventCode[{}] is null!", eventCode);
                return;
            }*/
            if (null == msgObject.getString("memberId")) {
                logger.error("没有会员id");
            }
            eventTraceListDTO.setMemberId(Long.parseLong(msgObject.getString("memberId")));
            String content = eventTraceListDTO.getContent();
            eventTraceListDTO
                    .setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(msgObject.getString("gmtCreate")));

            for (EventMqValueDTO eventMqValueDTO : attributeList) {
                if (content.contains("${".concat(eventMqValueDTO.getFieldCode()).concat("}"))) {
                    content = content.replace("${".concat(eventMqValueDTO.getFieldCode()).concat("}"),
                            eventMqValueDTO.getFieldValue());
                }
            }
            eventTraceListDTO.setContent(content);
            eventTraceListDTO.setLang("zh");
            eventTraceDomain.insertEventTrace(EventTraceConvertor.INSTANCE.dtoToParam(eventTraceListDTO));

            // 目前订单新增的时候，意味着支付成功,不特殊处理，消费事件触发不了
            if ("E0010".equals(eventCode)) {
                eventTraceListDTO.setEventId(12L); // 订单支付
                eventTraceDomain.insertEventTrace(EventTraceConvertor.INSTANCE.dtoToParam(eventTraceListDTO));

                msgObject.put("eventCode", "E0012");
                TraceEventDealService eventDealService = SpringContextUtils.getBean("marketingEventTriggerService");
                eventDealService.doWork(msgObject.toJSONString());
            }


            // 若为满足条件的指定事件，则需触发事件营销流程执行
            if (MarketingEventTypeEnum.getByEventCode(eventCode) != null) {
                TraceEventDealService eventDealService = SpringContextUtils.getBean("marketingEventTriggerService");
                eventDealService.doWork(msgObject.toJSONString());
            }
        }
        catch (Exception e) {
            logger.error("EventTraceConsumer.dealMq  fail...msg={}!", e.getMessage());
        }
    }
}
