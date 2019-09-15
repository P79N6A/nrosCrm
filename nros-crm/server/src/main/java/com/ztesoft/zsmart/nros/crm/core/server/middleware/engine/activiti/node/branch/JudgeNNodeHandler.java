package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch;

import java.util.List;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.ListOpsUtil;

/**
 * 判断节点，No分支
 * 
 * @author PQ
 * @date 2019/6/6
 */
@Component
public class JudgeNNodeHandler extends BaseFlowNodeHandler {
    // public class JudgeNFlowNode implements JavaDelegate {

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String param) {
        String parentId = JSONObject.parseObject(param).getString("parentActivityId");
        // 取targetUserListYes和tempTargetUserList差集
        List<TargetUserDTO> targetUserListYes = (List<TargetUserDTO>) delegateExecution
            .getVariable("targetUserListYes_" + parentId);
        List<TargetUserDTO> tempTargetUserList = (List<TargetUserDTO>) delegateExecution
            .getVariable("tempTargetUserList_" + parentId);
        // 取差集
        List<TargetUserDTO> resultList = ListOpsUtil.not(targetUserListYes, tempTargetUserList);
        delegateExecution.setVariableLocal("targetUserListYes", resultList);
        delegateExecution.setVariableLocal("hasTargetUserYes",
            !org.apache.commons.collections.CollectionUtils.isEmpty(resultList));
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.JUDGE_N.getCode();
    }
}
