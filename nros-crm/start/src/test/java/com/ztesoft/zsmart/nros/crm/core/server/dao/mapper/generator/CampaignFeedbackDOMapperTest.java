package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignFeedbackDO;
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
public class CampaignFeedbackDOMapperTest extends MockitoTest {

    @Mock
    private CampaignFeedbackDOMapper campaignFeedbackDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(campaignFeedbackDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, campaignFeedbackDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        CampaignFeedbackDO campaignFeedbackDO = getCampaignFeedbackDO();
        Mockito.when(campaignFeedbackDOMapper.insert(campaignFeedbackDO)).thenReturn(1);
        Assert.assertEquals(1, campaignFeedbackDOMapper.insert(campaignFeedbackDO));
    }

    private CampaignFeedbackDO getCampaignFeedbackDO() {
        CampaignFeedbackDO campaignFeedbackDO = new CampaignFeedbackDO();
        campaignFeedbackDO.setFeedbackType("1");
        campaignFeedbackDO.setStartTime(new Date());
        campaignFeedbackDO.setEndTime(new Date());
        campaignFeedbackDO.setDevice("1");
        campaignFeedbackDO.setLocation("");
        campaignFeedbackDO.setChannelId(1L);
        campaignFeedbackDO.setShareMemberId(1L);
        campaignFeedbackDO.setInviterContactId(1L);
        campaignFeedbackDO.setGuideId(1L);
        campaignFeedbackDO.setWxOpenId("1");
        campaignFeedbackDO.setCampaignId(1L);
        return campaignFeedbackDO;
    }

    @Test
    public void testInsertSelective() {
        CampaignFeedbackDO campaignFeedbackDO = getCampaignFeedbackDO();
        Mockito.when(campaignFeedbackDOMapper.insertSelective(campaignFeedbackDO)).thenReturn(1);
        Assert.assertEquals(1, campaignFeedbackDOMapper.insertSelective(campaignFeedbackDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        CampaignFeedbackDO campaignFeedbackDO = getCampaignFeedbackDO();
        Mockito.when(campaignFeedbackDOMapper.selectByPrimaryKey(1L)).thenReturn(campaignFeedbackDO);
        Assert.assertEquals(campaignFeedbackDO, campaignFeedbackDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        CampaignFeedbackDO campaignFeedbackDO = getCampaignFeedbackDO();
        Mockito.when(campaignFeedbackDOMapper.updateByPrimaryKeySelective(campaignFeedbackDO)).thenReturn(1);
        Assert.assertEquals(1, campaignFeedbackDOMapper.updateByPrimaryKeySelective(campaignFeedbackDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        CampaignFeedbackDO campaignFeedbackDO = getCampaignFeedbackDO();
        Mockito.when(campaignFeedbackDOMapper.updateByPrimaryKey(campaignFeedbackDO)).thenReturn(1);
        Assert.assertEquals(1, campaignFeedbackDOMapper.updateByPrimaryKey(campaignFeedbackDO));
    }
}
