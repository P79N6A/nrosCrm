package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingActionPresetDO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanxiaokai
 * @date 2019/7/13
 */
public class MarketingActionPresetDOMapperTest extends MockitoTest {

    @Mock
    private MarketingActionPresetDOMapper marketingActionPresetDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(marketingActionPresetDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, marketingActionPresetDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        MarketingActionPresetDO marketingActionPresetDO = getMarketingActionPresetDO();
        Mockito.when(marketingActionPresetDOMapper.insert(marketingActionPresetDO)).thenReturn(1);
        Assert.assertEquals(1, marketingActionPresetDOMapper.insert(marketingActionPresetDO));
    }

    private MarketingActionPresetDO getMarketingActionPresetDO() {
        MarketingActionPresetDO marketingActionPresetDO = new MarketingActionPresetDO();
        marketingActionPresetDO.setActionName("1");
        marketingActionPresetDO.setActionType("1");
        marketingActionPresetDO.setClassName("1");
        return marketingActionPresetDO;
    }

    @Test
    public void testInsertSelective() {
        MarketingActionPresetDO marketingActionPresetDO = getMarketingActionPresetDO();
        Mockito.when(marketingActionPresetDOMapper.insertSelective(marketingActionPresetDO)).thenReturn(1);
        Assert.assertEquals(1, marketingActionPresetDOMapper.insertSelective(marketingActionPresetDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        MarketingActionPresetDO marketingActionPresetDO = getMarketingActionPresetDO();
        Mockito.when(marketingActionPresetDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingActionPresetDO);
        Assert.assertEquals(marketingActionPresetDO, marketingActionPresetDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        MarketingActionPresetDO marketingActionPresetDO = getMarketingActionPresetDO();
        Mockito.when(marketingActionPresetDOMapper.updateByPrimaryKeySelective(marketingActionPresetDO)).thenReturn(1);
        Assert.assertEquals(1, marketingActionPresetDOMapper.updateByPrimaryKeySelective(marketingActionPresetDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        MarketingActionPresetDO marketingActionPresetDO = getMarketingActionPresetDO();
        Mockito.when(marketingActionPresetDOMapper.updateByPrimaryKey(marketingActionPresetDO)).thenReturn(1);
        Assert.assertEquals(1, marketingActionPresetDOMapper.updateByPrimaryKey(marketingActionPresetDO));
    }
}
