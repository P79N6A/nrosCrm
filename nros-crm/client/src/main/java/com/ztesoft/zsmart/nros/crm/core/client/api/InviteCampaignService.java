package com.ztesoft.zsmart.nros.crm.core.client.api;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignEditDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignDeleteList;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignFeedBackParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignStartOrStopParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.InviteRewardParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveInviteCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.QueryInviteCampaignQuery;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动service
 * @date 2019-04-10
 */
public interface InviteCampaignService {

    /**
     * 通过名称查询邀请活动
     *
     * @param queryInviteCampaignQuery
     * @return PageInfo<InviteCampaignDTO> 邀请活动DTO列表
     */
    PageInfo<InviteCampaignDTO> listInviteActivity(QueryInviteCampaignQuery queryInviteCampaignQuery);

    /**
     * 创建邀请活动
     *
     * @param campaignParam
     * @return
     */
    Long createInviteCampaign(SaveInviteCampaignParam campaignParam);

    /**
     * 更新邀请活动
     *
     * @param campaignParam
     * @return
     */
    Long updateInviteCampaign(SaveInviteCampaignParam campaignParam);

    /**
     * 更新邀请活动信息
     *
     * @param saveInviteCampaignParam
     */
    void updateInviteActivity(SaveInviteCampaignParam saveInviteCampaignParam);


    /**
     * 邀请活动详情查询  通过活动id查询
     *
     * @param campaignId
     * @return InviteCampaignDetailDTO
     */
    InviteCampaignDetailDTO queryInviteCampaignDetailById(Long campaignId);

    /**
     * 编辑邀请活动 信息查询
     *
     * @param campaignId
     * @return
     */
    InviteCampaignEditDTO queryEditDetailById(Long campaignId);

    /**
     * 邀请活动 邀请明细列表查询
     *
     * @param queryInviteCampaignQuery
     * @return PageInfo<InviteDetailDTO> 邀请明细DTO列表
     */
    PageInfo<InviteDetailDTO> listInviteDetail(QueryInviteCampaignQuery queryInviteCampaignQuery);

    /**
     * 删除邀请活动
     *
     * @param campaignDeleteList
     * @return
     */
    void deleteInviteCampaign(CampaignDeleteList campaignDeleteList);

    /**
     * 启用停用活动
     *
     * @param startOrStopParam
     * @return
     */
    Long startOrStopCampaign(CampaignStartOrStopParam startOrStopParam);

    /**
     * 邀请活动奖励发放
     *
     * @param rewardParam
     * @return
     */
    void sendReward(InviteRewardParam rewardParam);

    /**
     * 邀请活动分享
     *
     * @param campaignId
     * @return
     */
    InviteCampaignDTO shareCampaignById(Long campaignId);

    /**
     * 活动点击量记录  活动点击触发
     *
     * @param campaignFeedBackParam
     */
    void clickCampaign(CampaignFeedBackParam campaignFeedBackParam);

    /**
     * 活动点击记录
     *
     * @param campaignFeedBackParam
     * @return
     */
    Long addFeedBackRecord(CampaignFeedBackParam campaignFeedBackParam);

}
