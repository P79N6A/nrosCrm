package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingInstanceDO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yuanxiaokai
 * @date 2019/7/13
 */
public class MarketingInstanceDOMapperTest extends MockitoTest {

    @Mock
    private MarketingInstanceDOMapper marketingInstanceDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(marketingInstanceDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, marketingInstanceDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        MarketingInstanceDO marketingInstanceDO = getMarketingInstanceDO();
        Mockito.when(marketingInstanceDOMapper.insert(marketingInstanceDO)).thenReturn(1);
        Assert.assertEquals(1, marketingInstanceDOMapper.insert(marketingInstanceDO));
    }

    private MarketingInstanceDO getMarketingInstanceDO() {
        MarketingInstanceDO marketingInstanceDO = new MarketingInstanceDO();
        marketingInstanceDO.setStep(1);
        marketingInstanceDO.setExecuteTime(new Date());
        marketingInstanceDO.setResult("");
        marketingInstanceDO.setAnalysisStart(new Date());
        marketingInstanceDO.setAnalysisEnd(new Date());
        marketingInstanceDO.setContactIds("");
        marketingInstanceDO.setMarketingId(1L);
        return marketingInstanceDO;
    }

    @Test
    public void testInsertSelective() {
        MarketingInstanceDO marketingInstanceDO = getMarketingInstanceDO();
        Mockito.when(marketingInstanceDOMapper.insertSelective(marketingInstanceDO)).thenReturn(1);
        Assert.assertEquals(1, marketingInstanceDOMapper.insertSelective(marketingInstanceDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        MarketingInstanceDO marketingInstanceDO = getMarketingInstanceDO();
        Mockito.when(marketingInstanceDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingInstanceDO);
        Assert.assertEquals(marketingInstanceDO, marketingInstanceDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        MarketingInstanceDO marketingInstanceDO = getMarketingInstanceDO();
        Mockito.when(marketingInstanceDOMapper.updateByPrimaryKeySelective(marketingInstanceDO)).thenReturn(1);
        Assert.assertEquals(1, marketingInstanceDOMapper.updateByPrimaryKeySelective(marketingInstanceDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        MarketingInstanceDO marketingInstanceDO = getMarketingInstanceDO();
        Mockito.when(marketingInstanceDOMapper.updateByPrimaryKey(marketingInstanceDO)).thenReturn(1);
        Assert.assertEquals(1, marketingInstanceDOMapper.updateByPrimaryKey(marketingInstanceDO));
    }
}
