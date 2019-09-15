package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils;

import com.ztesoft.zsmart.nros.base.util.SnowflakeIdWorker;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.springframework.stereotype.Component;

/**
 * 自定义activiti IdGenerator生成器，用于解决分布式部署主键冲突问题
 * 
 * @author PQ
 * @date 2019/6/17
 */
@Component(value = "activitiIdGenerator")
public class ActivitiIdGenerator implements IdGenerator {

    @Override
    public String getNextId() {
        return SnowflakeIdWorker.generateId().toString();
    }
}