/**
 * [Product]
 * nrospromotion
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * MarketingEventTriggerServiceImpl.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019/6/17   PQ         最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ztesoft.zsmart.nros.base.util.DateUtil;
import org.activiti.engine.RuntimeService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Maps;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.crm.core.client.api.MarketingService;
import com.ztesoft.zsmart.nros.crm.core.client.api.TraceEventDealService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventMqValueDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingEventTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingProcessFinishStatusEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event.BaseEventNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.MemberService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;

import lombok.Setter;

/**
 * 事件营销触发服务处理实现类
 * 
 * @author PQ
 * @date 2019/6/17
 */
@Service(value = "marketingEventTriggerService")
@Setter
public class MarketingEventTriggerServiceImpl implements TraceEventDealService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MarketingEventTriggerServiceImpl.class);

    @Autowired
    private MarketingService marketingService;

    @Autowired
    private MemberService memberService;

    @Autowired
    RuntimeService runtimeService;

    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void doWork(String jsonParm) {
        JSONObject msgObject = JSON.parseObject(jsonParm);
        String merchantCode = msgObject.getString("merchantCode");
        String eventCode = msgObject.getString("eventCode");
        Long memberId = msgObject.getLong("memberId");
        String attributeStr = msgObject.getString("attributeList");
        if (eventCode == null || memberId == null || attributeStr == null) {
            logger.warn("MarketingEventTriggerServiceImpl.doWork eventCode or memberId or attributeList is null!");
            return;
        }

        // extInfo
        List<EventMqValueDTO> attributeList = JSONArray.parseArray(attributeStr, EventMqValueDTO.class);
        JSONObject extInfo = new JSONObject();
        for (EventMqValueDTO eventMqValueDTO : attributeList) {
            extInfo.put(eventMqValueDTO.getFieldCode(), eventMqValueDTO.getFieldValue());
        }

        // 根据会员ID查询详细信息
        MemberDetailDTO memberDetailDTO = memberService.getDetailById(memberId).getData();
        if (memberDetailDTO == null) {
            logger.warn("MarketingEventTriggerServiceImpl.doWork…… MemberId{} not exist!", memberId);
            return;
        }

        EventTriggerHistoryParam eventTriggerHistoryParam = generateEventTriggerParamFromJson(eventCode,
            memberDetailDTO, extInfo);

        // 获取满足触发事件条件的活动定义列表
        List<MarketingDefineDTO> lstFlowDefineList = getMatchDefine(merchantCode, eventCode, eventTriggerHistoryParam);
        if (CollectionUtils.isEmpty(lstFlowDefineList)) {
            // 记录事件触发历史表（营销流程定义字段留空）
            marketingService.saveEventTriggerHistory(eventTriggerHistoryParam);
        }
        else {
            RuntimeService runtimeService = SpringContextUtils.getBean(RuntimeService.class);
            // 遍历执行流程定义相关动作，并记录事件触发历史表
            lstFlowDefineList.forEach(defineDTO -> {
                EventTriggerHistoryParam innterEventTriggerHistoryParam = this
                    .generateEventTriggerParamFromJson(eventCode, memberDetailDTO, extInfo);
                innterEventTriggerHistoryParam.setMarketingId(defineDTO.getId());
                innterEventTriggerHistoryParam.setMerchantCode(defineDTO.getMerchantCode());
                Long insertEventTriggerHistoryId = marketingService
                    .saveEventTriggerHistory(innterEventTriggerHistoryParam);

                Map<String, Object> variables = Maps.newHashMap();
                variables.put("msgContext", innterEventTriggerHistoryParam);
                defineDTO.setExcuteConfigJson(null);
                defineDTO.setMarketingConfigJson(null);
                variables.put("marketingDefineId", defineDTO.getId());
                variables.put("marketingType", defineDTO.getMarketingType());
                variables.put("merchantCode", defineDTO.getMerchantCode());
                // 事件触发历史记录ID，用于后续流程执行完毕后更新完成状态
                variables.put("eventTriggerHistoryId", insertEventTriggerHistoryId);
                runtimeService.startProcessInstanceByKey(PromotionContants.ACTIVITI_PROCESS_PREFIX + defineDTO.getId(),
                    variables);
            });
        }
    }

    /**
     * 根据MQ消息体，拼接EventTriggerHistoryParam
     *
     * @param memberDetailDTO
     * @return EventTriggerHistoryParam
     * @author PQ
     * @date 2019/6/14
     */
    private EventTriggerHistoryParam generateEventTriggerParamFromJson(String eventCode,
        MemberDetailDTO memberDetailDTO, JSONObject extInfo) {
        EventTriggerHistoryParam eventTriggerHistoryParam = new EventTriggerHistoryParam();
        eventTriggerHistoryParam.setEventCode(eventCode);
        eventTriggerHistoryParam.setMemberId(memberDetailDTO.getId());
        eventTriggerHistoryParam.setMemberName(memberDetailDTO.getName());
        eventTriggerHistoryParam.setMemberPhone(memberDetailDTO.getPhone());
        eventTriggerHistoryParam.setExtInfo(extInfo);
        // eventTriggerHistoryParam.setWxNickname(msgObject.getString("wxNickname"));
        // eventTriggerHistoryParam.setIdentifyId(msgObject.getLong("identifyId"));
        // eventTriggerHistoryParam.setIdentifyCode(msgObject.getString("identifyCode"));
        // TODO 操作人改为json类型，暂时屏蔽
        // eventTriggerHistoryParam.setCreator(PromotionContants.SYSTEM_AUTO_OPERATOR);
        eventTriggerHistoryParam.setGmtCreate(new Date());
        // 触发事件完成状态
        eventTriggerHistoryParam.setIsProcessFinished(MarketingProcessFinishStatusEnum.NO.getValue());
        return eventTriggerHistoryParam;
    }

    /**
     * 查询满足条件的事件营销活动定义
     *
     * @param merchantCode
     * @param eventCode
     * @return eventTriggerHistoryParam
     * @author PQ
     * @date 2019/4/17
     */
    private List<MarketingDefineDTO> getMatchDefine(String merchantCode, String eventCode,
        EventTriggerHistoryParam eventTriggerHistoryParam) {
        List<MarketingDefineDTO> lstFlowContext = Lists.newArrayList();
        try {
            List<MarketingDefineDTO> lstDefine = marketingService.listActiveCampaignDefines(merchantCode,
                MarketingTypeEnum.EVENT.getCode());
            if (null != lstDefine && lstDefine.size() > 0) {
                // 获取一天 0 点的毫秒值，例：2019-08-09 00:00:00
                Date currentDate = DateUtil.getDateZero(DateUtil.getNow());
                lstFlowContext = lstDefine.stream().filter(define -> {
                    return null == define.getStarttime() || null == define.getFinishtime()
                        || (currentDate.compareTo(define.getStarttime()) >= 0
                            && currentDate.compareTo(define.getFinishtime()) <= 0);
                }).filter(define -> {
                    JSONObject eventNodeJson = JSONObject.parseArray(define.getExcuteConfigJson()).getJSONObject(0);
                    // 判断第一个节点事件ID是否与eventId匹配
                    if (!eventCode.equals(eventNodeJson.getString("eventCode"))) {
                        return false;
                    }

                    try {
                        // 解析到具体的事件节点
                        MarketingEventTypeEnum eventTypeEnum = MarketingEventTypeEnum.getByEventCode(eventCode);
                        if (eventTypeEnum == null) {
                            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0014");
                        }
                        BaseEventNodeHandler baseEventNodeHandler = eventTypeEnum.getClassName().newInstance();
                        return baseEventNodeHandler.execute(define, eventTriggerHistoryParam);
                    }
                    catch (Exception e) {
                        logger.error("EventCampaignConsumer.doBusinesses=> parse BaseEventNodeHandler error: "
                            + "marketingDefineId={},errorMsg={}", define.getId(), e.getMessage());
                        return false;
                    }
                }).collect(Collectors.toList());
            }
        }
        catch (Exception e) {
            logger.error("EventCampaignConsumer.getMatchDefine exception: ", e);
        }

        return lstFlowContext;
    }
}
