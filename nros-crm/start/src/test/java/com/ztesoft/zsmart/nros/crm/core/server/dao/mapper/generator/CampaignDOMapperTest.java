package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.alibaba.fastjson.JSONArray;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yuanxiaokai
 * @date 2019/7/13
 */
public class CampaignDOMapperTest extends MockitoTest {

    @Mock
    private CampaignDOMapper campaignDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(campaignDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, campaignDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        CampaignDO campaignDO = getCampaignDO();
        Mockito.when(campaignDOMapper.insert(campaignDO)).thenReturn(1);
        Assert.assertEquals(1, campaignDOMapper.insert(campaignDO));
    }

    private CampaignDO getCampaignDO() {
        CampaignDO campaignDO = new CampaignDO();
        campaignDO.setName("测试");
        campaignDO.setCampaignType("1");
        campaignDO.setSignStartTime(new Date());
        campaignDO.setSignEndTime(new Date());
        campaignDO.setBackImg("测试");
        campaignDO.setButtonColor("测试");
        campaignDO.setButtonText("测试");
        campaignDO.setContactPhone("测试");
        campaignDO.setStartTime(new Date());
        campaignDO.setEndTime(new Date());
        campaignDO.setLinkAddress("测试");
        campaignDO.setLocation("测试");
        campaignDO.setRichPic("测试");
        campaignDO.setNecessoryInfo("测试");
        campaignDO.setRichDetail("测试");
        campaignDO.setRichDigest("测试");
        campaignDO.setViewAddress("");
        campaignDO.setWxDescription("");
        campaignDO.setWxPic("");
        campaignDO.setWxTitle("");
        campaignDO.setWxUrl("");
        campaignDO.setAttendNumControl(1);
        campaignDO.setIsAuditOpen("1");
        campaignDO.setSubmitTitle("");
        campaignDO.setSubmitDescription("");
        campaignDO.setIsSignInOpen("1");
        campaignDO.setCampaignState("1");
        campaignDO.setTbUrl("");
        campaignDO.setIsLongTermActivity("1");
        campaignDO.setIsLongTermReservation("1");
        campaignDO.setReservationSelectableWeekdays("1");
        campaignDO.setDaysAheadReservation(1);
        campaignDO.setAppid("1");
        campaignDO.setReservationSelectableStores(new JSONArray());
        return campaignDO;
    }

    @Test
    public void testInsertSelective() {
        CampaignDO campaignDO = getCampaignDO();
        Mockito.when(campaignDOMapper.insertSelective(campaignDO)).thenReturn(1);
        Assert.assertEquals(1, campaignDOMapper.insertSelective(campaignDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        CampaignDO campaignDO = getCampaignDO();
        Mockito.when(campaignDOMapper.selectByPrimaryKey(1L)).thenReturn(campaignDO);
        Assert.assertEquals(campaignDO, campaignDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        CampaignDO campaignDO = getCampaignDO();
        Mockito.when(campaignDOMapper.updateByPrimaryKeySelective(campaignDO)).thenReturn(1);
        Assert.assertEquals(1, campaignDOMapper.updateByPrimaryKeySelective(campaignDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        CampaignDO campaignDO = getCampaignDO();
        Mockito.when(campaignDOMapper.updateByPrimaryKey(campaignDO)).thenReturn(1);
        Assert.assertEquals(1, campaignDOMapper.updateByPrimaryKey(campaignDO));
    }
}
