package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;

/**
 * @author yuanxiaokai
 * @date 2019/7/1
 */
public class SendPointActionHandlerTest extends MockitoTest {

    @Autowired
    private SendPointActionHandler sendPointActionHandler;

    @Test
    public void testHandler() {
        MarketingDefineDTO marketingDefineDTO = new MarketingDefineDTO();
        JSONObject jsono = new JSONObject();
        jsono.put("points", "10");
        jsono.put("protectDateType", "RIGHTNOW");
        jsono.put("validDateType", "FIXTERM");
        jsono.put("invalidFixTerm", "10000");
        EventTriggerHistoryParam eventTriggerHistoryParam = new EventTriggerHistoryParam();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("triggerChannel", "25");
        jsonObject.put("orderCode", "");
        eventTriggerHistoryParam.setExtInfo(jsonObject);
        List<TargetUserDTO> targetUserListYes = new ArrayList<>(1);
        TargetUserDTO memberDetailDTO = new TargetUserDTO();
        // memberDetailDTO.setPhone("15100000000");
        memberDetailDTO.setId(4L);
        targetUserListYes.add(memberDetailDTO);
        sendPointActionHandler.handler(marketingDefineDTO, jsono.toJSONString(), eventTriggerHistoryParam,
            targetUserListYes,"2");
    }
}
