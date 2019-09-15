package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.util.CollectionUtils;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;

/**
 * @author admin
 * @date 2018/6/6
 **/
// @Component
public abstract class BaseJudgeNodeHandler extends BaseFlowNodeHandler {

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String param) {
        List<TargetUserDTO> tempTargetUserList = null;
        List<TargetUserDTO> targetUserListYes = (List<TargetUserDTO>) delegateExecution
            .getVariable("targetUserListYes");
        // 如果目标用户列表为空,不继续往下执行
        if (!CollectionUtils.isEmpty(targetUserListYes)) {
            // 控制主节点 只负责筛选出所有的客群
            tempTargetUserList = this.operate(delegateExecution, param);
        }
        delegateExecution.setVariable("tempTargetUserList_" + delegateExecution.getCurrentActivityId(),
            tempTargetUserList);
        delegateExecution.setVariable("targetUserListYes_" + delegateExecution.getCurrentActivityId(),
            delegateExecution.getVariable("targetUserListYes"));
        delegateExecution.setVariable("hasTargetUserYes_" + delegateExecution.getCurrentActivityId(),
            !org.apache.commons.collections.CollectionUtils.isEmpty(tempTargetUserList));
    }

    /**
     * 不同控制节点的处理逻辑
     * 
     * @param delegateExecution
     * @param innerParam
     * @return
     * @throws BusiException
     */
    public abstract List<TargetUserDTO> operate(DelegateExecution delegateExecution, String innerParam)
        throws BusiException;

    /**
     * 重写获取当前节点筛选的目标用户列表，用于记录节点执行信息
     */
    @Override
    public List<TargetUserDTO> getCurrentTargetUserList(DelegateExecution delegateExecution) {
        Object targetUserListYes = delegateExecution
            .getVariable("tempTargetUserList_" + delegateExecution.getCurrentActivityId());
        return targetUserListYes == null ? null : (List<TargetUserDTO>) targetUserListYes;
    }
}
