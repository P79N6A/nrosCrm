package com.ztesoft.zsmart.nros.crm.core.server.service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignEditDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignDeleteList;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignFeedBackParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.InviteRewardParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.QueryInviteCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.api.InviteCampaignService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveInviteCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.YesOrNoEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动单元测试
 * @date 2019-06-18
 */
@Slf4j
public class InviteCampaignTest extends MockitoTest {

    @Autowired
    private InviteCampaignService inviteCampaignService;

    /**
     * 邀请活动列表查询方法测试
     */
    @Test
    public void listInviteActivityTest() {
        log.info("inviteCampaignService listInviteActivityTest start ");
        try {
            System.out.println("test running");

            QueryInviteCampaignQuery inviteCampaignParam = new QueryInviteCampaignQuery();
            inviteCampaignParam.setName("测试活动");
            PageInfo<InviteCampaignDTO> result = inviteCampaignService.listInviteActivity(inviteCampaignParam);

            log.info("inviteCampaignService listInviteActivityTest result ->. " + JSONArray.toJSON(result));
        } catch (Exception e) {
            log.error("inviteCampaignService listInviteActivityTest fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService listInviteActivityTest end ");
    }

    /**
     * 创建邀请活动方法测试
     */
    @Test
    public void saveInviteActivityTest() {
        log.info("inviteCampaignService createInviteCampaign start ");
        try {
            System.out.println("test running");
            for (int i = 0; i < 5; i++) {
                SaveInviteCampaignParam saveInviteCampaignParam = new SaveInviteCampaignParam();

                saveInviteCampaignParam.setName("测试活动修改");
                saveInviteCampaignParam.setIsLongTermActivity("1");
                saveInviteCampaignParam.setRichDetail("邀请满3人");
                saveInviteCampaignParam.setIsPointInviter("1");
                saveInviteCampaignParam.setPointInviter(50);
                saveInviteCampaignParam.setPointIsPermanetInviter("1");
                saveInviteCampaignParam.setIsPointInvitee("0");
                saveInviteCampaignParam.setCompounCodeInvitee("100101");
                inviteCampaignService.createInviteCampaign(saveInviteCampaignParam);
            }

        } catch (Exception e) {
            log.error("inviteCampaignService createInviteCampaign fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService createInviteCampaign end ");

    }

    /**
     * 编辑邀请活动方法测试
     */
    @Test
    public void updateInviteActivityTest() {
        log.info("inviteCampaignService updateInviteActivity start ");
        try {
            System.out.println("test running");
            SaveInviteCampaignParam saveInviteCampaignParam = new SaveInviteCampaignParam();
            saveInviteCampaignParam.setId(16L);
            saveInviteCampaignParam.setPointInviter(666);
            saveInviteCampaignParam.setName("测试活动更新");
            saveInviteCampaignParam.setIsLongTermActivity("1");
            saveInviteCampaignParam.setRichDetail("邀请满3人送积分");
            inviteCampaignService.updateInviteActivity(saveInviteCampaignParam);

        } catch (Exception e) {
            log.error("inviteCampaignService updateInviteActivity fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService updateInviteActivity end ");

    }

    /**
     * 邀请活动详情查询方法测试
     */
    @Test
    public void queryInviteCampaignDetailByIdTest() {
        log.info("inviteCampaignService queryInviteCampaignDetailByIdTest start ");
        try {
            System.out.println("test running");
            Long campaignId = Long.valueOf(6);
            InviteCampaignDetailDTO campaignDetailDTO = inviteCampaignService.queryInviteCampaignDetailById(campaignId);
            log.info("inviteCampaignService queryInviteCampaignDetailByIdTest result ->. " + JSONArray.toJSON(campaignDetailDTO));
        } catch (Exception e) {
            log.error("inviteCampaignService queryInviteCampaignDetailByIdTest fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService queryInviteCampaignDetailByIdTest end ");
    }


    /**
     * 邀请明细列表查询方法测试
     */
    @Test
    public void listInviteDetailByCampaignIdTest() {
        log.info("inviteCampaignService listInviteDetailByCampaignIdTest start ");
        try {
            System.out.println("test running");
            QueryInviteCampaignQuery queryInviteCampaignQuery = new QueryInviteCampaignQuery();
            queryInviteCampaignQuery.setCampaignId(Long.valueOf(6));
            PageInfo<InviteDetailDTO> pageInfo = inviteCampaignService.listInviteDetail(queryInviteCampaignQuery);
            log.info("inviteCampaignService listInviteDetailByCampaignIdTest result ->. " + JSONArray.toJSON(pageInfo));
        } catch (Exception e) {
            log.error("inviteCampaignService listInviteDetailByCampaignIdTest fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService listInviteDetailByCampaignIdTest end ");

    }

    /**
     * 编辑前查询方法测试
     */
    @Test
    public void queryEditDetailByIdTest() {
        log.info("inviteCampaignService queryEditDetailByIdTest start");
        try {
            System.out.println("test running");
            Long campaignId = 6L;
            InviteCampaignEditDTO inviteCampaignEditDTO = inviteCampaignService.queryEditDetailById(campaignId);
            log.info("inviteCampaignService queryEditDetailByIdTest result ->. " + JSONArray.toJSON(inviteCampaignEditDTO));
        } catch (Exception e) {
            log.error("inviteCampaignService queryEditDetailByIdTest fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService listInviteDetailByCampaignIdTest end ");
    }

    /**
     * 邀请活动删除方法测试
     */
    @Test
    public void deleteInviteCampaignTest() {

        log.info("inviteCampaignService deleteInviteCampaignTest start");
        try {
            System.out.println("test running");
            Long campaignId = 28L;
            List<Long> campaignIds = new ArrayList<>();
            campaignIds.add(campaignId);
            CampaignDeleteList deleteList = new CampaignDeleteList();
            deleteList.setCampaignIds(campaignIds);
            inviteCampaignService.deleteInviteCampaign(deleteList);
            log.info("inviteCampaignService deleteInviteCampaignTest result ->. " + "success");
        } catch (Exception e) {
            log.error("inviteCampaignService deleteInviteCampaignTest fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService deleteInviteCampaignTest end ");
    }

    /**
     * 启用停用测试
     */
    @Test
    public void startOrStopCampaign() {
        log.info("inviteCampaignService startOrStopCampaign start");
        try {
            System.out.println("test running");
            Long campaignId = 6L;
            InviteCampaignEditDTO inviteCampaignEditDTO = inviteCampaignService.queryEditDetailById(campaignId);
            log.info("inviteCampaignService startOrStopCampaign result ->. " + JSONArray.toJSON(inviteCampaignEditDTO));
        } catch (Exception e) {
            log.error("inviteCampaignService startOrStopCampaign fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService startOrStopCampaign end ");

    }

    /**
     * 活动点击方法测试
     */
    @Test
    public void clickCampaignTest() {

        log.info("inviteCampaignService clickCampaignTest start");
        try {
            System.out.println("test running");

            CampaignFeedBackParam feedBackParam = new CampaignFeedBackParam();
            feedBackParam.setCampaignId(6L);
            feedBackParam.setFeedbackType("1");
            feedBackParam.setStartTime(new Date());
//        feedBackParam.setEndTime(new Date()+10);
            feedBackParam.setDevice("iPhone X");
            feedBackParam.setLocation("Changsha Hunan Province China");
            feedBackParam.setChannelId(1001L);
            feedBackParam.setShareMemberId(1001L);
            feedBackParam.setInviterContactId(1001L);
            feedBackParam.setGuideId(1001L);
            feedBackParam.setWxOpenId("1001");
            inviteCampaignService.clickCampaign(feedBackParam);
            log.info("inviteCampaignService clickCampaignTest result ->. ");
        } catch (Exception e) {
            log.error("inviteCampaignService clickCampaignTest fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService clickCampaignTest end ");
    }

    /**
     * 活动点击量记录 测试
     */
    @Test
    public void addFeedBackRecordTest() {
        log.info("inviteCampaignService addFeedBackRecordTest start");
        try {
            System.out.println("test running");
            CampaignFeedBackParam feedBackParam = new CampaignFeedBackParam();
            feedBackParam.setCampaignId(6L);
            feedBackParam.setFeedbackType("1");
            feedBackParam.setStartTime(new Date());
//        feedBackParam.setEndTime(new Date()+10);
            feedBackParam.setDevice("iPhone X");
            feedBackParam.setLocation("Changsha Hunan Province China");
            feedBackParam.setChannelId(1001L);
            feedBackParam.setShareMemberId(1001L);
            feedBackParam.setInviterContactId(1001L);
            feedBackParam.setGuideId(1001L);
            feedBackParam.setWxOpenId("1001");
            Long result = inviteCampaignService.addFeedBackRecord(feedBackParam);
            log.info("inviteCampaignService addFeedBackRecordTest result ->. " + result);
        } catch (Exception e) {
            log.error("inviteCampaignService addFeedBackRecordTest fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService addFeedBackRecordTest end ");
    }

}
