package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignRewardDO;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动mapper
 * @date 2019-04-12
 */
public interface CampaignRewardMapper {

    /**
     * 根据活动编码查询活动奖励记录
     * @param campaignRewardDO
     * @return
     */
    CampaignRewardDO selectByCampaignId(CampaignRewardDO campaignRewardDO);

}