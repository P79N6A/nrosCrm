package com.ztesoft.zsmart.nros.crm.core;

import com.ztesoft.zsmart.nros.crm.core.server.ServerSuitTest;
import com.ztesoft.zsmart.nros.crm.core.start.controller.ControllerSuitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yuanxiaokai
 * @date 2019/7/12
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ControllerSuitTest.class, ServerSuitTest.class
})
public class CRMSuitTest {
}
