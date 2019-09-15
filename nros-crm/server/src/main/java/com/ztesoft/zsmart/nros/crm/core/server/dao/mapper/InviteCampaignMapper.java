package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;

import java.util.List;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动mapper
 * @date 2019-04-10
 */
public interface InviteCampaignMapper {
    /**
     * 根据名称查找邀请活动
     * @param campaignDO
     * @return
     */
    List<CampaignDO> listInviteActivityByName(CampaignDO campaignDO);
}
