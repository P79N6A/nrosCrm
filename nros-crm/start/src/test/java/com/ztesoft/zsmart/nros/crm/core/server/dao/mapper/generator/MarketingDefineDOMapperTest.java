package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingDefineDO;
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
public class MarketingDefineDOMapperTest extends MockitoTest {

    @Mock
    private MarketingDefineDOMapper marketingDefineDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(marketingDefineDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, marketingDefineDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        MarketingDefineDO marketingDefineDO = getMarketingDefineDO();
        Mockito.when(marketingDefineDOMapper.insert(marketingDefineDO)).thenReturn(1);
        Assert.assertEquals(1, marketingDefineDOMapper.insert(marketingDefineDO));
    }

    private MarketingDefineDO getMarketingDefineDO() {
        MarketingDefineDO marketingDefineDO = new MarketingDefineDO();
        marketingDefineDO.setTemplateId(1L);
        marketingDefineDO.setMarketingName("");
        marketingDefineDO.setMarketingRemark("");
        marketingDefineDO.setAnalysisStart(new Date());
        marketingDefineDO.setAnalysisEnd(new Date());
        marketingDefineDO.setIsRelative("1");
        marketingDefineDO.setBeforeCount(1);
        marketingDefineDO.setAfterCount(1);
        marketingDefineDO.setMarketingType("1");
        marketingDefineDO.setMarketingConfigJson("");
        marketingDefineDO.setExcuteConfigJson("");
        marketingDefineDO.setIsAlwaysValid("1");
        marketingDefineDO.setStarttime(new Date());
        marketingDefineDO.setFinishtime(new Date());
        marketingDefineDO.setFrequenceType("1");
        marketingDefineDO.setFrequenceCount(1);
        marketingDefineDO.setFrequenceUnit("1");
        marketingDefineDO.setMarketingStatus("1");
        return marketingDefineDO;
    }

    @Test
    public void testInsertSelective() {
        MarketingDefineDO marketingDefineDO = getMarketingDefineDO();
        Mockito.when(marketingDefineDOMapper.insertSelective(marketingDefineDO)).thenReturn(1);
        Assert.assertEquals(1, marketingDefineDOMapper.insertSelective(marketingDefineDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        MarketingDefineDO marketingDefineDO = getMarketingDefineDO();
        Mockito.when(marketingDefineDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingDefineDO);
        Assert.assertEquals(marketingDefineDO, marketingDefineDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        MarketingDefineDO marketingDefineDO = getMarketingDefineDO();
        Mockito.when(marketingDefineDOMapper.updateByPrimaryKeySelective(marketingDefineDO)).thenReturn(1);
        Assert.assertEquals(1, marketingDefineDOMapper.updateByPrimaryKeySelective(marketingDefineDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        MarketingDefineDO marketingDefineDO = getMarketingDefineDO();
        Mockito.when(marketingDefineDOMapper.updateByPrimaryKey(marketingDefineDO)).thenReturn(1);
        Assert.assertEquals(1, marketingDefineDOMapper.updateByPrimaryKey(marketingDefineDO));
    }
}
