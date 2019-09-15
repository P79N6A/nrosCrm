package com.ztesoft.zsmart.nros.crm.core.server.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventMqValueDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.MarketingDomain;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.MemberService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.server.service.impl.MarketingEventTriggerServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.service.impl.MarketingServiceImpl;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author yuanxiaokai
 * @date 2019/7/16
 */

public class MarketingEventTriggerServiceTest extends MockitoTest {

    @Autowired
    @InjectMocks
    private MarketingEventTriggerServiceImpl marketingEventTriggerService;

    @Autowired
    @InjectMocks
    private MarketingServiceImpl marketingService;

    @Mock
    private MarketingDomain marketingDomain;

    @Mock
    private RepositoryService repositoryService;

    @Mock
    private MemberService memberService;

    @Mock
    private RuntimeService runtimeService;

    @Test
    public void testDoWork() {
        JSONObject jsonParm = new JSONObject();
        jsonParm.put("merchantCode", 1);
        jsonParm.put("eventCode", "E0009");
        jsonParm.put("memberId", 1L);
        EventMqValueDTO eventMqValueDTO = new EventMqValueDTO();
        eventMqValueDTO.setFieldCode("code");
        eventMqValueDTO.setFieldValue("value");
        List<EventMqValueDTO> attributeList = Lists.newArrayList(eventMqValueDTO);
        jsonParm.put("attributeList", attributeList);
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        ResponseMsg<MemberDetailDTO> responseMsg = ResponseMsg.build(memberDetailDTO);
        Mockito.when(memberService.getDetailById(1L)).thenReturn(responseMsg);
        Mockito.when(marketingDomain.saveEventTriggerHistory(any())).thenReturn(1L);
        Mockito.when(runtimeService.startProcessInstanceByKey(any())).thenReturn(null);
        MarketingDefineDTO marketingDefineDTO = new MarketingDefineDTO();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("eventCode", "E0009");
        jsonArray.add(jsonObject);
        marketingDefineDTO.setExcuteConfigJson(jsonArray.toJSONString());
        List<MarketingDefineDTO> lstDefine = Lists.newArrayList(marketingDefineDTO);

        Mockito.when(marketingDomain.listActiveCampaignDefines("1", MarketingTypeEnum.EVENT.getCode()))
            .thenReturn(lstDefine);
        marketingEventTriggerService.doWork(jsonParm.toJSONString());

        /*
         * jsonParm.remove("memberId"); marketingEventTriggerService.doWork(jsonParm.toJSONString());
         * Mockito.when(memberService.getDetailById(1L)).thenReturn(new ResponseMsg<>());
         * marketingEventTriggerService.doWork(jsonParm.toJSONString());
         */
    }

}
