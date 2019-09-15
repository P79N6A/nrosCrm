package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import java.util.List;

import com.ztesoft.zsmart.nros.crm.core.client.model.query.QueryInviteCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignInListQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignServiceDO;

/**
 * @author zhou.xiaofeng
 * @description 活动明细mapper
 * @date 2019-04-13
 */
public interface CampaignServiceMapper {

    /**
     * 根据活动查询邀请活动明细
     *
     * @param campaignServiceDO
     * @return com.ztesoft.zsmart.nros.sbc.crm.dao.model.generator.CampaignServiceDO
     */
    List<CampaignServiceDO> selectInviteDetail(CampaignServiceDO campaignServiceDO);

    /**
     * 通过注册时间查询¬邀请明细
     *
     * @param inviteCampaignQuery
     * @return
     */
    List<CampaignServiceDO> selectInviteDetailByDate(QueryInviteCampaignQuery inviteCampaignQuery);

    /**
     * 根据活动id查询报名活动明细
     *
     * @param campaignId
     * @return com.ztesoft.zsmart.nros.sbc.crm.dao.model.generator.CampaignServiceDO
     */
    List<CampaignServiceDO> selectByCampaignId(Long campaignId);

    /**
     * 通过手机号查找报名活动服务
     * @param campaignServiceDO
     * @return
     */
    CampaignServiceDO selectBySignPhone(CampaignServiceDO campaignServiceDO);

    /**
     * 查询报名和签到用户列表
     * @param signInListQuery
     * @return
     */
    List<CampaignServiceDO> querySignList(SignInListQuery signInListQuery);
}