package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.config;

import org.activiti.engine.impl.cfg.IdGenerator;
import org.activiti.engine.impl.interceptor.DelegateInterceptor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * activiti引擎自定义配置实现
 * 
 * @author PQ
 * @date 2019/6/19
 */
@Component
public class MyProcessEngineConfigurationConfigurerImpl implements ProcessEngineConfigurationConfigurer {

    @Autowired
    @Qualifier("activitiIdGenerator")
    private IdGenerator activitiIdGenerator;

    @Autowired
    @Qualifier("flowNodeDelegateInterceptor")
    DelegateInterceptor flowNodeDelegateInterceptor;

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        // 使用自定义ID 生成策略
        springProcessEngineConfiguration.setIdGenerator(activitiIdGenerator);
        //// 设置自定义拦截器，用于记录流程节点公共日志信息
        //springProcessEngineConfiguration.setDelegateInterceptor(flowNodeDelegateInterceptor);
    }
}