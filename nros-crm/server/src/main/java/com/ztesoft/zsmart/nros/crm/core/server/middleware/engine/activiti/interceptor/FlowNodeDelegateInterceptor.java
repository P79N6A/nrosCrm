package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.interceptor;

import java.lang.reflect.Field;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.delegate.invocation.DelegateInvocation;
import org.activiti.engine.impl.delegate.invocation.ExecutionListenerInvocation;
import org.activiti.engine.impl.delegate.invocation.JavaDelegateInvocation;
import org.activiti.engine.impl.interceptor.DelegateInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component(value = "flowNodeDelegateInterceptor")
public class FlowNodeDelegateInterceptor implements DelegateInterceptor {

    private static Logger logger = LoggerFactory.getLogger(FlowNodeDelegateInterceptor.class);

    @Override
    public void handleInvocation(DelegateInvocation invocation) {
        try {
            Object target = invocation.getTarget();
            DelegateExecution execution = null;
            if (target instanceof JavaDelegate) {
                Field executionField = ReflectionUtils.findField(JavaDelegateInvocation.class, "execution",
                    DelegateExecution.class);
                ReflectionUtils.makeAccessible(executionField);
                execution = (DelegateExecution) ReflectionUtils.getField(executionField, invocation);
            }
            else if (target instanceof ExecutionListener) {
                Field executionField = ReflectionUtils.findField(ExecutionListenerInvocation.class, "execution",
                    DelegateExecution.class);
                ReflectionUtils.makeAccessible(executionField);
                execution = (DelegateExecution) ReflectionUtils.getField(executionField, invocation);
            }
            if (execution != null) {
                // 记录流程节点日志信息
                logger.info("执行流程节点：ProcessInstanceId={},name={},currentActivityId={},executionId={},parentId={}",
                    execution.getProcessInstanceId(), execution.getCurrentFlowElement().getName(),
                    execution.getCurrentActivityId(), execution.getId(), execution.getParentId());
                logger.info("流程节点处理类：handlerCLss=[{}],variables={}", target.toString(), execution.getVariables());
            }
        }
        catch (Exception e) {
            logger.error("FlowNodeDelegateInterceptor.handleInvocation error!", e.getCause());
        }
        finally {
            invocation.proceed();
        }
    }
}