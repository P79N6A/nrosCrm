package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author zhou.xiaofeng
 * @description 活动反馈mapper
 * @date 2019-06-13
 */
public interface CampaignFeedbackMapper {
    /**
     * 查询今日访问量
     *
     * @param campaignId
     * @return
     */
    Integer countToadyCounter(@Param("campaignId") Long campaignId);

}