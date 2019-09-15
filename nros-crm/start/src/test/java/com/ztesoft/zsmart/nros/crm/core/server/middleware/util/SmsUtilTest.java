package com.ztesoft.zsmart.nros.crm.core.server.middleware.util;

import com.google.common.collect.Lists;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.SmsUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yuanxiaokai
 * @date 2019/7/19
 */
public class SmsUtilTest extends MockitoTest {

    @Autowired
    SmsUtil smsUtil;

    /**
     * 测试批量发送短信
     *
     * @param
     * @return void
     * @author PQ
     * @date 2019/6/21
     */
    @Test
    public void testBatchSendSms() {
        String content = "测试固定内容";
        List phoneList = Lists.newArrayList("12372675566");
        String phoneListStr = StringUtils.join(phoneList, ",");
        smsUtil.batchSendSms(content, phoneList);
    }

    /**
     * 测试批量发送动态短信
     *
     * @param
     * @return void
     * @author PQ
     * @date 2019/6/21
     */
    @Test
    public void testBatchSendDynamicsSms() {
        List contentList = Lists.newArrayList("PQ1，你好！", "PQ2,你好！", "PQ3,你好！", "PQ4,你好！");
        List phoneList = Lists.newArrayList("123726755660", "123726755660", "123726755660", "123726755660");
        smsUtil.batchSendDynamicsSms(contentList, phoneList);
    }
}
