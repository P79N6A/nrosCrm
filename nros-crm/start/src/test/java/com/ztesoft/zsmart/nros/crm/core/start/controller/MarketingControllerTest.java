package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.MarketingDefineParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTriggerHistoryQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineListQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingInstanceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingDefineDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.NodeExecuteRecordDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.MarketingDefineMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.NodeExecuteRecordMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.MarketingDefineDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.repository.MarketingRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author yuanxiaokai
 * @date 2019/7/10
 */
public class MarketingControllerTest extends MockitoTest {

    @Autowired
    private MarketingController marketingController;

    @Mock
    private MarketingDefineDOMapper marketingDefineDOMapper;

    @Mock
    private MarketingDefineMapper marketingDefineMapper;

    @InjectMocks
    @Autowired
    private MarketingRepository marketingRepository;

    @Mock
    private NodeExecuteRecordMapper nodeExecuteRecordMapper;

    @Test
    public void testDefineSave() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setMarketingName("营销定义单元测试");
        marketingDefineParam.setMarketingType("1");

        when(marketingDefineDOMapper.insert(any(MarketingDefineDO.class))).thenReturn(1);

        Assert.assertTrue(marketingController.defineSave(marketingDefineParam).getSuccess() == true);
        verify(marketingDefineDOMapper).insert(any(MarketingDefineDO.class));
    }

    @Test
    public void testDefineUpdate() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setMarketingName("营销定义单元测试");
        marketingDefineParam.setMarketingType("1");
        marketingDefineParam.setId(1L);

        MarketingDefineDO marketingDefineDO = new MarketingDefineDO();
        marketingDefineDO.setId(1L);
        when(marketingDefineDOMapper.updateByPrimaryKeySelective(any(MarketingDefineDO.class))).thenReturn(1);
        when(marketingDefineDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingDefineDO);

        Assert.assertEquals(true, marketingController.defineUpdate(marketingDefineParam).getSuccess());
        verify(marketingDefineDOMapper).updateByPrimaryKeySelective(any(MarketingDefineDO.class));
    }

    @Test
    public void testDefineDelete() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setId(1L);
        marketingDefineParam.setMarketingStatus("0");

        MarketingDefineDO marketingDefineDO = new MarketingDefineDO();
        marketingDefineDO.setId(1L);
        marketingDefineDO.setMarketingStatus("0");
        when(marketingDefineDOMapper.updateByPrimaryKeySelective(any(MarketingDefineDO.class))).thenReturn(1);
        when(marketingDefineDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingDefineDO);

        Assert.assertEquals(true, marketingController.defineDelete(marketingDefineParam).getSuccess());

        verify(marketingDefineDOMapper).updateByPrimaryKeySelective(any(MarketingDefineDO.class));
    }

    @Test
    public void testProcessSave() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        // marketingDefineParam.setStatus("0");
        marketingDefineParam.setId(1L);

        MarketingDefineDO marketingDefineDO = new MarketingDefineDO();
        marketingDefineDO.setId(1L);
        marketingDefineDO.setMarketingStatus("0");
        when(marketingDefineDOMapper.updateByPrimaryKeySelective(any(MarketingDefineDO.class))).thenReturn(1);
        when(marketingDefineDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingDefineDO);

        Assert.assertEquals(true, marketingController.processSave(marketingDefineParam).getSuccess());
        verify(marketingDefineDOMapper).updateByPrimaryKeySelective(any(MarketingDefineDO.class));
    }

    @Test
    public void testProcessEnable() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setId(-1L);

        MarketingDefineDO marketingDefineDO = new MarketingDefineDO();
        marketingDefineDO.setId(-1L);
        marketingDefineDO.setMarketingStatus("0");
        when(marketingDefineDOMapper.updateByPrimaryKeySelective(any(MarketingDefineDO.class))).thenReturn(1);
        when(marketingDefineDOMapper.selectByPrimaryKey(-1L)).thenReturn(marketingDefineDO);

        Assert.assertEquals(true, marketingController.processEnable(marketingDefineParam).getSuccess());
        verify(marketingDefineDOMapper).updateByPrimaryKeySelective(any(MarketingDefineDO.class));
    }

    @Test
    public void testProcessDisable() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setId(-1L);

        MarketingDefineDO marketingDefineDO = new MarketingDefineDO();
        marketingDefineDO.setId(-1L);
        marketingDefineDO.setMarketingStatus("0");
        when(marketingDefineDOMapper.updateByPrimaryKeySelective(any(MarketingDefineDO.class))).thenReturn(1);
        when(marketingDefineDOMapper.selectByPrimaryKey(-1L)).thenReturn(marketingDefineDO);

        Assert.assertEquals(true, marketingController.processDisable(marketingDefineParam).getSuccess());
        verify(marketingDefineDOMapper).updateByPrimaryKeySelective(any(MarketingDefineDO.class));
    }

    @Test
    public void testDetail() {
        MarketingDefineQuery marketingDefineQuery = new MarketingDefineQuery();
        marketingDefineQuery.setId(-1L);

        MarketingDefineDO marketingDefineDO = new MarketingDefineDO();
        marketingDefineDO.setId(-1L);
        marketingDefineDO.setMarketingStatus("0");
        when(marketingDefineDOMapper.selectByPrimaryKey(-1L)).thenReturn(marketingDefineDO);

        Assert.assertNotNull(marketingController.detail(marketingDefineQuery).getData());

        verify(marketingDefineDOMapper, Mockito.times(2)).selectByPrimaryKey(-1L);
    }

    @Test
    public void testQueryList() {
        MarketingDefineListQuery query = new MarketingDefineListQuery();
        query.setMarketingStatus("1");
        Assert.assertNotNull(marketingController.queryList(query).getData());
    }

    @Test
    public void testSet() {
        MarketingDefineParam param = new MarketingDefineParam();
        param.setId(1L);

        when(marketingDefineMapper.setAnalysis(any(MarketingDefineDO.class))).thenReturn(1);
        Assert.assertEquals(true, marketingController.set(param).getSuccess());
        verify(marketingDefineMapper).setAnalysis(any(MarketingDefineDO.class));
    }

    @Test
    public void testStatisticsQuery() {
        EventTriggerHistoryQuery query = new EventTriggerHistoryQuery();
        query.setMarketingId(1L);
        Assert.assertEquals(true, marketingController.statisticsQuery(query).getSuccess());
    }

    @Test
    public void testEventTriggerList() {
        EventTriggerHistoryQuery query = new EventTriggerHistoryQuery();
        Assert.assertNotNull(marketingController.eventTriggerList(query).getData());
    }

    @Test
    public void testInstanceQuery() {
        Mockito.when(nodeExecuteRecordMapper.selectInstance(any(MarketingInstanceQuery.class)))
            .thenReturn(Lists.newArrayList(new NodeExecuteRecordDO()));
        marketingController.instanceQuery(1L);
    }

    @Test
    public void testInstanceStatisticsQuery() {
        MarketingDefineDO marketingDefineDO = new MarketingDefineDO();
        marketingDefineDO.setIsAlwaysValid("1");
        marketingDefineDO.setIsRelative("1");
        Mockito.when(marketingDefineDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingDefineDO);
        Mockito.when(nodeExecuteRecordMapper.selectInstance(any(MarketingInstanceQuery.class)))
            .thenReturn(Lists.newArrayList(new NodeExecuteRecordDO()));
        marketingController.instanceStatisticsQuery(1L, "1");
    }

}
