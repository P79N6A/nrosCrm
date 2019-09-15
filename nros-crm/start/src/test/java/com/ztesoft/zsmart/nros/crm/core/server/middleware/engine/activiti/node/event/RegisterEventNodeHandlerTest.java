package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event;

import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.base.util.JsonUtil;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author yuanxiaokai
 * @date 2019/7/12
 */
public class RegisterEventNodeHandlerTest extends MockitoTest {

    @Autowired
    private RegisterEventNodeHandler registerEventNodeHandler;

    @Test
    public void testCanTrigger() {
        MarketingDefineDTO defineDTO = new MarketingDefineDTO();
        List<JSONObject> list = new ArrayList<>(1);
        JSONObject excuteConfigJson = new JSONObject();
        excuteConfigJson.put("eventType", 1);
        list.add(excuteConfigJson);
        defineDTO.setExcuteConfigJson(JsonUtil.bean2JsonStr(list));
        EventTriggerHistoryParam eventTriggerHistoryParam = new EventTriggerHistoryParam();
        JSONObject extInfo = new JSONObject();
        extInfo.put("registerChannel", 1);
        eventTriggerHistoryParam.setExtInfo(extInfo);
        boolean rtn = registerEventNodeHandler.canTrigger(defineDTO, eventTriggerHistoryParam);

        assertTrue(rtn == true);
    }

}
