package com.ztesoft.zsmart.nros.crm.core.server.domain.campaign;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignRewardDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignRewardMapper;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CouponService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.MemberService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponBatchSendParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointUpdateParams;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import static org.mockito.ArgumentMatchers.any;

/**
 * @author yuanxiaokai
 * @date 2019/7/18
 */
public class InviteCampaignDomainTest extends MockitoTest {

    @Autowired
    @InjectMocks
    private InviteCampaignDomain inviteCampaignDomain;

    @Mock
    private CouponService couponService;

    @Mock
    private MemberService memberService;

    @Mock
    private CampaignRewardMapper campaignRewardMapper;

    @Test
    public void testGetCampaignRewardByCampaignId() {
        CampaignRewardDO param = new CampaignRewardDO();
        param.setCampaignId(1L);
        CampaignRewardDO campaignRewardDO = new CampaignRewardDO();
        Mockito.when(campaignRewardMapper.selectByCampaignId(param)).thenReturn(campaignRewardDO);
        Assert.assertEquals(campaignRewardDO, inviteCampaignDomain.getCampaignRewardByCampaignId(1L));
    }

    @Test
    public void testSendInviterCoupon() {
        CampaignRewardDO campaignRewardDO = new CampaignRewardDO();
        campaignRewardDO.setCompounCodeInviter("1");
        CouponBatchSendParam batchSendParam = new CouponBatchSendParam();
        batchSendParam.setCouponCode(campaignRewardDO.getCompounCodeInviter());
        batchSendParam.setReceiveType("0");
        batchSendParam.setActiveCode(String.valueOf(campaignRewardDO.getCampaignId()));
        Mockito.when(couponService.batchSend(batchSendParam)).thenReturn(new ResponseMsg());
        inviteCampaignDomain.sendInviterCoupon(campaignRewardDO);
    }

    @Test
    public void testSendInviterPoint() {
        CampaignRewardDO campaignRewardDO = new CampaignRewardDO();
        campaignRewardDO.setPointIsPermanetInviter("0");
        campaignRewardDO.setPointvalidAfterDaysInviter(10);
        Mockito.when(memberService.updatePointRecord(any(PointUpdateParams.class))).thenReturn(new ResponseMsg<>());
        inviteCampaignDomain.sendInviterPoint(1L, campaignRewardDO);
    }

    @Test
    public void sendInviteeCoupon() {
        CampaignRewardDO campaignRewardDO = new CampaignRewardDO();
        campaignRewardDO.setCampaignId(1L);
        campaignRewardDO.setCompounCodeInvitee("1");
        campaignRewardDO.setPointIsPermanetInvitee("0");
        campaignRewardDO.setPointvalidAfterDaysInvitee(10);
        CouponBatchSendParam batchSendParam = new CouponBatchSendParam();
        batchSendParam.setCouponCode(campaignRewardDO.getCompounCodeInvitee());
        batchSendParam.setReceiveType("0");
        batchSendParam.setActiveCode(String.valueOf(campaignRewardDO.getCampaignId()));
        Mockito.when(couponService.batchSend(batchSendParam)).thenReturn(new ResponseMsg());
        inviteCampaignDomain.sendInviteeCoupon(campaignRewardDO);
    }

    @Test
    public void sendInviteePoint() {
        CampaignRewardDO campaignRewardDO = new CampaignRewardDO();
        campaignRewardDO.setCampaignId(1L);
        campaignRewardDO.setCompounCodeInvitee("1");
        campaignRewardDO.setPointIsPermanetInvitee("0");
        campaignRewardDO.setPointvalidAfterDaysInvitee(10);
        Mockito.when(memberService.updatePointRecord(any(PointUpdateParams.class))).thenReturn(new ResponseMsg<>());
        inviteCampaignDomain.sendInviteePoint(1, campaignRewardDO);
    }
}
