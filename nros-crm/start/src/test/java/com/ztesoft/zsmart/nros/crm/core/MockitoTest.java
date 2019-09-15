package com.ztesoft.zsmart.nros.crm.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanxiaokai
 * @date 2019/7/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NrosCRMApplication.class)
@WebAppConfiguration
@Transactional
@Rollback()
@Slf4j
public class MockitoTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        log.info("开始测试-----------------");
    }

    @Test
    public void test() {
        log.debug("BaseTest test start。。。");
    }

    @After
    public void after() {
        log.info("测试结束-----------------");
    }
}
