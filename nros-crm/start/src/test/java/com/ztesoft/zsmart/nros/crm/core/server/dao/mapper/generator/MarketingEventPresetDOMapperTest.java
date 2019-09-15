package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingEventPresetDO;
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
public class MarketingEventPresetDOMapperTest extends MockitoTest {

    @Mock
    private MarketingEventPresetDOMapper marketingEventPresetDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(marketingEventPresetDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, marketingEventPresetDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        MarketingEventPresetDO marketingEventPresetDO = getMarketingEventPresetDO();
        Mockito.when(marketingEventPresetDOMapper.insert(marketingEventPresetDO)).thenReturn(1);
        Assert.assertEquals(1, marketingEventPresetDOMapper.insert(marketingEventPresetDO));
    }

    private MarketingEventPresetDO getMarketingEventPresetDO() {
        MarketingEventPresetDO marketingEventPresetDO = new MarketingEventPresetDO();
        marketingEventPresetDO.setEventCode("1");
        marketingEventPresetDO.setEventFilter("1");
        marketingEventPresetDO.setEventName("1");
        marketingEventPresetDO.setFormular("1");
        return marketingEventPresetDO;
    }

    @Test
    public void testInsertSelective() {
        MarketingEventPresetDO marketingEventPresetDO = getMarketingEventPresetDO();
        Mockito.when(marketingEventPresetDOMapper.insertSelective(marketingEventPresetDO)).thenReturn(1);
        Assert.assertEquals(1, marketingEventPresetDOMapper.insertSelective(marketingEventPresetDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        MarketingEventPresetDO marketingEventPresetDO = getMarketingEventPresetDO();
        Mockito.when(marketingEventPresetDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingEventPresetDO);
        Assert.assertEquals(marketingEventPresetDO, marketingEventPresetDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        MarketingEventPresetDO marketingEventPresetDO = getMarketingEventPresetDO();
        Mockito.when(marketingEventPresetDOMapper.updateByPrimaryKeySelective(marketingEventPresetDO)).thenReturn(1);
        Assert.assertEquals(1, marketingEventPresetDOMapper.updateByPrimaryKeySelective(marketingEventPresetDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        MarketingEventPresetDO marketingEventPresetDO = getMarketingEventPresetDO();
        Mockito.when(marketingEventPresetDOMapper.updateByPrimaryKey(marketingEventPresetDO)).thenReturn(1);
        Assert.assertEquals(1, marketingEventPresetDOMapper.updateByPrimaryKey(marketingEventPresetDO));
    }
}
