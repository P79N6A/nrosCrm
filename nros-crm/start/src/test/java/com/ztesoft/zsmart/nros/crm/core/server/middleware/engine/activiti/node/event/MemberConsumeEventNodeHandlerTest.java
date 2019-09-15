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
public class MemberConsumeEventNodeHandlerTest extends MockitoTest {

    @Autowired
    private MemberConsumeEventNodeHandler memberConsumeEventNodeHandler;

    @Test
    public void testCanTrigger() {
        MarketingDefineDTO defineDTO = new MarketingDefineDTO();
        List<JSONObject> list = new ArrayList<>(1);
        JSONObject eExcuteConfigJson = new JSONObject();
        JSONObject eventNodeParam = new JSONObject();
        eventNodeParam.put("factConsumeMoney", ">=100");
        eventNodeParam.put("factConsumeMoneyTotal", 100);
        eExcuteConfigJson.put("param", eventNodeParam);
        list.add(eExcuteConfigJson);
        defineDTO.setExcuteConfigJson(JsonUtil.bean2JsonStr(list));
        EventTriggerHistoryParam eventTriggerHistoryParam = new EventTriggerHistoryParam();
        JSONObject extInfo = new JSONObject();
        extInfo.put("orderCode", "1");
//        extInfo.put("orderFinalFee", 20000);
        extInfo.put("orderFee", 20000);
        eventTriggerHistoryParam.setExtInfo(extInfo);
        boolean rtn = memberConsumeEventNodeHandler.canTrigger(defineDTO, eventTriggerHistoryParam);
        assertTrue(rtn == true);
    }
}
