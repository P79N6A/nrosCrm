package com.ztesoft.zsmart.nros.crm.core.server.domain.marketing;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.common.model.enums.StatusEnum;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTriggerHistoryDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingEventStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.MarketingDefineParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTriggerHistoryQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineListQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.EventTriggerHistoryConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.MarketingDefineConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingStatusEnum;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.MarketingBO;
import com.ztesoft.zsmart.nros.crm.core.server.repository.MarketingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @author yuanxiaokai
 * @date 2019/7/15
 */
public class MarketingDomainTest extends MockitoTest {

    @Autowired
    @InjectMocks
    private MarketingDomain marketingDomain;

    @Mock
    private MarketingRepository marketingRepository;

    @Test
    public void testDefineSave() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setMarketingName("test");
        MarketingBO marketingDefineBO = MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam);
        marketingDefineBO.setMarketingStatus(MarketingStatusEnum.IN_DESIGN.getState());
        marketingDefineBO.setMerchantCode(marketingDefineParam.getMerchantCode());
        marketingDefineBO.setStatus(StatusEnum.ENABLE.getState());
        Mockito.when(marketingRepository.defineSave(marketingDefineBO)).thenReturn(1L);
        Assert.assertEquals(Long.valueOf(1), marketingDomain.defineSave(marketingDefineParam));

        // 测试异常流程
        marketingDefineParam.setMarketingName(null);
        try {
            marketingDomain.defineSave(marketingDefineParam);
        }
        catch (BusiException e) {
            Assert.assertEquals("NROS-SBC-PROMOTION-0033", e.getErrorCode());
        }

    }

    @Test
    public void testDefineUpdate() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setId(1L);
        Mockito.when(marketingRepository.getMarketingDefineByPrimaryKey(1L)).thenReturn(new MarketingDefineDTO());
        Mockito.doNothing().when(marketingRepository)
            .defineUpdate(MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam));
        marketingDomain.defineUpdate(marketingDefineParam);
    }

    @Test
    public void testDefineDelete() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setId(1L);
        MarketingDefineDTO marketingDefineDTO = new MarketingDefineDTO();
        marketingDefineDTO.setMarketingStatus(MarketingStatusEnum.IN_DESIGN.getState());
        Mockito.when(marketingRepository.getMarketingDefineByPrimaryKey(1L)).thenReturn(marketingDefineDTO);
        Mockito.doNothing().when(marketingRepository)
            .defineDelete(MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam));
        marketingDomain.defineDelete(marketingDefineParam);

        marketingDefineDTO.setMarketingStatus(null);
        Mockito.when(marketingRepository.getMarketingDefineByPrimaryKey(1L)).thenReturn(marketingDefineDTO);
        try {
            marketingDomain.defineDelete(marketingDefineParam);
        }
        catch (BusiException e) {
            Assert.assertEquals("NROS-SBC-PROMOTION-0034", e.getErrorCode());
        }
    }

    @Test
    public void testProcessSave() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setId(1L);
        marketingDefineParam.setIsEnable("1");
        JSONObject originalJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject childJson = new JSONObject();
        childJson.put("id", 1);
        jsonArray.add(childJson);
        originalJson.put("children", jsonArray);
        marketingDefineParam.setMarketingConfigJson(originalJson.toJSONString());
        MarketingDefineDTO marketingDefineDTO = new MarketingDefineDTO();
        marketingDefineDTO.setMarketingStatus(MarketingStatusEnum.IN_DESIGN.getState());
        Mockito.when(marketingRepository.getMarketingDefineByPrimaryKey(1L)).thenReturn(marketingDefineDTO);
        MarketingBO marketingProcessBO = MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam);
        Mockito.doNothing().when(marketingRepository).processSave(marketingProcessBO);
        marketingDomain.processSave(marketingDefineParam);

        marketingDefineDTO.setMarketingStatus(null);
        Mockito.when(marketingRepository.getMarketingDefineByPrimaryKey(1L)).thenReturn(marketingDefineDTO);
        try {
            marketingDomain.processSave(marketingDefineParam);
        }
        catch (BusiException e) {
            Assert.assertEquals("NROS-SBC-PROMOTION-0032", e.getErrorCode());
        }
    }

    @Test
    public void testProcessEnable() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setId(1L);
        Mockito.when(marketingRepository.getMarketingDefineByPrimaryKey(1L)).thenReturn(new MarketingDefineDTO());
        Mockito.doNothing().when(marketingRepository)
            .processEnable(MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam));
        marketingDomain.processEnable(marketingDefineParam);
    }

    @Test
    public void testProcessDisable() {
        MarketingDefineParam marketingDefineParam = new MarketingDefineParam();
        marketingDefineParam.setId(1L);
        Mockito.when(marketingRepository.getMarketingDefineByPrimaryKey(1L)).thenReturn(new MarketingDefineDTO());
        Mockito.doNothing().when(marketingRepository)
            .processDisable(MarketingDefineConvertor.INSTANCE.paramToBO(marketingDefineParam));
        marketingDomain.processDisable(marketingDefineParam);
    }

    @Test
    public void testDetail() {
        MarketingDefineQuery marketingDefineQuery = new MarketingDefineQuery();
        marketingDefineQuery.setId(1L);
        MarketingDefineDTO marketingDefineDTO = new MarketingDefineDTO();
        Mockito.when(marketingRepository.getMarketingDefineByPrimaryKey(1L)).thenReturn(marketingDefineDTO);
        Mockito.when(marketingRepository.detail(marketingDefineQuery)).thenReturn(marketingDefineDTO);
        Assert.assertEquals(marketingDefineDTO, marketingDomain.detail(marketingDefineQuery));
        marketingDefineQuery.setId(null);
        try {
            marketingDomain.detail(marketingDefineQuery);
        }
        catch (BusiException e) {
            Assert.assertEquals("NROS-SBC-PROMOTION-0030", e.getErrorCode());
        }
    }

    @Test
    public void testQueryList() {
        MarketingDefineListQuery query = new MarketingDefineListQuery();
        PageInfo<MarketingDefineDTO> objectPageInfo = new PageInfo<>(Collections.emptyList());
        Mockito.when(marketingRepository.queryList(query)).thenReturn(objectPageInfo);
        Assert.assertEquals(objectPageInfo, marketingDomain.queryList(query));
    }

    @Test
    public void testSetAnalysis() {
        MarketingDefineParam param = new MarketingDefineParam();
        param.setId(1L);
        Mockito.doNothing().when(marketingRepository).setAnalysis(MarketingDefineConvertor.INSTANCE.paramToBO(param));
        marketingDomain.setAnalysis(param);
    }

    @Test
    public void testListActiveCampaignDefines() {
        Mockito.when(marketingRepository.listActiveCampaignDefines("1", "1")).thenReturn(Collections.emptyList());
        Assert.assertEquals(Collections.emptyList(), marketingDomain.listActiveCampaignDefines("1", "1"));
    }

    @Test
    public void testSaveEventTriggerHistory() {
        EventTriggerHistoryParam eventTriggerHistoryParam = new EventTriggerHistoryParam();
        Mockito
            .when(marketingRepository
                .saveEventTriggerHistory(EventTriggerHistoryConvertor.INSTANCE.paramToBO(eventTriggerHistoryParam)))
            .thenReturn(1L);
        Assert.assertEquals(Long.valueOf(1), marketingDomain.saveEventTriggerHistory(eventTriggerHistoryParam));
    }

    @Test
    public void testStatisticsQuery() {
        EventTriggerHistoryQuery query = new EventTriggerHistoryQuery();
        MarketingEventStatisticsDTO marketingEventStatisticsDTO = new MarketingEventStatisticsDTO();
        Mockito.when(marketingRepository.statisticsQuery(query)).thenReturn(marketingEventStatisticsDTO);
        Assert.assertEquals(marketingEventStatisticsDTO, marketingDomain.statisticsQuery(query));
    }

    @Test
    public void testEventTriggerList() {
        EventTriggerHistoryQuery query = new EventTriggerHistoryQuery();
        PageInfo<EventTriggerHistoryDTO> pageInfo = new PageInfo<>(Collections.emptyList());
        Mockito.when(marketingRepository.eventTriggerList(query)).thenReturn(pageInfo);
        Assert.assertEquals(pageInfo, marketingDomain.eventTriggerList(query));
    }

    @Test
    public void testQueryTriggerHisByUserAndMarketingId() {
        List<EventTriggerHistoryDTO> eventTriggerHistoryDTOList = Collections.emptyList();
        Mockito.when(marketingRepository.queryTriggerHisByUserAndMarketingId("1", 1L, 1L))
            .thenReturn(eventTriggerHistoryDTOList);
        Assert.assertEquals(eventTriggerHistoryDTOList,
            marketingDomain.queryTriggerHisByUserAndMarketingId("1", 1L, 1L));
    }

    @Test
    public void testUpdateEventTriggerHisByKey() {
        EventTriggerHistoryParam eventTriggerHistoryParam = new EventTriggerHistoryParam();
        Mockito.doNothing().when(marketingRepository)
            .updateEventTriggerHisByKey(EventTriggerHistoryConvertor.INSTANCE.paramToBO(eventTriggerHistoryParam));
        marketingDomain.updateEventTriggerHisByKey(eventTriggerHistoryParam);
    }

}
