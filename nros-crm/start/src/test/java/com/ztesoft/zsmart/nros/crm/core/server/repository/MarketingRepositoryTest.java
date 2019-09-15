package com.ztesoft.zsmart.nros.crm.core.server.repository;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingEventStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTriggerHistoryQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineListQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.EventTriggerHistoryConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.MarketingDefineConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTriggerHistoryDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingDefineDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.EventTriggerHistoryMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.MarketingDefineMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.EventTriggerHistoryDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.MarketingDefineDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.EventTriggerHistoryBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.MarketingBO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * @author yuanxiaokai
 * @date 2019/7/15
 */
public class MarketingRepositoryTest extends MockitoTest {

    @Autowired
    @InjectMocks
    private MarketingRepository marketingRepository;

    @Mock
    private MarketingDefineDOMapper marketingDefineDOMapper;

    @Mock
    private MarketingDefineMapper marketingDefineMapper;

    @Mock
    private EventTriggerHistoryDOMapper eventTriggerHistoryDOMapper;

    @Mock
    private EventTriggerHistoryMapper eventTriggerHistoryMapper;

    @Test
    public void testDefineSave() {
        MarketingBO marketingDefineBO = new MarketingBO();
        marketingDefineBO.setId(1L);
        Mockito.when(marketingDefineDOMapper.insert(MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO)))
            .thenReturn(1);
        Assert.assertEquals(Long.valueOf(1), marketingRepository.defineSave(marketingDefineBO));
    }

    @Test
    public void testDefineUpdate() {
        MarketingBO marketingDefineBO = new MarketingBO();
        Mockito
            .when(marketingDefineDOMapper
                .updateByPrimaryKeySelective(MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO)))
            .thenReturn(1);
        marketingRepository.defineUpdate(marketingDefineBO);
    }

    @Test
    public void testDefineDelete() {
        MarketingBO marketingDefineBO = new MarketingBO();
        Mockito
            .when(marketingDefineDOMapper
                .updateByPrimaryKeySelective(MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO)))
            .thenReturn(1);
        marketingRepository.defineDelete(marketingDefineBO);
    }

    @Test
    public void processSave() {
        MarketingBO marketingDefineBO = new MarketingBO();
        Mockito
            .when(marketingDefineDOMapper
                .updateByPrimaryKeySelective(MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO)))
            .thenReturn(1);
        marketingRepository.processSave(marketingDefineBO);
    }

    @Test
    public void testProcessEnable() {
        MarketingBO marketingDefineBO = new MarketingBO();
        Mockito
            .when(marketingDefineDOMapper
                .updateByPrimaryKeySelective(MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO)))
            .thenReturn(1);
        marketingRepository.processEnable(marketingDefineBO);
    }

    @Test
    public void processDisable() {
        MarketingBO marketingDefineBO = new MarketingBO();
        Mockito
            .when(marketingDefineDOMapper
                .updateByPrimaryKeySelective(MarketingDefineConvertor.INSTANCE.boToDO(marketingDefineBO)))
            .thenReturn(1);
        marketingRepository.processDisable(marketingDefineBO);
    }

    @Test
    public void detail() {
        MarketingDefineQuery marketingDefineQuery = new MarketingDefineQuery();
        marketingDefineQuery.setId(1L);
        MarketingDefineDO marketingDefineDO = new MarketingDefineDO();
        marketingDefineDO.setIsAlwaysValid("1");
        Mockito.when(marketingDefineDOMapper.selectByPrimaryKey(1L)).thenReturn(marketingDefineDO);
        Assert.assertEquals(MarketingDefineConvertor.INSTANCE.doToDTO(marketingDefineDO),
            marketingRepository.detail(marketingDefineQuery));
    }

    @Test
    public void testQueryList() {
        MarketingDefineListQuery query = new MarketingDefineListQuery();
        MarketingDefineDO marketingDefineDO = ConvertUtil.beanCopy(query, MarketingDefineDO.class);
        Mockito.when(marketingDefineMapper.queryList(marketingDefineDO)).thenReturn(Collections.emptyList());
        Assert.assertEquals(new PageInfo<>(Collections.emptyList()).toString(),
            marketingRepository.queryList(query).toString());
    }

    @Test
    public void testSetAnalysis() {
        MarketingBO marketingBO = new MarketingBO();
        Mockito.when(marketingDefineMapper.setAnalysis(MarketingDefineConvertor.INSTANCE.boToDO(marketingBO)))
            .thenReturn(1);
        marketingRepository.setAnalysis(marketingBO);
    }

    @Test
    public void testListActiveCampaignDefines() {
        Mockito.when(marketingDefineMapper.selectActiveCampaignDefines("", "")).thenReturn(Collections.emptyList());
        Assert.assertEquals(Collections.emptyList(), marketingRepository.listActiveCampaignDefines("", ""));
    }

    @Test
    public void testSaveEventTriggerHistory() {
        EventTriggerHistoryBO eventTriggerHistoryBO = new EventTriggerHistoryBO();
        Mockito
            .when(
                eventTriggerHistoryDOMapper.insert(EventTriggerHistoryConvertor.INSTANCE.boToDO(eventTriggerHistoryBO)))
            .thenReturn(1);
        marketingRepository.saveEventTriggerHistory(eventTriggerHistoryBO);
    }

    @Test
    public void testStatisticsQuery() {
        EventTriggerHistoryQuery query = new EventTriggerHistoryQuery();
        EventTriggerHistoryDO eventTriggerHistoryDO = ConvertUtil.beanCopy(query, EventTriggerHistoryDO.class);
        Mockito.when(eventTriggerHistoryMapper.selectTriggerCountAllByMarketingId(eventTriggerHistoryDO)).thenReturn(1);
        Mockito.when(eventTriggerHistoryMapper.selectTriggerCountTodayByMarketingId(eventTriggerHistoryDO))
            .thenReturn(1);
        Mockito.when(eventTriggerHistoryMapper.selectProcessFinishedCountByMarketingId(eventTriggerHistoryDO))
            .thenReturn(1);
        MarketingEventStatisticsDTO returnDTO = new MarketingEventStatisticsDTO();
        returnDTO.setTriggerCountAll(1);
        returnDTO.setTriggerCountToday(1);
        returnDTO.setProcessFinishedCount(1);
        returnDTO.setFinishedPercent("100");
        Assert.assertEquals(returnDTO, marketingRepository.statisticsQuery(query));
    }

    @Test
    public void testEventTriggerList() {
        EventTriggerHistoryQuery query = new EventTriggerHistoryQuery();
        Mockito.when(eventTriggerHistoryMapper.eventTriggerList(EventTriggerHistoryConvertor.INSTANCE.queryToDO(query)))
            .thenReturn(Collections.emptyList());
        Assert.assertEquals(new PageInfo<>(Collections.emptyList()).toString(),
            marketingRepository.eventTriggerList(query).toString());
    }

    @Test
    public void testQueryTriggerHisByUserAndMarketingId() {
        Mockito.when(eventTriggerHistoryMapper.queryTriggerHisByUserAndMarketingId("1", 1L, 1L))
            .thenReturn(Collections.emptyList());
        Assert.assertEquals(Collections.emptyList(),
            marketingRepository.queryTriggerHisByUserAndMarketingId("", 1L, 1L));
    }

    @Test
    public void updateEventTriggerHisByKey() {
        EventTriggerHistoryBO eventTriggerHistoryBO = new EventTriggerHistoryBO();
        Mockito
            .when(eventTriggerHistoryDOMapper
                .updateByPrimaryKeySelective(EventTriggerHistoryConvertor.INSTANCE.boToDO(eventTriggerHistoryBO)))
            .thenReturn(1);
        marketingRepository.updateEventTriggerHisByKey(eventTriggerHistoryBO);
    }
}
