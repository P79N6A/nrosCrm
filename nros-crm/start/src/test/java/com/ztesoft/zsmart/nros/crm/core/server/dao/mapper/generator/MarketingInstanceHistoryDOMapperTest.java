package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingInstanceHistoryDO;
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
public class MarketingInstanceHistoryDOMapperTest extends MockitoTest {

    @Mock
    private MarketingInstanceHistoryDOMapper marketingInstanceHistoryDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(marketingInstanceHistoryDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, marketingInstanceHistoryDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        MarketingInstanceHistoryDO marketingInstanceHistoryDO = getMarketingInstanceHistoryDO();
        Mockito.when(marketingInstanceHistoryDOMapper.insert(marketingInstanceHistoryDO)).thenReturn(1);
        Assert.assertEquals(1, marketingInstanceHistoryDOMapper.insert(marketingInstanceHistoryDO));
    }

    private MarketingInstanceHistoryDO getMarketingInstanceHistoryDO() {
        MarketingInstanceHistoryDO marketingInstanceHistoryDO = new MarketingInstanceHistoryDO();
        marketingInstanceHistoryDO.setStep(1);
        marketingInstanceHistoryDO.setExecuteTime(new Date());
        marketingInstanceHistoryDO.setResult("");
        marketingInstanceHistoryDO.setAnalysisStart(new Date());
        marketingInstanceHistoryDO.setAnalysisEnd(new Date());
        marketingInstanceHistoryDO.setContactIds("");
        marketingInstanceHistoryDO.setMarketingInstanceId(1);
        marketingInstanceHistoryDO.setMarketingId(1L);
        return marketingInstanceHistoryDO;
    }

    @Test
    public void testInsertSelective() {
        MarketingInstanceHistoryDO marketingInstanceHistoryDO = getMarketingInstanceHistoryDO();
        Mockito.when(marketingInstanceHistoryDOMapper.insertSelective(marketingInstanceHistoryDO)).thenReturn(1);
        Assert.assertEquals(1, marketingInstanceHistoryDOMapper.insertSelective(marketingInstanceHistoryDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        MarketingInstanceHistoryDO marketingInstanceHistoryDO = getMarketingInstanceHistoryDO();
        Mockito.when(marketingInstanceHistoryDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingInstanceHistoryDO);
        Assert.assertEquals(marketingInstanceHistoryDO, marketingInstanceHistoryDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        MarketingInstanceHistoryDO marketingInstanceHistoryDO = getMarketingInstanceHistoryDO();
        Mockito.when(marketingInstanceHistoryDOMapper.updateByPrimaryKeySelective(marketingInstanceHistoryDO))
            .thenReturn(1);
        Assert.assertEquals(1,
            marketingInstanceHistoryDOMapper.updateByPrimaryKeySelective(marketingInstanceHistoryDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        MarketingInstanceHistoryDO marketingInstanceHistoryDO = getMarketingInstanceHistoryDO();
        Mockito.when(marketingInstanceHistoryDOMapper.updateByPrimaryKey(marketingInstanceHistoryDO)).thenReturn(1);
        Assert.assertEquals(1, marketingInstanceHistoryDOMapper.updateByPrimaryKey(marketingInstanceHistoryDO));
    }
}
