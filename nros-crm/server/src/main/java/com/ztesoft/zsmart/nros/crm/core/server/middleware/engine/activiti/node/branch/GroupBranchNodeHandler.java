package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch;

import java.util.List;
import java.util.Map;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.ListOpsUtil;

/**
 * @author admin
 * @date 2018/6/6
 **/
@Component
public class GroupBranchNodeHandler extends BaseFlowNodeHandler {
    // public class GroupBranchFlowNode implements JavaDelegate {

    private transient Logger logger = LoggerFactory.getLogger(GroupBranchNodeHandler.class);

    /**
     * 更新上下文中的目标用户列表<br>
     *
     * @param delegateExecution
     * @return
     */
    @Override
    public void doBusiness(DelegateExecution delegateExecution, String innerParam) {
        logger.info("执行分组支节点流程动作.currentActivityId={},id={},parentId={}", delegateExecution.getCurrentActivityId(),
            delegateExecution.getId(), delegateExecution.getParentId());
        logger.info("Variables=:" + delegateExecution.getVariables());
        List<TargetUserDTO> resultList = Lists.newArrayList();

        String parentId = JSONObject.parseObject(innerParam).getString("parentActivityId");
        // String parentId = delegateExecution.getCurrentFlowElement().getParentContainer();
        String marketingType = (String) delegateExecution.getVariable("marketingType");

        // String innerParam = (String) param.getValue(delegateExecution);
        JSONObject groupBranchParam = JSONObject.parseObject(innerParam);
        Integer groupWay = groupBranchParam.getInteger("groupWay");
        Integer groupId = groupBranchParam.getInteger("groupId");

        Map<String, Object> groupMasterParams = (Map<String, Object>) delegateExecution
            .getVariable("groupMasterParams_" + parentId);
        List<TargetUserDTO> targetUserListYes = (List<TargetUserDTO>) groupMasterParams.get("targetUserListYes");

        // 从父节点中获取当前分组的目标用户列表
        if (null != groupWay && 1 == groupWay) {
            resultList = this.filter(groupBranchParam, targetUserListYes);
        }
        else {
            int[] indexs = ((List<int[]>) groupMasterParams.get("userIndexes")).get(groupId - 1);
            if (String.valueOf(MarketingTypeEnum.PUSH.getCode()).equals(marketingType)) {
                for (int i = 0; i < indexs.length; i++) {
                    resultList.add(targetUserListYes.get(indexs[i]));
                }
            }
            else {
                if (null == indexs || indexs.length <= 0) {
                    logger.info("该分支无客群，停止该分支执行 groupId={}", groupId);
                }
                else {
                    resultList = targetUserListYes;
                }
            }
        }
        delegateExecution.setVariableLocal("targetUserListYes", resultList);
        delegateExecution.setVariableLocal("hasTargetUserYes",
            !org.apache.commons.collections.CollectionUtils.isEmpty(resultList));
    }

    /**
     * TODO-按属性筛选用户列表(当前阶段不做)
     * 
     * @param groupParam
     * @param targetUserListYes
     * @return java.util.List<java.lang.Long>
     * @author PQ
     * @date 2019/6/13
     */
    private List<TargetUserDTO> filter(JSONObject groupParam, List<TargetUserDTO> targetUserListYes) {
        // TODO-按属性筛选客群
        final List<TargetUserDTO> tempTargetUserList = null;
        // List<Long> targetUserList = standardService.commonQueryFilterList(appletId, param.getTenantId(), condition,
        // formula);
        // 取目标用户交集
        return ListOpsUtil.add(targetUserListYes, tempTargetUserList);
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.GROUP_BRANCH.getCode();
    }
}
