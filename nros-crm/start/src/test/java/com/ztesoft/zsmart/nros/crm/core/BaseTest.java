package com.ztesoft.zsmart.nros.crm.core;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yuanxiaokai
 * @date 2019/7/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NrosCRMApplication.class)
@WebAppConfiguration
@Transactional
@Rollback()
@Slf4j
public class BaseTest {

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    protected void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print())
            .addFilter(new CharacterEncodingFilter(), "/*").alwaysExpect(status().isOk()).build();
        log.info("开始测试-----------------");
    }

    @After
    protected void after() {
        log.info("测试结束-----------------");
    }

    /**
     * @param method 请求方式
     * @param contentType 内容类型
     * @param urlTemplate 请求路由
     * @param contentObject @RequestBody 参数对象
     * @param param @requestParam 传参
     * @param urlVariables @PathVariable请求参数
     * @return
     */
    protected ResultActions getResultAction(RequestMethod method, MediaType contentType, String urlTemplate,
        Object contentObject, Map<String, String> param, Object... urlVariables) {
        MockHttpServletRequestBuilder builder;
        switch (method) {
            case GET:
                builder = MockMvcRequestBuilders.get(urlTemplate, urlVariables);
                break;
            case POST:
                builder = MockMvcRequestBuilders.post(urlTemplate, urlVariables);
                break;
            case PUT:
                builder = MockMvcRequestBuilders.put(urlTemplate, urlVariables);
                break;
            case DELETE:
                builder = MockMvcRequestBuilders.delete(urlTemplate, urlVariables);
                break;
            default:
                throw new UnsupportedOperationException(method.name());
        }
        if (contentObject != null) {
            builder.contentType(contentType).content(JSONObject.toJSONString(contentObject));
        }
        if (MapUtils.isNotEmpty(param)) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                builder.param(entry.getKey(), entry.getValue());
            }
        }
        try {
            return mockMvc.perform(builder).andExpect(status().isOk());
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}