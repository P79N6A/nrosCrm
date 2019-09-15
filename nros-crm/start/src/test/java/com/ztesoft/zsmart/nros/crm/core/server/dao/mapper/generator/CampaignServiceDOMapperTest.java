package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignServiceDO;
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
public class CampaignServiceDOMapperTest extends MockitoTest {

    @Mock
    private CampaignServiceDOMapper campaignServiceDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(campaignServiceDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, campaignServiceDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        CampaignServiceDO campaignServiceDO = getCampaignServiceDO();
        Mockito.when(campaignServiceDOMapper.insert(campaignServiceDO)).thenReturn(1);
        Assert.assertEquals(1, campaignServiceDOMapper.insert(campaignServiceDO));
    }

    private CampaignServiceDO getCampaignServiceDO() {
        CampaignServiceDO campaignServiceDO = new CampaignServiceDO();
        campaignServiceDO.setInvitorPhone("");
        campaignServiceDO.setRegisterPhone("");
        campaignServiceDO.setSignPhone("");
        campaignServiceDO.setReservationStoreId(1L);
        campaignServiceDO.setIsNew("1");
        campaignServiceDO.setAuditStatus("");
        campaignServiceDO.setIsSignIn("");
        campaignServiceDO.setSignInCode("");
        campaignServiceDO.setSignTime("");
        campaignServiceDO.setSignTime("");
        campaignServiceDO.setRegisterTime(new Date());
        campaignServiceDO.setIsReward("");
        campaignServiceDO.setReserveTimeId(1L);
        campaignServiceDO.setCampaignId(1L);
        campaignServiceDO.setSignName("");
        return campaignServiceDO;
    }

    @Test
    public void testInsertSelective() {
        CampaignServiceDO campaignServiceDO = getCampaignServiceDO();
        Mockito.when(campaignServiceDOMapper.insertSelective(campaignServiceDO)).thenReturn(1);
        Assert.assertEquals(1, campaignServiceDOMapper.insertSelective(campaignServiceDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        CampaignServiceDO campaignServiceDO = getCampaignServiceDO();
        Mockito.when(campaignServiceDOMapper.selectByPrimaryKey(1L)).thenReturn(campaignServiceDO);
        Assert.assertEquals(campaignServiceDO, campaignServiceDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        CampaignServiceDO campaignServiceDO = getCampaignServiceDO();
        Mockito.when(campaignServiceDOMapper.updateByPrimaryKeySelective(campaignServiceDO)).thenReturn(1);
        Assert.assertEquals(1, campaignServiceDOMapper.updateByPrimaryKeySelective(campaignServiceDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        CampaignServiceDO campaignServiceDO = getCampaignServiceDO();
        Mockito.when(campaignServiceDOMapper.updateByPrimaryKey(campaignServiceDO)).thenReturn(1);
        Assert.assertEquals(1, campaignServiceDOMapper.updateByPrimaryKey(campaignServiceDO));
    }
}
