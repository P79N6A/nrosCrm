package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.base.util.StringUtil;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.NodeExecuteRecordParam;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.MarketingDomain;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;

/**
 * 基础流程节点处理类
 * 
 * @author PQ
 * @date 2019/6/27
 */
public abstract class BaseFlowNodeHandler {
    // public abstract class BaseFlowNodeHandler implements JavaDelegate, ExecutionListener {

    private static Logger logger = LoggerFactory.getLogger(BaseFlowNodeHandler.class);

    // @Override
    public void notify(DelegateExecution delegateExecution) {
        this.execute(delegateExecution);
    }

    // @Override
    public void execute(DelegateExecution delegateExecution) {
        // 组装节点参数
        String nodeJsonParam = delegateExecution.getCurrentFlowElement().getDocumentation();
        // JSONObject noeJsonObject = StringUtil.isNull(nodeJsonParam) ? new JSONObject()
        // : JSONObject.parseObject(nodeJsonParam);
        // noeJsonObject.put("parentActivityId", parentActivityId);
        // 记录流程节点日志信息
        logger.info("执行流程节点：name={},ProcessInstanceId={},currentActivityId={},executionId={},parentId={}",
            delegateExecution.getCurrentFlowElement().getName(), delegateExecution.getProcessInstanceId(),
            delegateExecution.getCurrentActivityId(), delegateExecution.getId(), delegateExecution.getParentId());
        logger.info("流程节点处理类：handlerCLss=[{}],params={}", this.toString(), nodeJsonParam);
        // 调用子类实际业务处理方法
        this.doBusiness(delegateExecution, nodeJsonParam);
        // 增加流程节点执行记录
        this.insertNodeExecuteRecord(delegateExecution, nodeJsonParam);
    }

    /**
     * 流程节点实际业务处理方法（需子类实现）
     * 
     * @param delegateExecution
     * @param nodeJsonParam
     * @return void
     * @author PQ
     * @date 2019/6/27
     */
    public abstract void doBusiness(DelegateExecution delegateExecution, String nodeJsonParam);

    /**
     * 流程节点类型
     * 
     * @return java.lang.String
     * @author PQ
     * @date 2019/7/24
     */
    public String getNodeType() {
        return null;
    }

    /**
     * 获取当前节点筛选的目标用户
     * 
     * @param delegateExecution
     * @return java.util.List<TargetUserDTO>
     * @author PQ
     * @date 2019/7/24
     */
    public List<TargetUserDTO> getCurrentTargetUserList(DelegateExecution delegateExecution) {
        Object targetUserListYes = delegateExecution.getVariable("targetUserListYes");
        return targetUserListYes == null ? null : (List<TargetUserDTO>) targetUserListYes;
    }

    /**
     * 插入营销流程节点执行记录
     * 
     * @param delegateExecution
     * @param nodeJsonParam
     * @return void
     * @author PQ
     * @date 2019/7/24
     */
    public void insertNodeExecuteRecord(DelegateExecution delegateExecution, String nodeJsonParam) {
        // 只有该抽象类的子类有重写节点类型时，才新增相应节点执行记录
        if (StringUtil.isNotNull(this.getNodeType())) {
            try {
                NodeExecuteRecordParam nodeExecuteRecordParam = new NodeExecuteRecordParam();
                nodeExecuteRecordParam.setNodeId(delegateExecution.getCurrentActivityId());
                nodeExecuteRecordParam.setNodeType(this.getNodeType());
                nodeExecuteRecordParam.setNodeName(delegateExecution.getCurrentFlowElement().getName());
                nodeExecuteRecordParam.setMarketingId((Long) delegateExecution.getVariable("marketingDefineId"));
                nodeExecuteRecordParam.setMarketingInstanceId(delegateExecution.getProcessInstanceId());
                nodeExecuteRecordParam.setParam(nodeJsonParam);
                List<TargetUserDTO> targetUserListYes = this.getCurrentTargetUserList(delegateExecution);
                Long targetUserCount = targetUserListYes == null ? null : Long.valueOf(targetUserListYes.size());
                nodeExecuteRecordParam.setTargetUserCount(targetUserCount);

                MarketingDomain marketingDomain = SpringContextUtils.getBean(MarketingDomain.class);
                marketingDomain.saveNodeExecuteRecord(nodeExecuteRecordParam);
            }
            catch (Exception e) {
                logger.error("BaseFlowNodeHandler#insertNodeExecuteRecord error!", e.getCause());
            }
        }

    }

}
