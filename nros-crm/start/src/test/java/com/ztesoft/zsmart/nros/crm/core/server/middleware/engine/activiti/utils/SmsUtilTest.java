package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils;

import com.google.common.collect.Lists;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 测试发送短信工具类
 */
public class SmsUtilTest extends MockitoTest {

    @Autowired
    private SmsUtil smsUtil;

    @Test
    public void testBatchSendSms() {
        String content = "测试固定内容";
        List phoneList = Lists.newArrayList("17372776725", "13574104432");
//        smsUtil.batchSendSms(content, phoneList);
    }

    @Test
    public void testBatchSendDynamicsSms() {
        List contentList = Lists.newArrayList("PQ1，你好！", "PQ2,你好！");
        List phoneList = Lists.newArrayList("173727767250", "135741044320");
//        smsUtil.batchSendDynamicsSms(contentList, phoneList);
    }

}
