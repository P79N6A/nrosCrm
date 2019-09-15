/**
 * [Product]
 * crm
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * MarketingServiceImpl.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019年3月27日   wangzhe    最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.base.util.DateUtil;
import com.ztesoft.zsmart.nros.base.util.StringUtil;
import com.ztesoft.zsmart.nros.crm.core.client.api.MarketingService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTriggerHistoryDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingEventStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingInstanceDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingInstanceStaticsDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingInstanceStaticsDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.NodeExecuteRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.MarketingDefineParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTriggerHistoryQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineListQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingInstanceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingStatusEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.util.NrosPreconditions;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.MarketingDomain;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.BpmnModelUtil;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CouponService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponReservedAndCheckedDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponStatisticsDTO;

/**
 * 营销服务实现类
 *
 * @author wangzhe
 * @date 2019/4/9 13:28
 */
@Service
public class MarketingServiceImpl implements MarketingService {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MarketingServiceImpl.class);

    /**
     * 优惠券领域
     */
    @Autowired
    private MarketingDomain marketingDomain;

    /**
     * activiti流程存储服务
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * activiti流程运行服务
     */
    @Autowired
    private RuntimeService runtimeService;

    /**
     * activiti流程历史服务
     */
    @Autowired
    private HistoryService historyService;

    /**
     * 优惠券feign服务
     */
    @Autowired
    CouponService couponService;

    /**
     * 营销定义保存
     *
     * @param marketingDefineParam 营销定义保存参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long defineSave(MarketingDefineParam marketingDefineParam) {
        Long id = marketingDomain.defineSave(marketingDefineParam);
        return id;
    }

    /**
     * 营销定义修改
     *
     * @param marketingDefineParam 营销定义修改参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void defineUpdate(MarketingDefineParam marketingDefineParam) {
        marketingDomain.defineUpdate(marketingDefineParam);
    }

    /**
     * 营销定义删除
     *
     * @param marketingDefineParam 营销定义删除参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void defineDelete(MarketingDefineParam marketingDefineParam) {
        marketingDomain.defineDelete(marketingDefineParam);
    }

    /**
     * 营销流程保存
     *
     * @param marketingDefineParam 营销流程保存参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processSave(MarketingDefineParam marketingDefineParam) {
        marketingDomain.processSave(marketingDefineParam);
        // 保存并启用
        if (StringUtils.equals(marketingDefineParam.getIsEnable(), MarketingStatusEnum.ENABLE.getState())) {
            JSONObject timeData = this.getProcessDefineTimeData(marketingDefineParam.getMarketingConfigJson());
            // 流程定义中的开始时间
            String flowBeginDate = (null == timeData) ? null : timeData.getString("from");
            /**
             * 如果存在周期性开始日期，且晚于当前时间，则需启用委托流程部署!! 注：通过部署委托流程，设置委托流程在指定开始日期触发时才真正部署当前目标流程的方式，以此来扩展activiti周期定时器不支持开始日期的缺陷
             */
            if (MarketingTypeEnum.PUSH.getCode().equals(marketingDefineParam.getMarketingType())
                && flowBeginDate != null
                && DateUtil.strToDate(flowBeginDate, DateUtil.DEFAULT_DATE_FORMAT).compareTo(DateUtil.getNow()) > 0) {
                BpmnModel delegateBpmnModel = BpmnModelUtil.generateDelegateDeployBpmnModel(flowBeginDate + "T00:00:00",
                    marketingDefineParam.getId());
                String activitiProcessKey = PromotionContants.ACTIVITI_PROCESS_PREFIX + marketingDefineParam.getId();
                BpmnModelUtil.deployProcess(delegateBpmnModel, activitiProcessKey);
            }
            else {
                // 将前端JSON流程定义数据转换成Bpmn.xml标准格式，部署到activiti
                BpmnModelUtil.deployProcess(marketingDefineParam.getMarketingConfigJson(),
                    marketingDefineParam.getId());
                // 若为主动营销&&非触发型流程，还需启动部署的流程
                if (MarketingTypeEnum.PUSH.getCode().equals(marketingDefineParam.getMarketingType()) && null != timeData
                    && 0 != timeData.getInteger("scheduleWay")) {
                    BpmnModelUtil.startActivitiProcess(marketingDefineParam);
                }
            }
        }
    }

    /**
     * 营销流程启用
     *
     * @param marketingDefineParam 营销流程启用参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processEnable(MarketingDefineParam marketingDefineParam) {
        marketingDomain.processEnable(marketingDefineParam);

        // 查询当前流程定义是否存在activiti实例
        String activitiProcessKey = PromotionContants.ACTIVITI_PROCESS_PREFIX + marketingDefineParam.getId();
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
            .processDefinitionKey(activitiProcessKey).latestVersion().list();
        if (!CollectionUtils.isEmpty(processDefinitionList)) {
            // 激活该流程定义下所有运行的流程实例
            // repositoryService.activateProcessDefinitionByKey(activitiProcessKey, true, null);
            // 激活该流程定义下的流程实例
            if (processDefinitionList.get(0).isSuspended()) {
                repositoryService.activateProcessDefinitionById(processDefinitionList.get(0).getId(), true, null);
                return;
            }
        }
        else {
            /**
             * 注：以下逻辑用于处理同时满足如下条件的特殊场景： 1、当前营销流程为存在起始日期区间的周期性主动营销; 2、当委托代理流程抵达当前流程开始日期触发条件，而流程定义已被界面设置为停止状态，造成当前流程未被部署
             * 3、当前时间已大于流程定义开始日期 在这种特殊场景下，应该重新部署当前流程定义，以使其启动后能正常执行
             */
            MarketingDefineQuery marketingDefineQuery = new MarketingDefineQuery();
            marketingDefineQuery.setId(marketingDefineParam.getId());
            MarketingDefineDTO marketingDefineDTO = marketingDomain.detail(marketingDefineQuery);
            JSONObject timeData = this.getProcessDefineTimeData(marketingDefineDTO.getMarketingConfigJson());
            // 流程定义中的开始时间
            String flowBeginDate = (null == timeData) ? null : timeData.getString("from");
            // 若是周期性流程定义，且开始时间早于当前时间，则还需对其进行部署
            if (MarketingTypeEnum.PUSH.getCode().equals(marketingDefineDTO.getMarketingType())
                && StringUtil.isNotNull(flowBeginDate)
                && DateUtil.strToDate(flowBeginDate, DateUtil.DEFAULT_DATE_FORMAT).compareTo(DateUtil.getNow()) <= 0) {
                BpmnModelUtil.deployProcess(marketingDefineDTO.getMarketingConfigJson(), marketingDefineDTO.getId());
            }
        }
    }

    /**
     * 营销流程停用
     *
     * @param marketingDefineParam 营销流程停用参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processDisable(MarketingDefineParam marketingDefineParam) {
        marketingDomain.processDisable(marketingDefineParam);

        // 查询当前流程定义是否存在activiti实例
        String activitiProcessKey = PromotionContants.ACTIVITI_PROCESS_PREFIX + marketingDefineParam.getId();
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
            .processDefinitionKey(activitiProcessKey).latestVersion().list();
        if (!CollectionUtils.isEmpty(processDefinitionList) && !processDefinitionList.get(0).isSuspended()) {
            // 挂起该流程定义下所有运行的流程实例
            // repositoryService.suspendProcessDefinitionByKey(activitiProcessKey, true, null);
            repositoryService.suspendProcessDefinitionById(processDefinitionList.get(0).getId(), true, null);
            return;
        }
    }

    /**
     * 营销定义详情查询
     *
     * @param marketingDefineQuery 营销定义详情查询参数
     */
    @Override
    public MarketingDefineDTO detail(MarketingDefineQuery marketingDefineQuery) {
        // 查询营销定义详情
        MarketingDefineDTO marketingDefineDTO = marketingDomain.detail(marketingDefineQuery);
        return marketingDefineDTO;
    }

    /**
     * 营销定义列表查询
     *
     * @param query
     * @return
     */
    @Override
    public PageInfo<MarketingDefineDTO> queryList(MarketingDefineListQuery query) {
        logger
            .info("MarketingService.queryList start, param:" + "marketingDefineListQuery=" + JSON.toJSONString(query));
        // 1.获取PageInfo
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        // PageInfo<MarketingDefineDTO> pageInfo = page.toPageInfo();

        // 2.查询domain
        //
        //// 3.DO集合转DTO集合
        // List<MarketingDefineDTO> marketingDefineDTOList = ConvertUtil
        // .listEntity2DTO(marketingDefineDTOPageInfo.getList(), MarketingDefineDTO.class);

        // 4.插入数据到PageInfo并返回
        // pageInfo.setList(marketingDefineDTOPageInfo.getList());
        // pageInfo.setTotal(marketingDefineDTOPageInfo.getTotal());
        // pageInfo.setPages(marketingDefineDTOPageInfo.getPages());
        logger.info("MarketingService.queryList end!");
        return marketingDomain.queryList(query);
    }

    /**
     * 事件营销有效期和触发频率设置
     *
     * @param param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setAnalysis(MarketingDefineParam param) {
        marketingDomain.setAnalysis(param);
    }

    /**
     * 事件触发统计分析查询
     *
     * @param query
     * @return
     */
    @Override
    public MarketingEventStatisticsDTO statisticsQuery(EventTriggerHistoryQuery query) {
        // 参数校验
        NrosPreconditions.getInstance().notNull(query.getMarketingId(), "NROS-SBC-PROMOTION-MARKET-0001");
        // 调用domain查询
        MarketingEventStatisticsDTO marketingEventStatisticsDTO = marketingDomain.statisticsQuery(query);
        return marketingEventStatisticsDTO;
    }

    /**
     * 事件触发历史列表
     *
     * @param query
     * @return
     */
    @Override
    public PageInfo<EventTriggerHistoryDTO> eventTriggerList(EventTriggerHistoryQuery query) {
        logger.info(
            "MarketingService.eventTriggerList start, param:" + "eventTriggerHistoryQuery=" + JSON.toJSONString(query));
        // 1.获取PageInfo
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        // PageInfo<EventTriggerHistoryDTO> pageInfo = page.toPageInfo();

        // 2.查询DTO集合

        // 4.插入数据到PageInfo并返回
        // pageInfo.setList(eventTriggerHistoryDTOPageInfo.getList());
        // pageInfo.setTotal(eventTriggerHistoryDTOPageInfo.getTotal());
        // pageInfo.setPages(eventTriggerHistoryDTOPageInfo.getPages());
        logger.info("MarketingService.eventTriggerList end!");
        return marketingDomain.eventTriggerList(query);
    }

    /**
     * 新增事件触发历史记录
     *
     * @param eventTriggerHistoryParam
     * @return void
     * @author PQ
     * @date 2019/4/19
     */
    @Override
    public Long saveEventTriggerHistory(EventTriggerHistoryParam eventTriggerHistoryParam) {
        return marketingDomain.saveEventTriggerHistory(eventTriggerHistoryParam);
    }

    /**
     * 查询生效状态的营销定义列表
     *
     * @param merchantCode 商家编码
     * @param marketingType 营销类型:[1]主动营销,[2]事件营销
     * @return List<MarketingDefineDTO>
     * @author PQ
     * @date 2019/4/18
     */
    @Override
    public List<MarketingDefineDTO> listActiveCampaignDefines(String merchantCode, String marketingType) {
        return marketingDomain.listActiveCampaignDefines(merchantCode, marketingType);
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
    @Override
    public List<EventTriggerHistoryDTO> queryTriggerHisByUserAndMarketingId(String merchantCode, Long marketingId,
        Long memberId) {
        return marketingDomain.queryTriggerHisByUserAndMarketingId(merchantCode, marketingId, memberId);
    }

    /**
     * 营销流程执行实例查询
     *
     * @param marketingDefineId
     * @return List<MarketingInstanceDTO>
     * @author PQ
     * @date 2019/7/24
     */
    @Override
    public List<MarketingInstanceDTO> instanceQuery(Long marketingDefineId) {
        // 参数校验
        if (marketingDefineId == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0030");
        }
        // 拼接查询条件
        MarketingInstanceQuery query = new MarketingInstanceQuery();
        query.setMarketingId(marketingDefineId);
        query.setExecuteNodeType(MarketingNodeExecuteTypeEnum.FILTER.getCode());
        List<NodeExecuteRecordDTO> nodeExecuteRecordDTOList = marketingDomain.instanceQuery(query);
        if (CollectionUtils.isEmpty(nodeExecuteRecordDTOList)) {
            return Lists.newArrayList();
        }
        // 按创建日期排序
        nodeExecuteRecordDTOList.sort(Comparator.comparing(NodeExecuteRecordDTO::getGmtCreate));
        return ConvertUtil.listEntity2DTO(nodeExecuteRecordDTOList, MarketingInstanceDTO.class);
    }

    /**
     * 营销流程执行实例统计分析查询
     *
     * @param marketingDefineId 营销流程定义ID
     * @param marketingInstanceId 营销流程实例ID
     * @return List<MarketingInstanceStaticsDTO>
     * @author PQ
     * @date 2019/7/24
     */
    @Override
    public List<MarketingInstanceStaticsDTO> instanceStatisticsQuery(Long marketingDefineId,
        String marketingInstanceId) {
        // 参数校验
        if (null == marketingDefineId || StringUtil.isNull(marketingInstanceId)) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0016");
        }
        MarketingDefineQuery marketingDefineQuery = new MarketingDefineQuery();
        marketingDefineQuery.setId(marketingDefineId);
        MarketingDefineDTO marketingDefineDTO = marketingDomain.detail(marketingDefineQuery);
        final String startDateStr;
        final String endDateStr;
        // 设置分析周期参数
        if ("1".equals(marketingDefineDTO.getIsRelative())) {
            // 相对日期设置
            // 根据实例ID，查询当前流程实例对应的开始、结束时间
            HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService
                .createHistoricProcessInstanceQuery();
            historicProcessInstanceQuery.processInstanceId(marketingInstanceId);
            // 查询流程历史数据
            HistoricProcessInstance historicProcessInstance = historicProcessInstanceQuery.singleResult();
            if (historicProcessInstance != null) {
                Date startTime = historicProcessInstance.getStartTime();
                Date endTime = historicProcessInstance.getEndTime();
                Integer beforeCount = marketingDefineDTO.getBeforeCount();
                Integer afterCount = marketingDefineDTO.getAfterCount();
                // 设置统计分析开始日期
                startDateStr = (startTime != null && beforeCount != 0) ? DateUtil
                    .dateToStr(DateUtil.getDateZero(startTime, beforeCount), DateUtil.DEFAULT_DATE_TIME_FORMAT) : null;

                // 设置统计分析结束日期
                endDateStr = (endTime != null && afterCount != 0)
                    ? DateUtil.dateToStr(DateUtil.getDateZero(endTime, afterCount), DateUtil.DEFAULT_DATE_FORMAT)
                        + " 23:59:59"
                    : null;
            }
            else {
                startDateStr = null;
                endDateStr = null;
            }
        }
        else if ("0".equals(marketingDefineDTO.getIsRelative())) {
            // 绝对日期设置
            startDateStr = marketingDefineDTO.getAnalysisStart() != null
                ? DateUtil.dateToStr(marketingDefineDTO.getAnalysisStart(), DateUtil.DEFAULT_DATE_FORMAT) + " 00:00:00"
                : null;
            endDateStr = (marketingDefineDTO.getAnalysisEnd() != null)
                ? DateUtil.dateToStr(marketingDefineDTO.getAnalysisEnd(), DateUtil.DEFAULT_DATE_FORMAT) + " 23:59:59"
                : null;
        }
        else {
            startDateStr = null;
            endDateStr = null;
        }

        // 拼接查询条件
        MarketingInstanceQuery query = new MarketingInstanceQuery();
        query.setMarketingId(marketingDefineId);
        query.setMarketingInstanceId(marketingInstanceId);
        if (StringUtil.isNotNull(startDateStr)) {
            query.setBeginDate(DateUtil.strToDate(startDateStr, DateUtil.DEFAULT_DATE_TIME_FORMAT));
        }
        if (StringUtil.isNotNull(endDateStr)) {
            query.setEndDate(DateUtil.strToDate(endDateStr, DateUtil.DEFAULT_DATE_TIME_FORMAT));
        }

        List<NodeExecuteRecordDTO> nodeExecuteRecordDTOList = marketingDomain.instanceStatisticsQuery(query);

        List<MarketingInstanceStaticsDTO> resultList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(nodeExecuteRecordDTOList)) {
            // 过滤掉不需要展示的节点类型数据
            nodeExecuteRecordDTOList.parallelStream().filter(nodeExecuteRecordDTO -> {
                MarketingNodeExecuteTypeEnum nodeExecuteTypeEnum = MarketingNodeExecuteTypeEnum
                    .getByCode(nodeExecuteRecordDTO.getNodeType());
                return nodeExecuteTypeEnum != null && nodeExecuteTypeEnum.getDisplay();
                // 按流程节点类型分组
            }).collect(Collectors.groupingBy(NodeExecuteRecordDTO::getNodeType))
                .forEach((nodeType, nodeExecuteRecordDTOS) -> {
                    // 若为优惠券类型节点，还需查询营销中心优惠券核销数据
                    boolean isCouponType = MarketingNodeExecuteTypeEnum.SENDCOUPON.getCode().equals(nodeType);
                    final List<CouponReservedAndCheckedDTO> couponReservedAndCheckedDTOList;
                    if (isCouponType) {
                        CouponStatisticsDTO couponStatisticsDTO = couponService.statisticsOfReservedAndChecked(
                            marketingInstanceId, Long.toString(marketingDefineId), startDateStr, endDateStr).getData();
                        couponReservedAndCheckedDTOList = (couponStatisticsDTO != null)
                            ? couponStatisticsDTO.getCouponReservedAndCheckedDTOList()
                            : null;
                    }
                    else {
                        couponReservedAndCheckedDTOList = null;
                    }
                    MarketingInstanceStaticsDTO marketingInstanceStaticsDTO = new MarketingInstanceStaticsDTO();
                    marketingInstanceStaticsDTO.setNodeType(nodeType);
                    MarketingNodeExecuteTypeEnum nodeExecuteTypeEnum = MarketingNodeExecuteTypeEnum.getByCode(nodeType);
                    marketingInstanceStaticsDTO
                        .setNodeName(nodeExecuteTypeEnum == null ? null : nodeExecuteTypeEnum.getName());
                    marketingInstanceStaticsDTO.setMarketingId(nodeExecuteRecordDTOS.get(0).getMarketingId());
                    marketingInstanceStaticsDTO
                        .setMarketingInstanceId(nodeExecuteRecordDTOS.get(0).getMarketingInstanceId());
                    // 拼接当前类型节点执行明细记录
                    List<MarketingInstanceStaticsDetailDTO> details = nodeExecuteRecordDTOS.parallelStream()
                        .map(innerNodeExecuteRecordDTO -> {
                            MarketingInstanceStaticsDetailDTO marketingInstanceStaticsDetailDTO = new MarketingInstanceStaticsDetailDTO();
                            marketingInstanceStaticsDetailDTO.setNodeId(innerNodeExecuteRecordDTO.getNodeId());
                            marketingInstanceStaticsDetailDTO.setNodeName(innerNodeExecuteRecordDTO.getNodeName());
                            marketingInstanceStaticsDetailDTO.setGmtCreate(innerNodeExecuteRecordDTO.getGmtCreate());
                            marketingInstanceStaticsDetailDTO
                                .setTargetUserCount(innerNodeExecuteRecordDTO.getTargetUserCount());
                            marketingInstanceStaticsDetailDTO.setTargetActionCount(0L);
                            // 如果是送积分类型，还需统计积分数
                            if (MarketingNodeExecuteTypeEnum.SENDPOINT.getCode().equals(nodeType)) {
                                JSONObject actionParamJson = JSON.parseObject(innerNodeExecuteRecordDTO.getParam());
                                // 变化的积分
                                Long exchangePoints = actionParamJson.getLong("points");
                                // 汇总当前节点赠送积分数量
                                marketingInstanceStaticsDetailDTO.setTargetActionCount(
                                    exchangePoints * innerNodeExecuteRecordDTO.getTargetUserCount());
                            }
                            // 如果是送优惠券类型，还需设置核销情况
                            else if (isCouponType && !CollectionUtils.isEmpty(couponReservedAndCheckedDTOList)) {
                                for (CouponReservedAndCheckedDTO couponReservedAndCheckedDTO : couponReservedAndCheckedDTOList) {
                                    if (innerNodeExecuteRecordDTO.getNodeId().equals(couponReservedAndCheckedDTO.getActiveNodeCode())) {
                                        marketingInstanceStaticsDetailDTO
                                            .setTargetActionCount(couponReservedAndCheckedDTO.getCheckedCount());
                                        break;
                                    }
                                }
                            }
                            return marketingInstanceStaticsDetailDTO;
                        }).collect(Collectors.toList());
                    // 按创建时间排序
                    details.sort(Comparator.comparing(MarketingInstanceStaticsDetailDTO::getGmtCreate));
                    marketingInstanceStaticsDTO.setDetails(details);
                    // 汇总当前类型节点执行总人次
                    marketingInstanceStaticsDTO.setTotalTargetUser(details.stream()
                        .collect(Collectors.summarizingLong(MarketingInstanceStaticsDetailDTO::getTargetUserCount))
                        .getSum());
                    // 汇总当前类型节点执行动作(如优惠券核销、积分数汇总等)
                    marketingInstanceStaticsDTO.setTotalTargetAction(details.stream()
                        .collect(Collectors.summarizingLong(MarketingInstanceStaticsDetailDTO::getTargetActionCount))
                        .getSum());
                    resultList.add(marketingInstanceStaticsDTO);
                });
            // 按照节点类型排序
            resultList.sort(Comparator.comparing(MarketingInstanceStaticsDTO::getNodeType));
        }
        return resultList;
    }

    /**
     * 获取营销流程定义中首节点时间数据
     *
     * @param marketingConfigJson
     * @return JSONObject
     * @author PQ
     * @date 2019/7/18
     */
    private JSONObject getProcessDefineTimeData(String marketingConfigJson) {
        JSONObject timeData = null;
        if (StringUtil.isNotNull(marketingConfigJson)) {
            try {
                JSONObject originalJson = JSON.parseObject(marketingConfigJson);
                // 获取流程第一个节点
                JSONObject firstFlowNode = originalJson.getJSONArray("children").getJSONObject(0);
                timeData = firstFlowNode.getJSONObject("timeData");
            }
            catch (Exception e) {
                logger.error("getProcessDefineStartDate() error! msg={}", e.getMessage());
            }
        }
        return timeData;
    }
}
