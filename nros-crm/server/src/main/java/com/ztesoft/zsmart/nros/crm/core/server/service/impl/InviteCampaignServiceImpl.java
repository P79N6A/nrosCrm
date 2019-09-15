package com.ztesoft.zsmart.nros.crm.core.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignRewardDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.InviteCampaignDomain;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.base.util.paas.CacheUtils;
import com.ztesoft.zsmart.nros.crm.core.client.api.InviteCampaignService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignEditDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignDeleteList;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignDeleteParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignFeedBackParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignStartOrStopParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.InviteRewardParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveInviteCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.QueryInviteCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.CampaignDomain;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CouponService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.MemberService;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动service实现类
 * @date 2019-04-10
 */
@Service
@Setter
public class InviteCampaignServiceImpl implements InviteCampaignService {

    /**
     * 活动领域
     */
    @Autowired
    private CampaignDomain campaignDomain;

    /**
     * 优惠券订阅服务service
     */
    @Autowired
    private CouponService couponService;

    /**
     * 会员订阅服务service
     */
    @Autowired
    private MemberService memberService;

    /**
     * 缓存工具类
     */
    @Autowired
    private CacheUtils cacheUtils;

    @Autowired
    private InviteCampaignDomain inviteCampaignDomain;

    /**
     * 通过名称查询邀请活动
     *
     * @param queryInviteCampaignQuery 邀请活动名称
     * @return
     */
    @Override
    public PageInfo<InviteCampaignDTO> listInviteActivity(QueryInviteCampaignQuery queryInviteCampaignQuery) {
        // 分页
        PageHelper.startPage(queryInviteCampaignQuery.getPageIndex(), queryInviteCampaignQuery.getPageSize());
        // List<InviteCampaignDTO> inviteCampaignDTOList = new ArrayList<>();
        // if (campaignDOList.isEmpty()) {
        // return new PageInfo<>(inviteCampaignDTOList);
        // }
        // inviteCampaignDTOList = ConvertUtil.listEntity2DTO(campaignDOList, InviteCampaignDTO.class);
        // PageInfo pageInfo = page.toPageInfo();
        // pageInfo.setList(campaignDTOList);
        return campaignDomain.listInviteActivityByName(queryInviteCampaignQuery);
    }

    /**
     * 创建邀请活动
     *
     * @param campaignParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createInviteCampaign(SaveInviteCampaignParam campaignParam) {
        return campaignDomain.createInviteCampaign(campaignParam);
    }

    /**
     * 更新邀请活动
     *
     * @param campaignParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateInviteCampaign(SaveInviteCampaignParam campaignParam) {
        return campaignDomain.updateInviteCampaign(campaignParam);
    }

    /**
     * 更新邀请活动信息
     *
     * @param saveInviteCampaignParam
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInviteActivity(SaveInviteCampaignParam saveInviteCampaignParam) {
        campaignDomain.updateInviteActivity(saveInviteCampaignParam);
    }

    /**
     * 删除活动 通过ID删除
     *
     * @param campaignDeleteList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInviteCampaign(CampaignDeleteList campaignDeleteList) {
        // 基本参数校验
        if (campaignDeleteList.getCampaignIds().isEmpty()) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        CampaignDeleteParam campaignDeleteParam = new CampaignDeleteParam();
        // 单个删除
        for (Long id : campaignDeleteList.getCampaignIds()) {
            campaignDeleteParam.setId(id);
            campaignDomain.deleteCampaign(campaignDeleteParam);
        }
    }

    /**
     * 邀请活动详情查询 通过活动id查询
     *
     * @param campaignId
     * @return InviteCampaignDetailDTO
     */
    @Override
    public InviteCampaignDetailDTO queryInviteCampaignDetailById(Long campaignId) {
        return campaignDomain.queryInviteCampaignDetailById(campaignId);
    }

