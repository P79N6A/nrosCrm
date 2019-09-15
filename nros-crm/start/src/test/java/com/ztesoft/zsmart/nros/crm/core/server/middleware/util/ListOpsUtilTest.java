package com.ztesoft.zsmart.nros.crm.core.server.middleware.util;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.ListOpsUtil;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author yuanxiaokai
 * @date 2019/7/19
 */
public class ListOpsUtilTest extends MockitoTest {

    @Test
    public void testAdd() {
        ListOpsUtil.add(Lists.newArrayList("a", "b", "c"), Lists.newArrayList("a", "b", "c", "d"));
        ListOpsUtil.add(new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void testNot() {
        ListOpsUtil.not(Lists.newArrayList("a", "b", "c"), Lists.newArrayList("a", "b", "c", "d"));
        ListOpsUtil.add(new ArrayList<>(), new ArrayList<>());

    }
}
