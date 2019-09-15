package com.ztesoft.zsmart.nros.crm.core.client.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTriggerHistoryDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingEventStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingInstanceDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingInstanceStaticsDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.MarketingDefineParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTriggerHistoryQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineListQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;

/**
 * 营销服务
 * 
 * @author wangzhe
 * @date 2019/4/9 13:32
 */
public interface MarketingService {

    /**
     * 营销定义保存
     * 
     * @param marketingDefineParam 营销定义保存参数
     * @return
     */
    Long defineSave(MarketingDefineParam marketingDefineParam);

    /**
     * 营销定义修改
     * 
     * @param marketingDefineParam 营销定义修改参数
     */
    void defineUpdate(MarketingDefineParam marketingDefineParam);

    /**
     * 营销定义删除
     * 
     * @param marketingDefineParam 营销定义删除参数
     */
    void defineDelete(MarketingDefineParam marketingDefineParam);

    /**
     * 营销流程保存
     * 
     * @param marketingDefineParam 营销流程保存参数
     */
    void processSave(MarketingDefineParam marketingDefineParam);

    /**
     * 营销流程启用
     * 
     * @param marketingDefineParam 营销流程启用参数
     */
    void processEnable(MarketingDefineParam marketingDefineParam);

    /**
     * 营销流程停用
     * 
     * @param marketingDefineParam 营销流程停用参数
     */
    void processDisable(MarketingDefineParam marketingDefineParam);

    /**
     * 营销定义详情查询
     * 
     * @param marketingDefineQuery 营销定义详情查询参数
     * @return
     */
    MarketingDefineDTO detail(MarketingDefineQuery marketingDefineQuery);

    /**
     * 营销定义列表查询
     * 
     * @param query
     * @return
     */
    PageInfo<MarketingDefineDTO> queryList(MarketingDefineListQuery query);

    /**
     * 事件营销有效期和触发频率设置
     * 
     * @param param
     */
    void setAnalysis(MarketingDefineParam param);

    /**
     * 事件触发统计分析查询
     * 
     * @param query
     * @return
     */
    MarketingEventStatisticsDTO statisticsQuery(EventTriggerHistoryQuery query);

    /**
     * 事件触发记录列表查询
     * 
     * @param query
     * @return
     */
    PageInfo<EventTriggerHistoryDTO> eventTriggerList(EventTriggerHistoryQuery query);

    /**
     * 新增事件触发历史记录
     * 
     * @param eventTriggerHistoryParam
     * @return Long
     * @author PQ
     * @date 2019/4/19
     */
    Long saveEventTriggerHistory(EventTriggerHistoryParam eventTriggerHistoryParam);

    /**
     * 查询生效状态的营销定义列表
     * 
     * @param merchantCode 商家编码
     * @param marketingType 营销类型:[1]主动营销,[2]事件营销
     * @return List<MarketingDefineDTO>
     * @author PQ
     * @date 2019/4/18
     */
    List<MarketingDefineDTO> listActiveCampaignDefines(String merchantCode, String marketingType);

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
    List<EventTriggerHistoryDTO> queryTriggerHisByUserAndMarketingId(String merchantCode, Long marketingId,
        Long memberId);

    /**
     * 查询营销流程执行实例
     * 
     * @param marketingDefineId
     * @return java.util.List<MarketingInstanceDTO>
     * @author PQ
     * @date 2019/7/24
     */
    List<MarketingInstanceDTO> instanceQuery(Long marketingDefineId);

    /**
     * 统计分析营销流程执行实例
     * 
     * @param marketingDefineId
     * @param marketingInstanceId
     * @return java.util.List<MarketingInstanceStaticsDTO>
     * @author PQ
     * @date 2019/7/24
     */
    List<MarketingInstanceStaticsDTO> instanceStatisticsQuery(Long marketingDefineId, String marketingInstanceId);

}
