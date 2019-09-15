package com.ztesoft.zsmart.nros.crm.core.server.middleware.util;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.ActivitiIdGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yuanxiaokai
 * @date 2019/7/19
 */
public class ActivitiIdGeneratorTest extends MockitoTest {

    @Autowired
    private ActivitiIdGenerator activitiIdGenerator;

    @Test
    public void testGetNextId() {
        activitiIdGenerator.getNextId();
    }
}
