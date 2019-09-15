package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.paas.CacheUtils;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.*;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.QueryInviteCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignRewardDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignServiceDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignStatisticDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignFeedbackMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignRewardMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignServiceMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignStatisticMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.InviteCampaignMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignRewardDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.InviteCampaignDomain;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.CouponServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.MemberServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.CouponProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.MemberProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponBatchSendParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointUpdateParams;
import com.ztesoft.zsmart.nros.crm.core.server.repository.CampaignRepository;
import com.ztesoft.zsmart.nros.crm.core.server.service.impl.InviteCampaignServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author yuanxiaokai
 * @date 2019/7/10
 */
public class InviteCampaignControllerTest extends MockitoTest {

    @Autowired
    private InviteCampaignController inviteCampaignController;

    @Autowired
    @InjectMocks
    private CampaignRepository campaignRepository;

    @Mock
    private InviteCampaignMapper inviteCampaignMapper;

    @Mock
    private CampaignDOMapper campaignDOMapper;

    @Mock
    private CampaignRewardDOMapper campaignRewardDOMapper;

    @Mock
    private CampaignRewardMapper campaignRewardMapper;

    @Mock
    private CampaignStatisticMapper campaignStatisticMapper;

    @Mock
    private CampaignServiceMapper campaignServiceMapper;

    @Mock
    private CampaignFeedbackMapper campaignFeedbackMapper;

    @Autowired
    @InjectMocks
    private CouponServiceImpl couponService;

    @Mock
    private CouponProxy couponProxy;

    @Autowired
    @InjectMocks
    private InviteCampaignDomain inviteCampaignDomain;

    @Autowired
    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberProxy memberProxy;

    @Autowired
    @InjectMocks
    private InviteCampaignServiceImpl inviteCampaignService;

    @Mock
    private CacheUtils cacheUtils;

    @Test
    public void testListInviteCampaignByName() {
        QueryInviteCampaignQuery queryInviteCampaignQuery = new QueryInviteCampaignQuery();
        queryInviteCampaignQuery.setPageIndex(1);
        queryInviteCampaignQuery.setPageSize(10);
        CampaignDO campaignDO = new CampaignDO();
        campaignDO.setId(1L);
        List<CampaignDO> campaignDOList = Lists.newArrayList(campaignDO);
        Mockito.when(inviteCampaignMapper.listInviteActivityByName(any(CampaignDO.class))).thenReturn(campaignDOList);
        Assert.assertNotNull(inviteCampaignController.listInviteCampaignByName(queryInviteCampaignQuery).getData());
    }

    @Test
    public void testCreateInviteCampaign() {
        SaveInviteCampaignParam saveInviteCampaignParam = new SaveInviteCampaignParam();
        saveInviteCampaignParam.setName("单元测试");
        saveInviteCampaignParam.setId(1L);
        saveInviteCampaignParam.setIsLongTermActivity("1");
        saveInviteCampaignParam.setRichDetail("单元测试");
        Mockito.when(campaignDOMapper.insert(any(CampaignDO.class))).thenReturn(1);
        Mockito.when(campaignRewardDOMapper.insert(any(CampaignRewardDO.class))).thenReturn(1);
        Assert.assertEquals(1L, inviteCampaignController.createInviteCampaign(saveInviteCampaignParam).getData());
    }

    @Test
    public void testUpdateInviteCampaign() {
        SaveInviteCampaignParam saveInviteCampaignParam = new SaveInviteCampaignParam();
        saveInviteCampaignParam.setName("单元测试");
        saveInviteCampaignParam.setIsLongTermActivity("1");
        saveInviteCampaignParam.setRichDetail("单元测试");
        Mockito.when(campaignRewardMapper.selectByCampaignId(any(CampaignRewardDO.class)))
            .thenReturn(new CampaignRewardDO());
        Mockito.when(campaignRewardDOMapper.updateByPrimaryKeySelective(any(CampaignRewardDO.class))).thenReturn(1);
        Assert.assertEquals(true, inviteCampaignController.updateInviteCampaign(saveInviteCampaignParam).getSuccess());
    }

    @Test
    public void testQueryInviteCampaignDetailById() {
        Mockito.when(campaignDOMapper.selectByPrimaryKey(any())).thenReturn(new CampaignDO());
        Mockito.when(campaignStatisticMapper.selectByCampaignId(any(CampaignStatisticDO.class)))
            .thenReturn(new CampaignStatisticDO());
        Mockito.when(campaignServiceMapper.selectInviteDetail(any(CampaignServiceDO.class)))
            .thenReturn(new ArrayList<>());
        Mockito.when(campaignFeedbackMapper.countToadyCounter(any())).thenReturn(1);
        Assert.assertNotNull(inviteCampaignController.queryInviteCampaignDetailById(36L).getData());
    }

