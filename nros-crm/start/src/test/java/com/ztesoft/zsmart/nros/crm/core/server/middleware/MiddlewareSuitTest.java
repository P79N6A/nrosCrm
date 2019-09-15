package com.ztesoft.zsmart.nros.crm.core.server.middleware;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.EngineSuitTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.util.ActivitiIdGeneratorTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.util.BpmnModelUtilTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.util.ListOpsUtilTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.util.SmsUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yuanxiaokai
 * @date 2019/7/19
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    EngineSuitTest.class, ActivitiIdGeneratorTest.class, BpmnModelUtilTest.class, ListOpsUtilTest.class,
    SmsUtilTest.class
})
public class MiddlewareSuitTest {
}
