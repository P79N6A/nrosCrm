package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignStatisticDO;

/**
 * @author zhou.xiaofeng
 * @description 活动统计mapper
 * @date 2019-04-13
 */
public interface CampaignStatisticMapper {

    /**
     * 根据活动Id获取一条数据库记录
     *
     * @param campaignStatisticDO
     * @return com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignStatisticDO
     */
    CampaignStatisticDO selectByCampaignId(CampaignStatisticDO campaignStatisticDO);

    /**
     * 根据活动Id获取一条数据库记录
     *
     * @param campaignId
     * @return com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignStatisticDO
     */
    CampaignStatisticDO selectCampaignStatisticByCampaignId(Long campaignId);

    /**
     * 更新报名人数
     *
     * @param campaignId
     */
    void updateSignUpStatistic(Long campaignId);

    /**
     * 更新签到人数
     *
     * @param campaignId
     */
    void updateSignInStatistic(Long campaignId);
}