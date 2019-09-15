package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.FieldExtension;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.InclusiveGateway;
import org.activiti.bpmn.model.IntermediateCatchEvent;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.bpmn.model.TimerEventDefinition;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingActionTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingEventTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingJudgeNodeTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.TimerIntervalUnitEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action.EmptyActionHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch.GroupBranchNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch.GroupMasterNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch.JudgeNNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch.JudgeYNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.filter.FilterNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.timer.TimerNode;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.BpmnModelUtil;

/**
 * @author admin
 * @date 2018/6/6
 **/
public enum MarketingNodeTypeEnum {
    /**
     * 空节点
     */
    EMPTYNODE(-1, "空节点", "") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            ServiceTask flowNode = new ServiceTask();
            flowNode.setId(PromotionContants.ACTIVITI_SERVICE_TASK_NODE_PREFIX + jsonObject.getString("id"));
            flowNode.setName(generateFlowNodeName(jsonObject));
            flowNode.setDocumentation(BpmnModelUtil.generateFlowNodeDocument(jsonObject.getString("param"),
                parentActivityId, marketingDefineId));

            // flowNode.setImplementationType("class");
            // flowNode.setImplementation(EmptyActionHandler.class.getName());
            flowNode.setImplementationType("expression");
            flowNode.setImplementation(BpmnModelUtil.generateActivitiExpression(EmptyActionHandler.class));

