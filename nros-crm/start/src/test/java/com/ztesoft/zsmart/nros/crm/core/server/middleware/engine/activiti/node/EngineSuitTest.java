package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action.ActionHandlerSuitTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch.BranchHandlerSuitTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event.MemberConsumeEventNodeHandlerTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event.RegisterEventNodeHandlerTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.filter.FilterNodeHandlerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yuanxiaokai
 * @date 2019/7/12
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ActionHandlerSuitTest.class, BranchHandlerSuitTest.class, MemberConsumeEventNodeHandlerTest.class,
    RegisterEventNodeHandlerTest.class, FilterNodeHandlerTest.class
})
public class EngineSuitTest {
}
