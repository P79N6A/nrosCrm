package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingChannelDO;
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
public class MarketingChannelDOMapperTest extends MockitoTest {

    @Mock
    private MarketingChannelDOMapper marketingChannelDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(marketingChannelDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, marketingChannelDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        MarketingChannelDO marketingChannelDO = getMarketingChannelDO();
        Mockito.when(marketingChannelDOMapper.insert(marketingChannelDO)).thenReturn(1);
        Assert.assertEquals(1, marketingChannelDOMapper.insert(marketingChannelDO));
    }

    private MarketingChannelDO getMarketingChannelDO() {
        MarketingChannelDO marketingChannelDO = new MarketingChannelDO();
        marketingChannelDO.setNodeId(1);
        marketingChannelDO.setChannelType("1");
        marketingChannelDO.setFailCode("202");
        marketingChannelDO.setFailCounter(1);
        marketingChannelDO.setFailMsg("fail");
        marketingChannelDO.setMsgContent("");
        marketingChannelDO.setMsgStatus("2");
        marketingChannelDO.setReleaseTime(new Date());
        marketingChannelDO.setSendnow("1");
        marketingChannelDO.setSentCounter(1);
        marketingChannelDO.setSuccessCounter(1);
        marketingChannelDO.setTargetGroup(1L);
        marketingChannelDO.setTargetPhoneList("");
        marketingChannelDO.setFilterCondition("");
        marketingChannelDO.setCcPhoneList("");
        marketingChannelDO.setIsRepeat("1");
        marketingChannelDO.setRepeatType("1");
        marketingChannelDO.setWxMediaId("1");
        marketingChannelDO.setFilterConditionJson("");
        marketingChannelDO.setTitle("");
        marketingChannelDO.setIsAllWxfans("1");
        marketingChannelDO.setOriginGroup(1L);
        marketingChannelDO.setSmsTemplateId(1L);
        marketingChannelDO.setActiveCallTypeId(1);
        marketingChannelDO.setDueDate(new Date());
        marketingChannelDO.setSurveyId(1L);
        marketingChannelDO.setDescription("");
        marketingChannelDO.setH5ChannelUrl("");
        marketingChannelDO.setWxMediaId("1");
        marketingChannelDO.setCouponCode("1");
        marketingChannelDO.setCampaignH5Id(1L);
        marketingChannelDO.setWxMsgId("1");
        return marketingChannelDO;
    }

    @Test
    public void testInsertSelective() {
        MarketingChannelDO marketingChannelDO = getMarketingChannelDO();
        Mockito.when(marketingChannelDOMapper.insertSelective(marketingChannelDO)).thenReturn(1);
        Assert.assertEquals(1, marketingChannelDOMapper.insertSelective(marketingChannelDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        MarketingChannelDO marketingChannelDO = getMarketingChannelDO();
        Mockito.when(marketingChannelDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingChannelDO);
        Assert.assertEquals(marketingChannelDO, marketingChannelDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        MarketingChannelDO marketingChannelDO = getMarketingChannelDO();
        Mockito.when(marketingChannelDOMapper.updateByPrimaryKeySelective(marketingChannelDO)).thenReturn(1);
        Assert.assertEquals(1, marketingChannelDOMapper.updateByPrimaryKeySelective(marketingChannelDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        MarketingChannelDO marketingChannelDO = getMarketingChannelDO();
        Mockito.when(marketingChannelDOMapper.updateByPrimaryKey(marketingChannelDO)).thenReturn(1);
        Assert.assertEquals(1, marketingChannelDOMapper.updateByPrimaryKey(marketingChannelDO));
    }
}