            return flowNode;
        }
    },
    /**
     * 事件节点
     */
    EVENTNODE(0, "事件节点", "") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            ServiceTask flowNode = new ServiceTask();
            flowNode.setId(PromotionContants.ACTIVITI_EVENT_NODE_PREFIX + jsonObject.getString("id"));
            flowNode.setName(generateFlowNodeName(jsonObject));
            flowNode.setDocumentation(BpmnModelUtil.generateFlowNodeDocument(jsonObject.getString("param"),
                parentActivityId, marketingDefineId));
            // flowNode.setImplementationType("class");
            // 解析到具体的事件节点
            MarketingEventTypeEnum eventType = MarketingEventTypeEnum.getByEventCode(jsonObject.getString("eventCode"));
            if (Objects.isNull(eventType)) {
                ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0014");
            }
            // 设置事件执行类
            // flowNode.setImplementation(eventType.getClassName().getName());

            flowNode.setImplementationType("expression");
            flowNode.setImplementation(BpmnModelUtil.generateActivitiExpression(eventType.getClassName()));

            return flowNode;
        }
    },
    /**
     * 时间节点
     */
    TIMERNODE(1, "时间节点", "") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            IntermediateCatchEvent intermediateCatchEvent = new IntermediateCatchEvent();
            intermediateCatchEvent.setId(PromotionContants.ACTIVITI_TIMER_NODE_PREFIX + jsonObject.getString("id"));
            intermediateCatchEvent.setName(generateFlowNodeName(jsonObject));
            // 增加节点处理器
            List<ActivitiListener> executionListeners = Lists.newArrayList();
            ActivitiListener activitiListener = new ActivitiListener();
            activitiListener.setEvent("start");
            activitiListener.setImplementationType("expression");
            activitiListener.setImplementation(BpmnModelUtil.generateActivitiExpression(EmptyActionHandler.class));
            executionListeners.add(activitiListener);
            intermediateCatchEvent.setExecutionListeners(executionListeners);
            // 增加定时器设置
            TimerEventDefinition timerEventDefinition = new TimerEventDefinition();
            intermediateCatchEvent.setEventDefinitions(Lists.newArrayList(timerEventDefinition));
            TimerNode timerNode = new TimerNode(jsonObject.getJSONObject("timeData"));
            if (StringUtils.isEmpty(timerNode.getCrontab())) {
                // 相对日期
                // intervalUnit：[1]立即执行,[2]分,[4]天,[5]周,[6]月
                // TimeDuration: P(年)Y(月)M(日)DT（时）H（分）M（秒）S
                StringBuffer timeDuration = new StringBuffer("P");
                TimerIntervalUnitEnum timerIntervalUnitEnum = TimerIntervalUnitEnum
                    .getByValue(timerNode.getIntervalUnit());
                if (timerNode.getIntervalUnit() <= 2) {
                    timeDuration.append("T");
                }
                // 注：当前activiti中duedate不支持W周，所以当时间间隔单位为周时，需转换为天！！
                if (TimerIntervalUnitEnum.WEEK.getCode().equals(timerIntervalUnitEnum.getCode())) {
                    timeDuration.append(timerNode.getInterval() * 7).append(TimerIntervalUnitEnum.DAY.getCode());
                }
                else {
                    timeDuration.append(timerNode.getInterval()).append(timerIntervalUnitEnum.getCode());
                }
                timerEventDefinition.setTimeDuration(timeDuration.toString());
            }
            else {
                // CRON表达式
                timerEventDefinition.setTimeCycle(timerNode.getCrontab());
                // TODO:@PQ 测试设置起始时间
                // timerEventDefinition.setTimeDate(LocalDateTime.now().toString());
                timerEventDefinition.setEndDate(LocalDateTime.now().toString());
                // 设置周期执行结束日期
                if (timerNode.getTo() != null) {
                    timerEventDefinition.setEndDate(timerNode.getTo().toString());
                }
            }
            return intermediateCatchEvent;
        }
    },
    /**
     * 客群节点
     */
    FILTERNODE(2, "客群节点", "${hasTargetUserYes==false}") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            ServiceTask flowNode = new ServiceTask();
            flowNode.setId(PromotionContants.ACTIVITI_FILTER_NODE_PREFIX + jsonObject.getString("id"));
            flowNode.setName(generateFlowNodeName(jsonObject));
            flowNode.setDocumentation(BpmnModelUtil.generateFlowNodeDocument(jsonObject.getString("param"),
                parentActivityId, marketingDefineId));
            // flowNode.setDocumentation("${hasTargetUserYes==true}");
            // flowNode.setImplementationType("class");
            // 设置动作执行类
            // flowNode.setImplementation(FilterFlowNode.class.getName());
            // JSONObject paramJsonObject = jsonObject.getJSONObject("param");
            // if (paramJsonObject != null) {
            // // 设置流程节点参数
            // flowNode.setFieldExtensions(generateFlowNodeParams(paramJsonObject.toJSONString()));
            // }
            flowNode.setImplementationType("expression");
            flowNode.setImplementation(BpmnModelUtil.generateActivitiExpression(FilterNodeHandler.class));

            return flowNode;
        }
    },
    /**
     * 动作节点
     */
    ACTIONNODE(3, "动作节点", "") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            ServiceTask flowNode = new ServiceTask();
            flowNode.setId(PromotionContants.ACTIVITI_ACTION_NODE_PREFIX + jsonObject.getString("id"));
            flowNode.setName(generateFlowNodeName(jsonObject));
            flowNode.setDocumentation(BpmnModelUtil.generateFlowNodeDocument(jsonObject.getString("param"),
                parentActivityId, marketingDefineId));

            // flowNode.setImplementationType("class");
            // 解析到具体的动作节点
            MarketingActionTypeEnum actionTypeEnum = MarketingActionTypeEnum
                .getByCode(jsonObject.getString("actionType"));
            // 设置动作执行类
            // flowNode.setImplementation(actionTypeEnum.getClazz().getName());
            // JSONObject paramJsonObject = jsonObject.getJSONObject("param");
            // if (paramJsonObject != null) {
            // // 设置流程节点参数
            // flowNode.setFieldExtensions(generateFlowNodeParams(paramJsonObject.toJSONString()));
            // }
            flowNode.setImplementationType("expression");
            flowNode.setImplementation(BpmnModelUtil.generateActivitiExpression(actionTypeEnum.getClazz()));

            return flowNode;
        }
    },
    /**
     * 分组主节点
     */
    GROUPMASTERNODE(4, "分组主节点", "${hasTargetUserYes==false}") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            Integer groupWay = jsonObject.getInteger("groupWay");
            JSONArray groupMasterParam = jsonObject.getJSONArray("param");
            // 校验分组比例是否为100%
            if (!validateGroupMasterRate(groupWay, groupMasterParam)) {
                ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0012");
            }
            Integer groupCount = jsonObject.getInteger("groupCount");
            JSONObject groupMasterJson = new JSONObject();
            groupMasterJson.put("groupWay", groupWay);
            groupMasterJson.put("groupCount", groupCount);
            groupMasterJson.put("groupMasterParam", groupMasterParam);

            // 包容网关Fork节点
            InclusiveGateway inclusiveGateway = new InclusiveGateway();
            inclusiveGateway.setId(PromotionContants.ACTIVITI_GROUP_MASTER_NODE_PREFIX + jsonObject.getString("id"));
            inclusiveGateway.setName(generateFlowNodeName(jsonObject));
            inclusiveGateway.setDocumentation(BpmnModelUtil.generateFlowNodeDocument(groupMasterJson.toString(),
                parentActivityId, marketingDefineId));
            // 增加节点处理器
            List<ActivitiListener> executionListeners = Lists.newArrayList();
            ActivitiListener activitiListener = new ActivitiListener();
            activitiListener.setEvent("start");
            // activitiListener.setFieldExtensions(generateFlowNodeParams(groupMasterJson.toString()));
            // activitiListener.setImplementationType("class");
            // activitiListener.setImplementation(GroupMasterFlowNode.class.getName());
            activitiListener.setImplementationType("expression");
            activitiListener.setImplementation(BpmnModelUtil.generateActivitiExpression(GroupMasterNodeHandler.class));

            executionListeners.add(activitiListener);
            inclusiveGateway.setExecutionListeners(executionListeners);
            return inclusiveGateway;

        }
    },
    /**
     * 分组支节点
     */
    GROUPBRANCHNODE(5, "分组支节点", "${hasTargetUserYes==false}") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            ServiceTask flowNode = new ServiceTask();
            flowNode.setId(PromotionContants.ACTIVITI_GROUP_BRANCH_NODE_PREFIX + jsonObject.getString("id"));
            flowNode.setName(generateFlowNodeName(jsonObject));
            // flowNode.setImplementationType("class");
            // flowNode.setImplementation(GroupBranchFlowNode.class.getName());

            JSONObject groupBranchJson = new JSONObject();
            groupBranchJson.put("groupWay", jsonObject.getInteger("groupWay"));
            groupBranchJson.put("groupId", jsonObject.getInteger("groupId"));
            groupBranchJson.put("rate", jsonObject.getInteger("rate"));
            groupBranchJson.put("groupBranchCondition", jsonObject.getString("condition"));
            flowNode.setDocumentation(BpmnModelUtil.generateFlowNodeDocument(groupBranchJson.toString(),
                parentActivityId, marketingDefineId));

            // 设置流程节点参数
            // flowNode.setFieldExtensions(generateFlowNodeParams(groupBranchJson.toJSONString()));

            flowNode.setImplementationType("expression");
            flowNode.setImplementation(BpmnModelUtil.generateActivitiExpression(GroupBranchNodeHandler.class));

            return flowNode;
        }
    },
    /**
     * 判断节点
     */
    JUDGENODE(6, "判断节点", "${hasTargetUserYes==false}") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            // 包容网关Fork节点
            InclusiveGateway inclusiveGateway = new InclusiveGateway();
            inclusiveGateway.setId(PromotionContants.ACTIVITI_JUDGE_NODE_PREFIX + jsonObject.getString("id"));
            inclusiveGateway.setName(generateFlowNodeName(jsonObject));
            inclusiveGateway.setDocumentation(BpmnModelUtil.generateFlowNodeDocument(jsonObject.getString("param"),
                parentActivityId, marketingDefineId));

            List<ActivitiListener> executionListeners = Lists.newArrayList();
            ActivitiListener activitiListener = new ActivitiListener();
            activitiListener.setEvent("start");
            // activitiListener.setFieldExtensions(generateFlowNodeParams(jsonObject.getString("param")));
            // activitiListener.setImplementationType("class");
            executionListeners.add(activitiListener);
            inclusiveGateway.setExecutionListeners(executionListeners);

            // 区分不同的控制节点
            Integer conditionType = jsonObject.getInteger("conditionType");
            MarketingJudgeNodeTypeEnum judgeType = MarketingJudgeNodeTypeEnum.index(conditionType);
            activitiListener.setImplementationType("expression");
            activitiListener.setImplementation(BpmnModelUtil.generateActivitiExpression(judgeType.getClassName()));
            return inclusiveGateway;
        }
    },
    /**
     * 判断Y节点
     */
    JUDGENODE_Y(7, "判断Y节点", "${hasTargetUserYes==false}") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            // 取targetUserListYes和tempTargetUserList交集
            ServiceTask flowNode = new ServiceTask();
            flowNode.setId(PromotionContants.ACTIVITI_JUDGE_NODE_YES_PREFIX + jsonObject.getString("id"));
            flowNode.setName(generateFlowNodeName(jsonObject));
            flowNode.setDocumentation(BpmnModelUtil.generateFlowNodeDocument(jsonObject.getString("param"),
                parentActivityId, marketingDefineId));
            // flowNode.setImplementationType("class");
            // flowNode.setImplementation(JudgeYFlowNode.class.getName());
            flowNode.setImplementationType("expression");
            flowNode.setImplementation(BpmnModelUtil.generateActivitiExpression(JudgeYNodeHandler.class));
            return flowNode;
        }
    },
    /**
     * 判断N节点
     */
    JUDGENODE_N(8, "判断N节点", "${hasTargetUserYes==false}") {
        @Override
        public FlowNode getMarketingNode(JSONObject jsonObject, String parentActivityId, Long marketingDefineId)
            throws BusiException {
            // 取targetUserListYes和tempTargetUserList差集
            ServiceTask flowNode = new ServiceTask();
            flowNode.setId(PromotionContants.ACTIVITI_JUDGE_NODE_NO_PREFIX + jsonObject.getString("id"));
            flowNode.setName(generateFlowNodeName(jsonObject));
            flowNode.setDocumentation(BpmnModelUtil.generateFlowNodeDocument(jsonObject.getString("param"),
                parentActivityId, marketingDefineId));
            // flowNode.setImplementationType("class");
            // flowNode.setImplementation(JudgeNFlowNode.class.getName());
            flowNode.setImplementationType("expression");
            flowNode.setImplementation(BpmnModelUtil.generateActivitiExpression(JudgeNNodeHandler.class));

            return flowNode;
        }
    };

    private Integer nodeType;

    private String title;

    private String flowExpressionNo;

    public Integer getNodeType() {
        return nodeType;
    }

    public String getTitle() {
        return title;
    }

    public String getFlowExpressionNo() {
        return flowExpressionNo;
    }

    /*
     * public void setFlowExpressionNo(String flowExpressionNo) { this.flowExpressionNo = flowExpressionNo; }
     */

    MarketingNodeTypeEnum(Integer nodeType, String title, String flowExpressionNo) {
        this.nodeType = nodeType;
        this.title = title;
        this.flowExpressionNo = flowExpressionNo;
    }

    public static MarketingNodeTypeEnum getByNodeType(Integer nodeType) {
        return Arrays.stream(MarketingNodeTypeEnum.values())
            .filter(nodeTypeEnum -> nodeTypeEnum.nodeType.equals(nodeType)).findAny().orElse(null);
    }

    public abstract FlowNode getMarketingNode(JSONObject nodeJson, String parentActivityId, Long marketingDefineId)
        throws BusiException;

    /**
     * 生成节点参数
     *
     * @param params
     * @return void
     * @author PQ
     * @date 2019/6/6
     */
    private static List<FieldExtension> generateFlowNodeParams(String params) {
        FieldExtension fieldExtension = new FieldExtension();
        fieldExtension.setFieldName("param");
        fieldExtension.setExpression(params);
        return Lists.newArrayList(fieldExtension);
    }

    /**
     * 生成节点名称
     *
     * @param jsonObject
     * @return java.lang.String
     * @author PQ
     * @date 2019/6/13
     */
    private static String generateFlowNodeName(JSONObject jsonObject) {
        String nodeName = jsonObject.getString("nodeName");
        if (StringUtils.isEmpty(nodeName)) {
            nodeName = jsonObject.getString("nodeDesc");
            if (StringUtils.isEmpty(nodeName)) {
                nodeName = jsonObject.getString("label");
            }
        }
        return nodeName;
    }

    /**
     * 校验分组总和是否是100%
     *
     * @return
     */
    private static boolean validateGroupMasterRate(Integer groupWay, JSONArray jsonArray) {
        try {
            // 如果分组方式是随机分组,从param中获取各组的百分比,校验是否为100%
            // if (0 == groupWay) {
            int rateCount = 0;
            for (int i = 0; i < jsonArray.size(); i++) {
                rateCount += jsonArray.getJSONObject(i).getInteger("rate");
            }
            if (100 != rateCount) {
                return false;
            }
            // }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
