package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTriggerHistoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 事件触发记录自定义Mapper
 *
 * @author fan.chaolin
 * @date 2019/4/19
 */
public interface EventTriggerHistoryMapper {
    /**
     * 根据营销定义id查询触发事件记录
     *
     * @param marketingId
     * @return
     */

    List<EventTriggerHistoryDO> selectByMarketingId(Long marketingId);

    /**
     * 根据营销定义id查询触发总次数
     *
     * @param eventTriggerHistoryDO
     * @return
     */
    Integer selectTriggerCountAllByMarketingId(EventTriggerHistoryDO eventTriggerHistoryDO);

    /**
     * 根据营销定义id查询今日触发次数
     *
     * @param eventTriggerHistoryDO
     * @return
     */
    Integer selectTriggerCountTodayByMarketingId(EventTriggerHistoryDO eventTriggerHistoryDO);

    /**
     * 根据营销定义id查询流程完成人数
     *
     * @param eventTriggerHistoryDO
     * @return
     */
    Integer selectProcessFinishedCountByMarketingId(EventTriggerHistoryDO eventTriggerHistoryDO);

    /**
     * 事件触发历史列表
     * 
     * @param eventTriggerHistoryDO
     * @return
     */
    List<EventTriggerHistoryDO> eventTriggerList(EventTriggerHistoryDO eventTriggerHistoryDO);

    /**
     * 查询用户在指定营销定义活动的事件触发记录
     * 
     * @param merchantCode
     * @param marketingId
     * @param memberId
     * @return java.util.List<com.ztesoft.zsmart.nros.sbc.crm.dao.model.generator.EventTriggerHistoryDO>
     * @author PQ
     * @date 2019/4/29
     */
    List<EventTriggerHistoryDO> queryTriggerHisByUserAndMarketingId(@Param("merchantCode") String merchantCode,
        @Param("marketingId") Long marketingId, @Param("memberId") Long memberId);

}
