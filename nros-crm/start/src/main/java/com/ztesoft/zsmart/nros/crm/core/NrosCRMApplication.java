/**
 * [Product]
 *     nrospromotion
 * [Copyright]
 *     Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 *     NrosPromotionApplication.java
 * [History]
 *     Version  Date      Author     Content
 *     -------- --------- ---------- ------------------------
 *     1.0.0    2019年3月27日   zhouyl5    最初版本
 */
package com.ztesoft.zsmart.nros.crm.core;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * CRM启动类
 * 
 * @author wangzhe
 * @date 2019/4/12 15:13
 */
@SpringBootApplication(scanBasePackages = {
    "com.ztesoft.zsmart.nros.base", "com.ztesoft.zsmart.nros.crm.core"
}, exclude = SecurityAutoConfiguration.class)
@EnableFeignClients
@EnableDiscoveryClient
@EnableAsync
public class NrosCRMApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(NrosCRMApplication.class);

    /**
     * spring-boot的上下文
     */
    private static ConfigurableApplicationContext applicationContext = null;

    /**
     * 启动spring boot<br>
     *
     * @author mao.xiaozhong<br>
     *         <br>
     */
    public static void start(String[] args) {
        applicationContext = new SpringApplicationBuilder(NrosCRMApplication.class).web(WebApplicationType.SERVLET)
            .bannerMode(Banner.Mode.OFF).run(args);
        applicationContext.registerShutdownHook();
    }

    /**
     * 启动main函数<br>
     *
     * @author mao.xiaozhong<br>
     * @param args <br>
     */
    public static void main(String[] args) {
        start(args);
    }
}