    @Test
    public void testQueryEditDetailById() {
        Mockito.when(campaignDOMapper.selectByPrimaryKey(35L)).thenReturn(new CampaignDO());
        Mockito.when(campaignRewardMapper.selectByCampaignId(any(CampaignRewardDO.class)))
            .thenReturn(new CampaignRewardDO());
        Assert.assertNotNull(inviteCampaignController.queryEditDetailById(35L).getData());
    }

    @Test
    public void testListInviteDetailByCampaignId() {
        QueryInviteCampaignQuery queryInviteCampaignQuery = new QueryInviteCampaignQuery();
        queryInviteCampaignQuery.setPageSize(10);
        queryInviteCampaignQuery.setPageIndex(1);
        queryInviteCampaignQuery.setCampaignId(35L);
        List<CampaignServiceDO> campaignServiceDOList = Lists.newArrayList(new CampaignServiceDO());
        Mockito.when(campaignServiceMapper.selectInviteDetailByDate(queryInviteCampaignQuery))
            .thenReturn(campaignServiceDOList);
        Assert.assertNotNull(inviteCampaignController.listInviteDetailByCampaignId(queryInviteCampaignQuery).getData());
    }

    @Test
    public void testSendReward() {
        InviteRewardParam rewardParam = new InviteRewardParam();
        try {
            inviteCampaignController.sendReward(rewardParam);
        }
        catch (BusiException e) {
            Assert.assertEquals("NROS-SBC-PROMOTION-0019", e.getErrorCode());
        }
        rewardParam.setInviterId(1L);
        try {
            inviteCampaignController.sendReward(rewardParam);
        }
        catch (BusiException e) {
            Assert.assertEquals("NROS-SBC-PROMOTION-0020", e.getErrorCode());
        }
        rewardParam.setNewMemberId(2L);
        try {
            inviteCampaignController.sendReward(rewardParam);
        }
        catch (BusiException e) {
            Assert.assertEquals("NROS-SBC-PROMOTION-0014", e.getErrorCode());
        }
        rewardParam.setActivityId(1L);
        CampaignRewardDO campaignRewardDO = new CampaignRewardDO();
        campaignRewardDO.setIsCompounInviter("1");
        campaignRewardDO.setIsCompounInvitee("1");
        campaignRewardDO.setIsPointInviter("1");
        campaignRewardDO.setIsPointInvitee("1");
        Mockito.when(campaignRewardMapper.selectByCampaignId(any(CampaignRewardDO.class))).thenReturn(campaignRewardDO);
        Mockito.when(couponProxy.batchSend(any(CouponBatchSendParam.class))).thenReturn(new ResponseMsg());
        Mockito.when(memberProxy.savePointRecord(any(PointUpdateParams.class))).thenReturn(new ResponseMsg<>());
        Assert.assertEquals(true, inviteCampaignController.sendReward(rewardParam).getSuccess());
    }

    @Test
    public void testDeleteInviteCampaign() {
        CampaignDeleteList campaignDeleteList = new CampaignDeleteList();
        List<Long> campaignIds = new ArrayList<>(2);
        campaignIds.add(1L);
        campaignIds.add(2L);
        campaignDeleteList.setCampaignIds(campaignIds);
        inviteCampaignController.deleteInviteCampaign(campaignDeleteList);
    }

    @Test
    public void testStartOrStopCampaign() {
        CampaignStartOrStopParam campaignStartOrStopParam = new CampaignStartOrStopParam();
        campaignStartOrStopParam.setId(1L);
        campaignStartOrStopParam.setCampaignState("t");

        Mockito.when(campaignDOMapper.updateByPrimaryKeySelective(any(CampaignDO.class))).thenReturn(1);
        Mockito.when(campaignRewardMapper.selectByCampaignId(any(CampaignRewardDO.class)))
            .thenReturn(new CampaignRewardDO());
        Mockito.when(campaignRewardDOMapper.updateByPrimaryKeySelective(any(CampaignRewardDO.class))).thenReturn(1);
        Assert.assertEquals(1L, inviteCampaignController.startOrStopCampaign(campaignStartOrStopParam).getData());
    }

    @Test
    public void testCampaignClickerStatistics() {
        CampaignFeedBackParam campaignFeedBackParam = new CampaignFeedBackParam();
        Mockito.when(cacheUtils.get("nrosCrmCampaignFeedBack")).thenReturn(new ArrayList<>());
        Mockito.when(cacheUtils.set(any(String.class), any(ArrayList.class), any(Long.class))).thenReturn(true);
        inviteCampaignController.campaignClickerStatistics(campaignFeedBackParam);
        Mockito.when(cacheUtils.get("nrosCrmCampaignFeedBack")).thenReturn(null);
        Assert.assertEquals(true,
            inviteCampaignController.campaignClickerStatistics(campaignFeedBackParam).getSuccess());
    }
}
