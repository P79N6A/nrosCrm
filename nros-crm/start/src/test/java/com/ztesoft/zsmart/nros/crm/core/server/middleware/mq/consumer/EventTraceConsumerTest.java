package com.ztesoft.zsmart.nros.crm.core.server.middleware.mq.consumer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.ztesoft.zsmart.nros.base.util.DateUtil;
import com.ztesoft.zsmart.nros.base.zmq.entity.NrosMQMessage;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;

/**
 * 会员轨迹mq消费者测试
 */
public class EventTraceConsumerTest extends MockitoTest {

    @Autowired
    private EventTraceConsumer eventTraceConsumer;

    @Transactional
    @Test
    public void testDoBusinesses() {
        ConsumeContext consumeContext = Mockito.mock(ConsumeContext.class);
        NrosMQMessage nrosMQMessage = Mockito.mock(NrosMQMessage.class);
        Message message = Mockito.mock(Message.class);
        when(nrosMQMessage.getMqMessage()).thenReturn(message);

        JSONObject msgObject = new JSONObject();
        msgObject.put("__nrosMsgIdInner__", "recharge123");
        msgObject.put("attributeList", buildAttributeList().toJSONString());
        msgObject.put("eventCode", "E0013");
//        msgObject.put("eventCode", "E0010");
        msgObject.put("memberId", "317228352979968000");
        msgObject.put("gmtCreate", DateUtil.getNow("yyyy-MM-dd HH:mm:ss"));

        String msgBody = msgObject.toJSONString();
        when(message.getBody()).thenReturn(msgBody.getBytes());

        eventTraceConsumer.doBusinesses(nrosMQMessage, consumeContext);

    }

    private JSONArray buildAttributeList() {
        JSONArray jsonArray = new JSONArray();
        JSONObject orderObj = new JSONObject();
        orderObj.put("fieldCode", "orderCode");
        orderObj.put("fieldValue", "11111");
        JSONObject rechargeObj = new JSONObject();
        rechargeObj.put("fieldCode", "rechargeMoney");
//        rechargeObj.put("fieldCode", "orderFee");
        rechargeObj.put("fieldValue", "11");
        jsonArray.add(orderObj);
        jsonArray.add(rechargeObj);
        return jsonArray;
    }


}
