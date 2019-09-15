package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingTemplateDO;
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
public class MarketingTemplateDOMapperTest extends MockitoTest {

    @Mock
    private MarketingTemplateDOMapper marketingTemplateDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(marketingTemplateDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, marketingTemplateDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        MarketingTemplateDO marketingTemplateDO = getMarketingTemplateDO();
        Mockito.when(marketingTemplateDOMapper.insert(marketingTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, marketingTemplateDOMapper.insert(marketingTemplateDO));
    }

    private MarketingTemplateDO getMarketingTemplateDO() {
        MarketingTemplateDO marketingTemplateDO = new MarketingTemplateDO();
        marketingTemplateDO.setTemplateDescription("");
        marketingTemplateDO.setTemplateName("");
        marketingTemplateDO.setMarketingConfigJson("");
        marketingTemplateDO.setMarketingType("1");
        marketingTemplateDO.setTemplatePicture("");
        return marketingTemplateDO;
    }

    @Test
    public void testInsertSelective() {
        MarketingTemplateDO marketingTemplateDO = getMarketingTemplateDO();
        Mockito.when(marketingTemplateDOMapper.insertSelective(marketingTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, marketingTemplateDOMapper.insertSelective(marketingTemplateDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        MarketingTemplateDO marketingTemplateDO = getMarketingTemplateDO();
        Mockito.when(marketingTemplateDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingTemplateDO);
        Assert.assertEquals(marketingTemplateDO, marketingTemplateDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        MarketingTemplateDO marketingTemplateDO = getMarketingTemplateDO();
        Mockito.when(marketingTemplateDOMapper.updateByPrimaryKeySelective(marketingTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, marketingTemplateDOMapper.updateByPrimaryKeySelective(marketingTemplateDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        MarketingTemplateDO marketingTemplateDO = getMarketingTemplateDO();
        Mockito.when(marketingTemplateDOMapper.updateByPrimaryKey(marketingTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, marketingTemplateDOMapper.updateByPrimaryKey(marketingTemplateDO));
    }
}
