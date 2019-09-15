package com.ztesoft.zsmart.nros.crm.core.start.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.zmq.entity.NrosMQMessage;
import com.ztesoft.zsmart.nros.base.zmq.producer.DefaultZMQProducer;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventMqValueDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingEventTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 事件触发
 * 
 * @author PQ
 * @date 2019/4/17
 */
@SessionController
@RequestMapping("/marketing/event")
@Api(value = "事件触发", tags = {
    "事件触发"
})
@Slf4j
public class EventTriggerController {

    @Value("${zmq.produce.event.topic}")
    private String topic;

    @Autowired
    private DefaultZMQProducer defaultZMQProducer;

    @Autowired
    @Setter
    private RuntimeService runtimeService;

    @Autowired
    @Setter
    private RepositoryService repositoryService;

    @PostMapping(value = "/register-trigger")
    @ApiOperation("注册事件触发")
    public ResponseMsg registerTrigger(@RequestBody EventTriggerParam eventTriggerParam) {
        Long memberId = eventTriggerParam.getMemberId();
        if (null == memberId) {
            return new ResponseMsg().fail("memberId is null");
        }
        log.info("注册事件触发...memberId={}", memberId);
        try {
            List<EventMqValueDTO> attributeList = Lists.newArrayList();
            EventMqValueDTO innerEventMqValueDTO1 = new EventMqValueDTO();
            innerEventMqValueDTO1.setFieldCode("registerChannel");
            innerEventMqValueDTO1.setFieldValue("0");

            EventMqValueDTO innerEventMqValueDTO2 = new EventMqValueDTO();
            innerEventMqValueDTO2.setFieldCode("triggerChannel");
            innerEventMqValueDTO2.setFieldValue("25");

            attributeList.add(innerEventMqValueDTO1);
            attributeList.add(innerEventMqValueDTO2);

            // 推送数据到MQ
            JSONObject msgBody = new JSONObject();
            msgBody.put("attributeList", attributeList);
            msgBody.put("eventCode", MarketingEventTypeEnum.REGISTER.getEventCode());
            msgBody.put("memberId", memberId);
            msgBody.put("gmtCreate", LocalDateTime.now().withNano(0).toString().replace("T", " "));

            NrosMQMessage msg = NrosMQMessage.buildNrosMQMessage(msgBody, topic);
//            defaultZMQProducer.sendMessage(defaultZMQProducer.getProducer(), msg);
        }
        catch (Exception e) {
            return new ResponseMsg().fail(e.getMessage());
        }
        return new ResponseMsg().success();
    }

    @GetMapping(value = "/start-activiti")
    @ApiOperation("测试启动营销流程")
    public ResponseMsg startActiviti(@RequestParam Long marketingDefineId) {
        Map<String, Object> variableMap = Maps.newHashMap();
        // variableMap.put("msgContext", eventTriggerHistoryParam);
        /*
         * MarketingDefineQuery marketingDefineQuery = new MarketingDefineQuery();
         * marketingDefineQuery.setId(marketingDefineId);
         */
        // MarketingDefineDTO marketingDefine = marketingService.detail(marketingDefineQuery);
        MarketingDefineDTO marketingDefine = new MarketingDefineDTO();
        marketingDefine.setMerchantCode("1");
        marketingDefine.setId(1L);
        marketingDefine.setMarketingType("1");
        if (marketingDefine != null) {
            List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(PromotionContants.ACTIVITI_PROCESS_PREFIX + marketingDefineId).latestVersion()
                .list();
            if (!CollectionUtils.isEmpty(processDefinitionList)) {
                variableMap.put("marketingDefineId", marketingDefine.getId());
                variableMap.put("marketingType", marketingDefine.getMarketingType());
                variableMap.put("merchantCode", marketingDefine.getMerchantCode());
                runtimeService.startProcessInstanceByKey(PromotionContants.ACTIVITI_PROCESS_PREFIX + marketingDefineId,
                    variableMap);
                log.info("###############startActiviti ####################");
                return new ResponseMsg().success();
            }
        }
        return new ResponseMsg().fail("当前营销定义ID不存在执行实例！");

    }
}
