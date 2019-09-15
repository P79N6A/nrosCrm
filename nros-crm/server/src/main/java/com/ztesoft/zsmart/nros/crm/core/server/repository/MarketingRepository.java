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
package com.ztesoft.zsmart.nros.crm.core.server.repository;

import java.util.List;

import com.ztesoft.zsmart.nros.crm.core.client.model.dto.NodeExecuteRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingInstanceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.NodeExecuteRecordMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.common.model.enums.StatusEnum;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTriggerHistoryDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingEventStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTriggerHistoryQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineListQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.EventTriggerHistoryConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.MarketingDefineConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.NodeExecuteRecordConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingStatusEnum;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTriggerHistoryDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingDefineDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.NodeExecuteRecordDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.EventTriggerHistoryMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.MarketingDefineMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.EventTriggerHistoryDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.MarketingDefineDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.NodeExecuteRecordDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.EventTriggerHistoryBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.MarketingBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.NodeExecuteRecordBO;

import lombok.Setter;

/**
 * 营销Repository
 * 
 * @author PQ
 * @date 2019/6/24
 */
@Repository
@Setter
public class MarketingRepository {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MarketingRepository.class);

    /**
     * 营销定义Mapper
     */
    @Autowired
    private MarketingDefineDOMapper marketingDefineDOMapper;

    /**
     * 营销自定义Mapper
     */
    @Autowired
    private MarketingDefineMapper marketingDefineMapper;

    /**
     * 事件触发历史Mapper
     */
    @Autowired
    private EventTriggerHistoryDOMapper eventTriggerHistoryDOMapper;

    /**
     * 事件触发历史自定义Mapper
     */
    @Autowired
    private EventTriggerHistoryMapper eventTriggerHistoryMapper;

    /**
     * 营销流程节点执行记录Mapper
     */
    @Autowired
    private NodeExecuteRecordDOMapper nodeExecuteRecordDOMapper;

    /**
     * 自定义营销流程节点执行记录Mapper
     */
    @Autowired
    private NodeExecuteRecordMapper nodeExecuteRecordMapper;

    /**
     * 营销定义保存
     *
     * @param marketingDefineBO 营销定义保存参数
     */
    public Long defineSave(MarketingBO marketingDefineBO) {
        // Param转DO并插入数据库
        MarketingDefineDO marketingDefineDO = MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO);
        marketingDefineDOMapper.insert(marketingDefineDO);
        return marketingDefineDO.getId();
    }

    /**
     * 营销定义修改
     *
     * @param marketingDefineBO 营销定义修改参数
     */
    public void defineUpdate(MarketingBO marketingDefineBO) {
        // BO转DO
        MarketingDefineDO marketingDefineDO = MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO);

        // 更新到数据库
        baseMarketingDefineUpdate(marketingDefineDO, marketingDefineBO.getModifier());
    }

    /**
     * 营销定义删除
     *
     * @param marketingDefineBO 营销定义删除参数
     */
    public void defineDelete(MarketingBO marketingDefineBO) {
        // Param转DO
        MarketingDefineDO marketingProcessDO = MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO);
        marketingProcessDO.setStatus(StatusEnum.DISABLE.getState());

        // 更新到数据库
        baseMarketingDefineUpdate(marketingProcessDO, marketingDefineBO.getModifier());
    }

    /**
     * 营销流程保存
     *
     * @param marketingDefineBO 营销流程保存参数
     */
    public void processSave(MarketingBO marketingDefineBO) {
        // BO转DO
        MarketingDefineDO marketingProcessDO = MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO);

        // 更新到数据库
        baseMarketingDefineUpdate(marketingProcessDO, marketingDefineBO.getModifier());
    }

    /**
     * 营销流程启用
     *
     * @param marketingDefineBO 营销流程启用参数
     */
    public void processEnable(MarketingBO marketingDefineBO) {
        // BO转DO
        MarketingDefineDO marketingDefineDO = MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO);
        marketingDefineDO.setMarketingStatus(MarketingStatusEnum.ENABLE.getState());

        // 更新到数据库
        baseMarketingDefineUpdate(marketingDefineDO, marketingDefineBO.getModifier());
    }

    /**
     * 营销流程停用
     *
     * @param marketingDefineBO 营销流程停用参数
     */
    public void processDisable(MarketingBO marketingDefineBO) {
        // Param转DO
        MarketingDefineDO marketingDefineDO = MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO);
        marketingDefineDO.setMarketingStatus(MarketingStatusEnum.DISABLE.getState());

        // 更新到数据库
        baseMarketingDefineUpdate(marketingDefineDO, marketingDefineBO.getModifier());
    }

    /**
     * 营销定义详情查询
     *
     * @param marketingDefineQuery 营销定义详情查询参数
     */
    public MarketingDefineDTO detail(MarketingDefineQuery marketingDefineQuery) {
        // DO转DTO
        MarketingDefineDTO marketingDefineDTO = this.getMarketingDefineByPrimaryKey(marketingDefineQuery.getId());
        if ("1".equals(marketingDefineDTO.getIsAlwaysValid())) {
            marketingDefineDTO.setStarttime(null);
            marketingDefineDTO.setFinishtime(null);
        }
        return marketingDefineDTO;
    }

    /**
     * 根据ID查询营销定义
     *
     * @param id 营销定义ID
     * @return 营销定义DO
     */
    public MarketingDefineDTO getMarketingDefineByPrimaryKey(Long id) {
        // 根据id查询营销定义
        MarketingDefineDO marketingDefineDO = marketingDefineDOMapper.selectByPrimaryKey(id);
        if (marketingDefineDO == null) {
            return null;
        }
        return MarketingDefineConvertor.INSTANCE.doToDTO(marketingDefineDO);
    }

    /**
     * 公用更新方法
     *
     * @param marketingDefineDO 需要更新的营销定义DO
     * @param operator 操作人ID
     */
    private void baseMarketingDefineUpdate(MarketingDefineDO marketingDefineDO, JSONObject operator) {
        marketingDefineDO.setModifier(operator);
        marketingDefineDO.setCreator(null);
        marketingDefineDOMapper.updateByPrimaryKeySelective(marketingDefineDO);
    }

    /**
     * 营销定义列表查询
     *
     * @param query
     * @return
     */
    public PageInfo<MarketingDefineDTO> queryList(MarketingDefineListQuery query) {
        // param转do
        MarketingDefineDO marketingDefineDO = ConvertUtil.beanCopy(query, MarketingDefineDO.class);
        // 查询数据并返回
        List<MarketingDefineDO> marketingDefineDOList = this.marketingDefineMapper.queryList(marketingDefineDO);
        return MarketingDefineConvertor.INSTANCE.doPageToDTO(new PageInfo<>(marketingDefineDOList));
    }

    /**
     * 事件营销有效期和触发频率设置
     *
     * @param marketingBO
     */
    public void setAnalysis(MarketingBO marketingBO) {
        // Param转DO
        MarketingDefineDO marketingDefineDO = MarketingDefineConvertor.INSTANCE.boToDO(marketingBO);
        // 补充修改人和修改时间
        marketingDefineDO.setModifier(marketingBO.getModifier());
        marketingDefineDO.setCreator(null);
        // 修改
        // marketingDefineDOMapper.updateByPrimaryKeySelective(marketingDefineDO);
        // 原生更新方法对于NULL值无法正确更新，改用自定义更新方式
        marketingDefineMapper.setAnalysis(marketingDefineDO);

    }

    /**
     * 查询生效状态的营销定义列表
     *
     * @param merchantCode 商家CODE
     * @param marketingType 营销类型:[1]主动营销,[2]事件营销
     * @return List<MarketingDefineDTO>
     * @author PQ
     * @date 2019/4/18
     */
    public List<MarketingDefineDTO> listActiveCampaignDefines(String merchantCode, String marketingType) {
        List<MarketingDefineDO> marketingDefineDOList = marketingDefineMapper.selectActiveCampaignDefines(merchantCode,
            marketingType);
        if (CollectionUtils.isEmpty(marketingDefineDOList)) {
            return Lists.newArrayList();
        }
        else {
            return MarketingDefineConvertor.INSTANCE.doDOListToDTO(marketingDefineDOList);
        }
    }

    /**
     * 新增事件触发历史记录
     *
     * @param eventTriggerHistoryBO
     * @return Long 新增记录的ID主键值
     * @author PQ
     * @date 2019/4/19
     */
    public Long saveEventTriggerHistory(EventTriggerHistoryBO eventTriggerHistoryBO) {
        EventTriggerHistoryDO eventTriggerHistoryDO = EventTriggerHistoryConvertor.INSTANCE
            .boToDO(eventTriggerHistoryBO);
        eventTriggerHistoryDOMapper.insert(eventTriggerHistoryDO);
        return eventTriggerHistoryDO.getId();
    }

    /**
     * 事件触发统计分析查询
     *
     * @param query
     * @return
     * @throws NumberFormatException
     */
    public MarketingEventStatisticsDTO statisticsQuery(EventTriggerHistoryQuery query) {
        // 创建返回对象
        MarketingEventStatisticsDTO returnDTO = new MarketingEventStatisticsDTO();

        // query 转DO
        EventTriggerHistoryDO eventTriggerHistoryDO = ConvertUtil.beanCopy(query, EventTriggerHistoryDO.class);
        // 查询总触发次数
        Integer triggerCountAll = eventTriggerHistoryMapper.selectTriggerCountAllByMarketingId(eventTriggerHistoryDO);
        // 查询今日触发次数
        Integer triggerCountToday = eventTriggerHistoryMapper
            .selectTriggerCountTodayByMarketingId(eventTriggerHistoryDO);
        // 查询流程完成人数
        Integer processFinishedCount = eventTriggerHistoryMapper
            .selectProcessFinishedCountByMarketingId(eventTriggerHistoryDO);
        // 计算完成率
        if (triggerCountAll == 0 || processFinishedCount == 0) {
            returnDTO.setFinishedPercent("0");
        }
        else {
            Integer percent = (processFinishedCount * 100) / triggerCountAll;
            returnDTO.setFinishedPercent(percent + "");
        }

        // 返回对象
        returnDTO.setTriggerCountAll(triggerCountAll);
        returnDTO.setTriggerCountToday(triggerCountToday);
        returnDTO.setProcessFinishedCount(processFinishedCount);
        return returnDTO;

    }

    /**
     * 事件触发历史列表
     *
     * @param query
     * @return
     */
    public PageInfo<EventTriggerHistoryDTO> eventTriggerList(EventTriggerHistoryQuery query) {
        // 查询数据并返回
        List<EventTriggerHistoryDO> eventTriggerHistoryDOList = this.eventTriggerHistoryMapper
            .eventTriggerList(EventTriggerHistoryConvertor.INSTANCE.queryToDO(query));
        return EventTriggerHistoryConvertor.INSTANCE.doPageToDTO(new PageInfo<>(eventTriggerHistoryDOList));
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
        List<EventTriggerHistoryDO> eventTriggerHistoryDOList = eventTriggerHistoryMapper
            .queryTriggerHisByUserAndMarketingId(merchantCode, marketingId, memberId);
        if (CollectionUtils.isEmpty(eventTriggerHistoryDOList)) {
            return Lists.newArrayList();
        }
        return EventTriggerHistoryConvertor.INSTANCE.doDOListToDTO(eventTriggerHistoryDOList);
    }

    /**
     * 更新事件触发历史记录表
     * 
     * @param eventTriggerHistoryBO
     * @return void
     * @author PQ
     * @date 2019/6/24
     */
    public void updateEventTriggerHisByKey(EventTriggerHistoryBO eventTriggerHistoryBO) {
        eventTriggerHistoryDOMapper
            .updateByPrimaryKeySelective(EventTriggerHistoryConvertor.INSTANCE.boToDO(eventTriggerHistoryBO));
    }

    /**
     * 新增流程节点执行记录
     * 
     * @param nodeExecuteRecordBO
     * @return java.lang.Long
     * @author PQ
     * @date 2019/7/24
     */
    public Long saveNodeExecuteRecord(NodeExecuteRecordBO nodeExecuteRecordBO) {
        NodeExecuteRecordDO nodeExecuteRecordDO = NodeExecuteRecordConvertor.INSTANCE.boToDO(nodeExecuteRecordBO);
        nodeExecuteRecordDOMapper.insert(nodeExecuteRecordDO);
        return nodeExecuteRecordDO.getId();
    }

    /**
     * 营销流程执行实例查询
     * 
     * @param query
     * @return List<NodeExecuteRecordDTO>
     * @author PQ
     * @date 2019/7/24
     */
    public List<NodeExecuteRecordDTO> instanceQuery(MarketingInstanceQuery query) {
        return NodeExecuteRecordConvertor.INSTANCE.doDOListToDTO(nodeExecuteRecordMapper.selectInstance(query));
    }
}
