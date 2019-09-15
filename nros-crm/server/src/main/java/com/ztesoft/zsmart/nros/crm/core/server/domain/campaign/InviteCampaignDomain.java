package com.ztesoft.zsmart.nros.crm.core.server.domain.campaign;

import com.ztesoft.zsmart.nros.base.util.DateUtil;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.YesOrNoEnum;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignRewardDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignRewardMapper;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CouponService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.MemberService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponBatchSendParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointUpdateParams;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yuanxiaokai
 * @date 2019/7/17
 */
@Component
@Setter
public class InviteCampaignDomain {

    @Autowired
    private CouponService couponService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CampaignRewardMapper campaignRewardMapper;

    public CampaignRewardDO getCampaignRewardByCampaignId(long campaignId) {
        CampaignRewardDO param = new CampaignRewardDO();
        param.setCampaignId(campaignId);
        return campaignRewardMapper.selectByCampaignId(param);
    }

    /**
     * 邀请人发送优惠卷奖励
     * 
     * @param campaignRewardDO
     */
    public void sendInviterCoupon(CampaignRewardDO campaignRewardDO) {
        CouponBatchSendParam batchSendParam = new CouponBatchSendParam();
        batchSendParam.setCouponCode(campaignRewardDO.getCompounCodeInviter());
        batchSendParam.setReceiveType("0");
        batchSendParam.setActiveCode(String.valueOf(campaignRewardDO.getCampaignId()));
        couponService.batchSend(batchSendParam);
    }

    /**
     * 邀请人发送积分奖励
     * 
     * @param inviterId
     * @param campaignRewardDO
     */
    public void sendInviterPoint(long inviterId, CampaignRewardDO campaignRewardDO) {
        PointUpdateParams updateParams = new PointUpdateParams();
        updateParams.setMemberId(inviterId);
        if (YesOrNoEnum.NO.getValue().equals(campaignRewardDO.getPointIsPermanetInviter())) {
            updateParams
                .setEffDate(DateUtil.getEndDateByDay(new Date(), campaignRewardDO.getPointvalidAfterDaysInviter()));
        }
        updateParams.setPoint(campaignRewardDO.getPointInviter());
        updateParams.setChannel("28");
        updateParams.setDescription("邀请活动");
        memberService.updatePointRecord(updateParams);
    }

    /**
     * 新会员发送优惠卷奖励
     * 
     * @param campaignRewardDO
     */
    public void sendInviteeCoupon(CampaignRewardDO campaignRewardDO) {
        CouponBatchSendParam batchSendParam = new CouponBatchSendParam();
        batchSendParam.setCouponCode(campaignRewardDO.getCompounCodeInvitee());
        batchSendParam.setReceiveType("0");
        batchSendParam.setActiveCode(String.valueOf(campaignRewardDO.getCampaignId()));
        couponService.batchSend(batchSendParam);
    }

    /**
     * 新会员发送积分奖励
     * 
     * @param newMemberId
     * @param campaignRewardDO
     */
    public void sendInviteePoint(long newMemberId, CampaignRewardDO campaignRewardDO) {
        PointUpdateParams updateParams = new PointUpdateParams();
        updateParams.setMemberId(newMemberId);
        if (YesOrNoEnum.NO.getValue().equals(campaignRewardDO.getPointIsPermanetInvitee())) {
            updateParams
                .setEffDate(DateUtil.getEndDateByDay(new Date(), campaignRewardDO.getPointvalidAfterDaysInvitee()));
        }
        updateParams.setPoint(campaignRewardDO.getPointInvitee());
        updateParams.setChannel("28");
        updateParams.setDescription("邀请活动");
        memberService.updatePointRecord(updateParams);
    }

}
