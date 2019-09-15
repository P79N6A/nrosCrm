package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignRewardDO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanxiaokai
 * @date 2019/7/13
 */
public class CampaignRewardDOMapperTest extends MockitoTest {

    @Mock
    private CampaignRewardDOMapper campaignRewardDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(campaignRewardDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, campaignRewardDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        CampaignRewardDO campaignRewardDO = getCampaignRewardDO();
        Mockito.when(campaignRewardDOMapper.insert(campaignRewardDO)).thenReturn(1);
        Assert.assertEquals(1, campaignRewardDOMapper.insert(campaignRewardDO));
    }

    private CampaignRewardDO getCampaignRewardDO() {
        CampaignRewardDO campaignRewardDO = new CampaignRewardDO();
        campaignRewardDO.setIsCompounInviter("1");
        campaignRewardDO.setCompounCodeInviter("");
        campaignRewardDO.setIsPointInviter("1");
        campaignRewardDO.setPointInviter(1);
        campaignRewardDO.setPointIsPermanetInviter("");
        campaignRewardDO.setPointvalidAfterDaysInviter(1);
        campaignRewardDO.setIsOtherInviter("1");
        campaignRewardDO.setOtherInviter("");
        campaignRewardDO.setIsCompounInvitee("");
        campaignRewardDO.setCompounCodeInvitee("");
        campaignRewardDO.setIsPointInvitee("");
        campaignRewardDO.setPointInvitee(1);
        campaignRewardDO.setPointIsPermanetInvitee("");
        campaignRewardDO.setPointvalidAfterDaysInvitee(1);
        campaignRewardDO.setIsOtherInvitee("");
        campaignRewardDO.setOtherInvitee("");
        campaignRewardDO.setCampaignId(1L);
        return campaignRewardDO;
    }

    @Test
    public void testInsertSelective() {
        CampaignRewardDO campaignRewardDO = getCampaignRewardDO();
        Mockito.when(campaignRewardDOMapper.insertSelective(campaignRewardDO)).thenReturn(1);
        Assert.assertEquals(1, campaignRewardDOMapper.insertSelective(campaignRewardDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        CampaignRewardDO campaignRewardDO = getCampaignRewardDO();
        Mockito.when(campaignRewardDOMapper.selectByPrimaryKey(1L)).thenReturn(campaignRewardDO);
        Assert.assertEquals(campaignRewardDO, campaignRewardDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        CampaignRewardDO campaignRewardDO = getCampaignRewardDO();
        Mockito.when(campaignRewardDOMapper.updateByPrimaryKeySelective(campaignRewardDO)).thenReturn(1);
        Assert.assertEquals(1, campaignRewardDOMapper.updateByPrimaryKeySelective(campaignRewardDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        CampaignRewardDO campaignRewardDO = getCampaignRewardDO();
        Mockito.when(campaignRewardDOMapper.updateByPrimaryKey(campaignRewardDO)).thenReturn(1);
        Assert.assertEquals(1, campaignRewardDOMapper.updateByPrimaryKey(campaignRewardDO));
    }
}