    /**
     * 编辑邀请活动 信息查询
     *
     * @param campaignId
     * @return
     */
    @Override
    public InviteCampaignEditDTO queryEditDetailById(Long campaignId) {
        // 参数校验
        if (campaignId == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        return campaignDomain.queryEditDetailById(campaignId);
    }

    /**
     * 邀请活动 邀请明细列表查询
     *
     * @param queryInviteCampaignQuery
     * @return
     */
    @Override
    public PageInfo<InviteDetailDTO> listInviteDetail(QueryInviteCampaignQuery queryInviteCampaignQuery) {
        PageInfo<InviteDetailDTO> servicePageInfo = campaignDomain.listDetail(queryInviteCampaignQuery);
        if (servicePageInfo.getList().isEmpty()) {
            return new PageInfo<InviteDetailDTO>();
        }
        return servicePageInfo;
    }

    /**
     * 启用停用活动
     *
     * @param startOrStopParam
     * @return
     */
    @Override
    public Long startOrStopCampaign(CampaignStartOrStopParam startOrStopParam) {
        // 参数校验
        if (startOrStopParam.getId() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        return campaignDomain.startOrStopCampaign(startOrStopParam);
    }

    /**
     * 邀请活动分享
     *
     * @param campaignId
     * @return
     */
    @Override
    public InviteCampaignDTO shareCampaignById(Long campaignId) {
        return campaignDomain.shareCampaignById(campaignId);
    }

    /**
     * 活动点击量记录 活动点击
     *
     * @param campaignFeedBackParam
     * @return
     */
    @Override
    public void clickCampaign(CampaignFeedBackParam campaignFeedBackParam) {
        // 活动点击量与访问量存于redis
        Object feedBackList = cacheUtils.get("nrosCrmCampaignFeedBack");

        if (feedBackList == null) {
            List<CampaignFeedBackParam> feedBackParamList = new ArrayList<>();
            feedBackParamList.add(campaignFeedBackParam);
            cacheUtils.set("nrosCrmCampaignFeedBack", feedBackParamList, TimeUnit.MINUTES.toHours(2));
        }
        else {
            if (feedBackList instanceof ArrayList) {
                // 转List
                ((ArrayList) feedBackList).add(campaignFeedBackParam);
            }

        }
        cacheUtils.set("nrosCrmCampaignFeedBack", feedBackList, TimeUnit.MINUTES.toHours(2));

    }

    /**
     * 活动点击量记录
     *
     * @param feedBackParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addFeedBackRecord(CampaignFeedBackParam feedBackParam) {
        // 参数校验
        if (feedBackParam.getCampaignId() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        if (StringUtils.isBlank(feedBackParam.getFeedbackType())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0017");
        }
        if (feedBackParam.getShareMemberId() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0018");
        }

        return campaignDomain.addFeedBackRecord(feedBackParam);
    }

    /**
     * 邀请活动奖励发放 优惠券与积分发放
     *
     * @param rewardParam
     * @return
     */
    @Override
    public void sendReward(InviteRewardParam rewardParam) {
        // 参数校验
        if (Objects.isNull(rewardParam.getInviterId())) {
            com.ztesoft.zsmart.nros.base.exception.ExceptionHandler.publish("NROS-SBC-PROMOTION-0019");
        }
        if (Objects.isNull(rewardParam.getNewMemberId())) {
            com.ztesoft.zsmart.nros.base.exception.ExceptionHandler.publish("NROS-SBC-PROMOTION-0020");
        }
        if (Objects.isNull(rewardParam.getActivityId())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        CampaignRewardDO campaignRewardDO = inviteCampaignDomain
            .getCampaignRewardByCampaignId(rewardParam.getActivityId());
        // 邀请人发送优惠卷奖励
        if (Objects.nonNull(campaignRewardDO.getIsCompounInviter())) {
            inviteCampaignDomain.sendInviterCoupon(campaignRewardDO);
        }
        // 邀请人发送积分奖励
        if (Objects.nonNull(campaignRewardDO.getIsPointInviter())) {
            inviteCampaignDomain.sendInviterPoint(rewardParam.getInviterId(), campaignRewardDO);
        }
        // 新会员发送优惠卷奖励
        if (Objects.nonNull(campaignRewardDO.getIsCompounInvitee())) {
            inviteCampaignDomain.sendInviteeCoupon(campaignRewardDO);
        }
        // 新会员发送积分奖励
        if (Objects.nonNull(campaignRewardDO.getIsPointInvitee())) {
            inviteCampaignDomain.sendInviteePoint(rewardParam.getNewMemberId(), campaignRewardDO);
        }
    }

}
