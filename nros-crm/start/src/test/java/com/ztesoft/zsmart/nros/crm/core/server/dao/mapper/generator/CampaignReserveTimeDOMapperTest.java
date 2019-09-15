package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignReserveTimeDO;
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
public class CampaignReserveTimeDOMapperTest extends MockitoTest {

    @Mock
    private CampaignReserveTimeDOMapper campaignReserveTimeDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(campaignReserveTimeDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, campaignReserveTimeDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        CampaignReserveTimeDO campaignReserveTimeDO = getCampaignReserveTimeDO();
        Mockito.when(campaignReserveTimeDOMapper.insert(campaignReserveTimeDO)).thenReturn(1);
        Assert.assertEquals(1, campaignReserveTimeDOMapper.insert(campaignReserveTimeDO));
    }

    private CampaignReserveTimeDO getCampaignReserveTimeDO() {
        CampaignReserveTimeDO campaignReserveTimeDO = new CampaignReserveTimeDO();
        campaignReserveTimeDO.setCampaignId(1L);
        campaignReserveTimeDO.setReserveEndTime(new Date());
        campaignReserveTimeDO.setReserveStartTime(new Date());
        return campaignReserveTimeDO;
    }

    @Test
    public void testInsertSelective() {
        CampaignReserveTimeDO campaignReserveTimeDO = getCampaignReserveTimeDO();
        Mockito.when(campaignReserveTimeDOMapper.insertSelective(campaignReserveTimeDO)).thenReturn(1);
        Assert.assertEquals(1, campaignReserveTimeDOMapper.insertSelective(campaignReserveTimeDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        CampaignReserveTimeDO campaignReserveTimeDO = getCampaignReserveTimeDO();
        Mockito.when(campaignReserveTimeDOMapper.selectByPrimaryKey(1L)).thenReturn(campaignReserveTimeDO);
        Assert.assertEquals(campaignReserveTimeDO, campaignReserveTimeDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        CampaignReserveTimeDO campaignReserveTimeDO = getCampaignReserveTimeDO();
        Mockito.when(campaignReserveTimeDOMapper.updateByPrimaryKeySelective(campaignReserveTimeDO)).thenReturn(1);
        Assert.assertEquals(1, campaignReserveTimeDOMapper.updateByPrimaryKeySelective(campaignReserveTimeDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        CampaignReserveTimeDO campaignReserveTimeDO = getCampaignReserveTimeDO();
        Mockito.when(campaignReserveTimeDOMapper.updateByPrimaryKey(campaignReserveTimeDO)).thenReturn(1);
        Assert.assertEquals(1, campaignReserveTimeDOMapper.updateByPrimaryKey(campaignReserveTimeDO));
    }
}
