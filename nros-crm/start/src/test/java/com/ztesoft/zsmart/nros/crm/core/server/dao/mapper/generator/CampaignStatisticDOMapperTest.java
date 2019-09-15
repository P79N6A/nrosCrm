package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignStatisticDO;
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
public class CampaignStatisticDOMapperTest extends MockitoTest {

    @Mock
    private CampaignStatisticDOMapper campaignStatisticDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(campaignStatisticDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, campaignStatisticDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        CampaignStatisticDO campaignStatisticDO = getCampaignStatisticDO();
        Mockito.when(campaignStatisticDOMapper.insert(campaignStatisticDO)).thenReturn(1);
        Assert.assertEquals(1, campaignStatisticDOMapper.insert(campaignStatisticDO));
    }

    private CampaignStatisticDO getCampaignStatisticDO() {
        CampaignStatisticDO campaignStatisticDO = new CampaignStatisticDO();
        campaignStatisticDO.setClickCounter(1);
        campaignStatisticDO.setPageviewCounter(1);
        campaignStatisticDO.setStayTime("");
        campaignStatisticDO.setStayTimeCounter(1);
        campaignStatisticDO.setRegisterNum(1);
        campaignStatisticDO.setRecommandNum(1);
        campaignStatisticDO.setWxShareCounter(1);
        campaignStatisticDO.setRealAttendNum(1);
        campaignStatisticDO.setCampaignType("");
        campaignStatisticDO.setCampaignId(1L);
        campaignStatisticDO.setSignInNum(1);
        campaignStatisticDO.setSignUpNum(1);
        return campaignStatisticDO;
    }

    @Test
    public void testInsertSelective() {
        CampaignStatisticDO campaignStatisticDO = getCampaignStatisticDO();
        Mockito.when(campaignStatisticDOMapper.insertSelective(campaignStatisticDO)).thenReturn(1);
        Assert.assertEquals(1, campaignStatisticDOMapper.insertSelective(campaignStatisticDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        CampaignStatisticDO campaignStatisticDO = getCampaignStatisticDO();
        Mockito.when(campaignStatisticDOMapper.selectByPrimaryKey(1L)).thenReturn(campaignStatisticDO);
        Assert.assertEquals(campaignStatisticDO, campaignStatisticDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        CampaignStatisticDO campaignStatisticDO = getCampaignStatisticDO();
        Mockito.when(campaignStatisticDOMapper.updateByPrimaryKeySelective(campaignStatisticDO)).thenReturn(1);
        Assert.assertEquals(1, campaignStatisticDOMapper.updateByPrimaryKeySelective(campaignStatisticDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        CampaignStatisticDO campaignStatisticDO = getCampaignStatisticDO();
        Mockito.when(campaignStatisticDOMapper.updateByPrimaryKey(campaignStatisticDO)).thenReturn(1);
        Assert.assertEquals(1, campaignStatisticDOMapper.updateByPrimaryKey(campaignStatisticDO));
    }
}
