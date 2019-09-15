package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

/**
 * @author yuanxiaokai
 * @date 2019/7/17
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    FlowFinishActionHandlerTest.class, SendCouponActionHandlerTest.class, SendPointActionHandlerTest.class,
    SendSmsActionHandlerTest.class, TaggedActionHandlerTest.class
})
public class ActionHandlerSuitTest {
}
