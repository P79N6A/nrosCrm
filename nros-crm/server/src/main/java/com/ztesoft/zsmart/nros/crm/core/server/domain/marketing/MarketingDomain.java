/**
 * [Product]
 * crm
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * MarketingDomain.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019年3月27日   wangzhe    最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.domain.marketing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.common.model.enums.StatusEnum;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTriggerHistoryDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingEventStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.NodeExecuteRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.MarketingDefineParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.NodeExecuteRecordParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTriggerHistoryQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineListQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingInstanceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.EventTriggerHistoryConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.MarketingDefineConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.NodeExecuteRecordConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingStatusEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.util.NrosPreconditions;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.MarketingBO;
import com.ztesoft.zsmart.nros.crm.core.server.repository.MarketingRepository;

import lombok.Setter;

/**
 * 营销领域
 *
 * @author wangzhe
 * @date 2019/4/9 13:28
 */
@Component
@Setter
public class MarketingDomain {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MarketingDomain.class);

    /**
     * 营销Repository
     */
    @Autowired
    private MarketingRepository marketingRepository;

    /**
     * 营销定义保存
     *
     * @param marketingDefineParam 营销定义保存参数
     */
    public Long defineSave(MarketingDefineParam marketingDefineParam) {
        // 参数校验
        if (StringUtils.isBlank(marketingDefineParam.getMarketingName())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0033");
        }
        if (StringUtils.isBlank(marketingDefineParam.getFrequenceType())) {
            marketingDefineParam.setFrequenceType("0");
        }

        // Param转BO并插入数据库
        MarketingBO marketingDefineBO = MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam);
        marketingDefineBO.setMarketingStatus(MarketingStatusEnum.IN_DESIGN.getState());
        marketingDefineBO.setMerchantCode(marketingDefineParam.getMerchantCode());
        marketingDefineBO.setStatus(StatusEnum.ENABLE.getState());
        return marketingRepository.defineSave(marketingDefineBO);
    }

    /**
     * 营销定义修改
     *
     * @param marketingDefineParam 营销定义修改参数
     */
    public void defineUpdate(MarketingDefineParam marketingDefineParam) {
        // 公共校验
        this.baseMarketingDefineCheck(marketingDefineParam.getId());
        // Param转BO
        MarketingBO marketingProcessBO = MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam);
        // 更新到数据库
        marketingRepository.defineUpdate(marketingProcessBO);
    }

    /**
     * 营销定义删除
     *
     * @param marketingDefineParam 营销定义删除参数
     */
    public void defineDelete(MarketingDefineParam marketingDefineParam) {
        // 公共校验
        MarketingDefineDTO marketingDefineDTO = this.baseMarketingDefineCheck(marketingDefineParam.getId());

        // 非设计状态的营销活动不允许再修改
        if (!StringUtils.equals(MarketingStatusEnum.IN_DESIGN.getState(), marketingDefineDTO.getMarketingStatus())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0034");
        }

        // Param转BO
        MarketingBO marketingProcessBO = MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam);
        // 更新到数据库
        marketingRepository.defineDelete(marketingProcessBO);
    }

    /**
     * 营销流程保存
     *
     * @param marketingDefineParam 营销流程保存参数
     */
    public void processSave(MarketingDefineParam marketingDefineParam) {
        // 公共校验
        MarketingDefineDTO marketingDefineDTO = this.baseMarketingDefineCheck(marketingDefineParam.getId());

        // 非设计状态的营销活动不允许再修改
        if (!StringUtils.equals(MarketingStatusEnum.IN_DESIGN.getState(), marketingDefineDTO.getMarketingStatus())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0032");
        }

        // Param转BO
        MarketingBO marketingProcessBO = MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam);

        // 保存并启用
        if (StringUtils.equals(marketingDefineParam.getIsEnable(), "1")) {
            // 营销预览配置JSON转流程执行配置JSON
            String excuteConfigJson = previewToExcute(marketingProcessBO.getMarketingConfigJson());
            marketingProcessBO.setExcuteConfigJson(excuteConfigJson);
            // 启用
            marketingProcessBO.setMarketingStatus(MarketingStatusEnum.ENABLE.getState());
        }
        // 更新到数据库
        marketingRepository.processSave(marketingProcessBO);
    }

    /**
     * 营销流程启用
     *
     * @param marketingDefineParam 营销流程启用参数
     */
    public void processEnable(MarketingDefineParam marketingDefineParam) {
        // 公共校验
        this.baseMarketingDefineCheck(marketingDefineParam.getId());
        marketingRepository.processEnable(MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam));

    }

    /**
     * 营销流程停用
     *
     * @param marketingDefineParam 营销流程停用参数
     */
    public void processDisable(MarketingDefineParam marketingDefineParam) {
        // 公共校验
        this.baseMarketingDefineCheck(marketingDefineParam.getId());
        marketingRepository.processDisable(MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam));
    }

    /**
     * 营销定义详情查询
     *
     * @param marketingDefineQuery 营销定义详情查询参数
     */
    public MarketingDefineDTO detail(MarketingDefineQuery marketingDefineQuery) {
        // 公共校验
        this.baseMarketingDefineCheck(marketingDefineQuery.getId());
        return marketingRepository.detail(marketingDefineQuery);
    }

    /**
     * 营销定义列表查询
     *
     * @param query
     * @return
     */
    public PageInfo<MarketingDefineDTO> queryList(MarketingDefineListQuery query) {
        return marketingRepository.queryList(query);
    }

    /**
     * 事件营销有效期和触发频率设置
     *
     * @param param
     */
    public void setAnalysis(MarketingDefineParam param) {
        // 数据校验
        NrosPreconditions.getInstance().notNull(param.getId(), "NROS-SBC-PROMOTION-MARKET-0001");
        // 修改
        marketingRepository.setAnalysis(MarketingDefineConvertor.INSTANCE.paramToBO(param));

    }

    /**
     * 查询生效状态的营销定义列表
     *
     * @param merchantCode  商家CODE
     * @param marketingType 营销类型:[1]主动营销,[2]事件营销
     * @return List<MarketingDefineDTO>
     * @author PQ
     * @date 2019/4/18
     */
    public List<MarketingDefineDTO> listActiveCampaignDefines(String merchantCode, String marketingType) {
        return marketingRepository.listActiveCampaignDefines(merchantCode, marketingType);
    }

    /**
     * 新增事件触发历史记录
     *
     * @param eventTriggerHistoryParam
     * @return void
     * @author PQ
     * @date 2019/4/19
     */
    public Long saveEventTriggerHistory(EventTriggerHistoryParam eventTriggerHistoryParam) {
        return marketingRepository
                .saveEventTriggerHistory(EventTriggerHistoryConvertor.INSTANCE.paramToBO(eventTriggerHistoryParam));
    }

    /**
     * 事件触发统计分析查询
     *
     * @param query
     * @return
     */
    public MarketingEventStatisticsDTO statisticsQuery(EventTriggerHistoryQuery query) {
        return marketingRepository.statisticsQuery(query);

    }

    /**
     * 事件触发历史列表
     *
     * @param query
     * @return
     */
    public PageInfo<EventTriggerHistoryDTO> eventTriggerList(EventTriggerHistoryQuery query) {
        return marketingRepository.eventTriggerList(query);
    }

    /**
     * 查询用户在指定营销定义活动的事件触发记录
     *
     * @param merchantCode
     * @param marketingId
     * @param memberId
     * @return java.util.List<EventTriggerHistoryDTO>
     * @author PQ
     * @date 2019/4/29
     */
    public List<EventTriggerHistoryDTO> queryTriggerHisByUserAndMarketingId(String merchantCode, Long marketingId,
                                                                            Long memberId) {
        return marketingRepository.queryTriggerHisByUserAndMarketingId(merchantCode, marketingId, memberId);
    }

    /**
     * 更新事件触发历史记录表
     *
     * @param eventTriggerHistoryParam
     * @return void
     * @author PQ
     * @date 2019/6/24
     */
    public void updateEventTriggerHisByKey(EventTriggerHistoryParam eventTriggerHistoryParam) {
        marketingRepository
                .updateEventTriggerHisByKey(EventTriggerHistoryConvertor.INSTANCE.paramToBO(eventTriggerHistoryParam));
    }

    /**
     * 新增营销流程节点执行记录
     *
     * @param nodeExecuteRecordParam
     * @return java.lang.Long
     * @author PQ
     * @date 2019/7/24
     */
    public Long saveNodeExecuteRecord(NodeExecuteRecordParam nodeExecuteRecordParam) {
        return marketingRepository
                .saveNodeExecuteRecord(NodeExecuteRecordConvertor.INSTANCE.paramToBO(nodeExecuteRecordParam));
    }

    /**
     * 营销流程执行实例查询
     *
     * @param query
     * @return List<MarketingInstanceDTO>
     * @author PQ
     * @date 2019/7/24
     */
    public List<NodeExecuteRecordDTO> instanceQuery(MarketingInstanceQuery query) {
        return marketingRepository.instanceQuery(query);
    }

    /**
     * 营销流程执行实例统计分析查询
     *
     * @param query
     * @return List<MarketingInstanceStaticsDTO>
     * @author PQ
     * @date 2019/7/24
     */
    public List<NodeExecuteRecordDTO> instanceStatisticsQuery(MarketingInstanceQuery query) {
        return marketingRepository.instanceQuery(query);
    }

    /**
     * 营销预览配置JSON转流程执行配置JSON
     *
     * @param marketingConfigJson 营销预览配置JSON
     * @return 流程执行配置JSON
     */
    private String previewToExcute(String marketingConfigJson) {
        Integer seq = 0;
        Map<String, Integer> seqMap = new HashMap<>(1);
        seqMap.put("seq", seq);
        JSONArray excuteConfigJson = new JSONArray();
        JSONObject originalJson = JSON.parseObject(marketingConfigJson);

        JSONArray jsonArray = originalJson.getJSONArray("children");
        if (jsonArray != null && jsonArray.size() > 0) {
            jsonArray.forEach(childJsonObj -> {
                JSONObject childJson = (JSONObject) childJsonObj;
                parseNodeJson(childJson, excuteConfigJson, seqMap, null);
            });
        }
        else {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0035");
        }

        return JSON.toJSONString(excuteConfigJson);
    }

    /**
     * 解析节点JSON
     *
     * @param nodeJson         节点JSON对象
     * @param excuteConfigJson 流程执行配置JSON
     * @param seqMap           执行步骤
     * @param parentId         父节点ID
     */
    private void parseNodeJson(JSONObject nodeJson, JSONArray excuteConfigJson, Map<String, Integer> seqMap,
                               String parentId) {
        String id = nodeJson.getString("id");
        JSONArray jsonArray = nodeJson.getJSONArray("children");

        if (parentId != null) {
            nodeJson.put("parentId", parentId);
        }

        Integer seq = seqMap.get("seq");
        nodeJson.put("seq", seq);
        seq++;
        seqMap.put("seq", seq);
        nodeJson.remove("children");
        excuteConfigJson.add(nodeJson);
        boolean condition = jsonArray != null && jsonArray.size() > 0
                && (StringUtils.equals(nodeJson.getString("isLast"), "0"));
        if (condition) {
            for (Object childJsonObj : jsonArray) {
                JSONObject childJson = (JSONObject) childJsonObj;
                parseNodeJson(childJson, excuteConfigJson, seqMap, id);
            }
        }
        else {
            return;
        }
    }

    /**
     * 公共校验
     *
     * @param id 营销定义ID
     * @return 营销定义DTO
     */
    private MarketingDefineDTO baseMarketingDefineCheck(Long id) {
        // 参数校验
        if (id == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0030");
        }

        // 根据id查询营销定义
        MarketingDefineDTO marketingDefineDTO = marketingRepository.getMarketingDefineByPrimaryKey(id);
        if (marketingDefineDTO == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0031");
        }
        return marketingDefineDTO;
    }

}
