package com.ztesoft.zsmart.nros.crm.core.server.service;

import java.util.List;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.api.MarketingService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;

public class MarketingServiceTest extends MockitoTest {

    @Autowired
    private MarketingService marketingService;

    @Test
    public void listActiveCampaignDefines() {
        String merchantCode="1";
        String marketingType="2";
        List<MarketingDefineDTO> marketingDefineDTOS=marketingService.listActiveCampaignDefines(merchantCode,
             marketingType);
            ResponseMsg msg = ResponseMsg.build(marketingDefineDTOS);
            System.out.println(JSON.toJSONString(msg));
        }
}