/**
 * [Product]
 * nrospromotion
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * BpmnModelUtil.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019/6/3   PQ         最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.InclusiveGateway;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.TimerEventDefinition;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Maps;
import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.base.util.DateUtil;
import com.ztesoft.zsmart.nros.base.util.SnowflakeIdWorker;
import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.base.util.StringUtil;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.MarketingDefineParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action.DelegateDeployActionHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action.EmptyActionHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action.FlowFinishActionHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.timer.TimerNode;

/**
 * BPMN模型工具类
 *
 * @author PQ
 * @date 2019/6/3
 */
public class BpmnModelUtil {
    private static final Logger logger = LoggerFactory.getLogger(BpmnModelUtil.class);

    /**
     * 构建一个专门在指定时间触发目标流程定义部署和执行的内部委托流程，以此来扩充activiti周期定时器不支持开始时间的场景
     * 
     * @param targetMarketingBeginTime 目标流程开始时间
     * @param targetMarketingDefineId 目标流程定义ID
     * @return org.activiti.bpmn.model.BpmnModel
     * @author PQ
     * @date 2019/7/17
     */
    public static BpmnModel generateDelegateDeployBpmnModel(String targetMarketingBeginTime,
        Long targetMarketingDefineId) {
        Process process = new Process();
        process.setId(PromotionContants.ACTIVITI_PROCESS_PREFIX + SnowflakeIdWorker.generateId().toString());
        // 实例化BpmnModel对象
        BpmnModel bpmnModel = new BpmnModel();
        bpmnModel.addProcess(process);
        // 开始节点
        StartEvent startEvent = new StartEvent();
        startEvent.setId("startEvent");
        startEvent.setName("流程开始");
        // 开始节点增加监听处理器，用于在启动时部署目标流程定义
        List<ActivitiListener> startExecutionListeners = Lists.newArrayList();
        ActivitiListener startActivitiListener = new ActivitiListener();
        startActivitiListener.setEvent("start");
        startActivitiListener.setImplementationType("expression");
        startActivitiListener.setImplementation(generateActivitiExpression(DelegateDeployActionHandler.class));
        startExecutionListeners.add(startActivitiListener);
        startEvent.setExecutionListeners(startExecutionListeners);
        // 为开始节点增加定时器设置
        TimerEventDefinition timerEventDefinition = new TimerEventDefinition();
        timerEventDefinition.setTimeDate(targetMarketingBeginTime);
        startEvent.setEventDefinitions(Lists.newArrayList(timerEventDefinition));
        // 为开始节点设置参数
        JSONObject startNodeParam = new JSONObject();
        startNodeParam.put("marketingDefineId", targetMarketingDefineId);
        startEvent.setDocumentation(startNodeParam.toString());

        // 结束节点
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent");
        endEvent.setName("流程结束");

        SequenceFlow innerEndSequenceFlow = genarateSequenceFlow(
            PromotionContants.ACTIVITI_SEQUENCE_FLOW_PREFIX + startEvent.getId() + "_" + endEvent.getId(), "",
            startEvent.getId(), endEvent.getId(), "", process);
        startEvent.getOutgoingFlows().add(innerEndSequenceFlow);
        endEvent.getIncomingFlows().add(innerEndSequenceFlow);

        // Process对象
        process.addFlowElement(startEvent);
        process.addFlowElement(innerEndSequenceFlow);
        process.addFlowElement(endEvent);
        return bpmnModel;

    }

    /**
     * 将BpmnModel转成xml文件格式字符串
     *
     * @param bpmnModel
     * @return String
     * @author PQ
     * @date 2019/6/3
     */
    public static String getBpmnModelStrFromModel(BpmnModel bpmnModel) {
        // bpmnModel转换为标准的bpmn.xml文件内容
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        String bpmnModelStr = "";
        try {
            bpmnModelStr = new String(bpmnXMLConverter.convertToXML(bpmnModel), "UTF-8");
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return bpmnModelStr;
    }

    /**
     * 将前台定义的营销活动JSON数据，转换成标准BPMN格式数据
     *
     * @param marketingConfigJson
     * @param marketingDefineId
     * @return String
     * @author PQ
     * @date 2019/6/3
     */
    public static String getBpmnModelStrFromJson(String marketingConfigJson, Long marketingDefineId) {
        // bpmnModel转换为标准的bpmn.xml文件内容
        return getBpmnModelStrFromModel(getBpmnModelFromJson(marketingConfigJson, marketingDefineId));
    }

    /**
     * 将前台定义的营销活动JSON数据，转换成标准BPMN格式数据
     *
     * @param marketingConfigJson
     * @param marketingDefineId
     * @return org.activiti.bpmn.model.BpmnModel
     * @author PQ
     * @date 2019/6/3
     */
    public static BpmnModel getBpmnModelFromJson(String marketingConfigJson, Long marketingDefineId) {
        Process process = new Process();
        process.setId(PromotionContants.ACTIVITI_PROCESS_PREFIX + marketingDefineId);
        // 实例化BpmnModel对象
        BpmnModel bpmnModel = new BpmnModel();
        bpmnModel.addProcess(process);

        // 组装bpmnModel数据
        JSONArray jsonArray = null;
        try {
            JSONObject originalJson = JSON.parseObject(marketingConfigJson);
            jsonArray = originalJson.getJSONArray("children");
            if (jsonArray == null || jsonArray.size() < 1) {
                throw new BusiException();
            }

            // 开始节点
            StartEvent startEvent = new StartEvent();
            startEvent.setId("startEvent");
            startEvent.setName("流程开始");
            // 为开始节点设置参数
            JSONObject startNodeParam = new JSONObject();
            startNodeParam.put("marketingDefineId", marketingDefineId);
            startEvent.setDocumentation(startNodeParam.toString());
            // 开始节点增加监听处理器，用于显示流程启动（可用于后续业务扩展）
            List<ActivitiListener> startExecutionListeners = Lists.newArrayList();
            ActivitiListener startActivitiListener = new ActivitiListener();
            startActivitiListener.setEvent("start");
            startActivitiListener.setImplementationType("expression");
            startActivitiListener.setImplementation(generateActivitiExpression(EmptyActionHandler.class));

            startExecutionListeners.add(startActivitiListener);
            startEvent.setExecutionListeners(startExecutionListeners);

            // 包容网关Join节点
            InclusiveGateway endInclusiveGateway = new InclusiveGateway();
            endInclusiveGateway.setId("endInclusiveGateway");
            endInclusiveGateway.setName("流程结束汇聚节点");

            // 结束节点
            EndEvent endEvent = new EndEvent();
            endEvent.setId("endEvent");
            endEvent.setName("流程结束");
            // 结束节点增加监听处理器，用于更新事件触发历史记录表完成状态
            List<ActivitiListener> executionListeners = Lists.newArrayList();
            ActivitiListener endActivitiListener = new ActivitiListener();
            endActivitiListener.setEvent("start");
            endActivitiListener.setImplementationType("expression");
            endActivitiListener.setImplementation(generateActivitiExpression(FlowFinishActionHandler.class));

            executionListeners.add(endActivitiListener);
            endEvent.setExecutionListeners(executionListeners);

            // 连接包容网关Join节点和最终结束节点
            SequenceFlow innerEndSequenceFlow = genarateSequenceFlow(
                PromotionContants.ACTIVITI_SEQUENCE_FLOW_PREFIX + endInclusiveGateway.getId() + "_" + endEvent.getId(),
                "", endInclusiveGateway.getId(), endEvent.getId(), "", process);
            endInclusiveGateway.getOutgoingFlows().add(innerEndSequenceFlow);
            endEvent.getIncomingFlows().add(innerEndSequenceFlow);

            // Process对象
            process.addFlowElement(startEvent);
            process.addFlowElement(endInclusiveGateway);
            process.addFlowElement(innerEndSequenceFlow);
            process.addFlowElement(endEvent);

            Integer seq = 0;
            Map<String, Integer> seqMap = new HashMap<>(1);
            seqMap.put("seq", seq);

            jsonArray.forEach(childJsonObj -> {
                JSONObject childJson = (JSONObject) childJsonObj;
                parseNodeJson(childJson, process, seqMap, null, marketingDefineId);
            });
            // 自动布局
            new BpmnAutoLayout(bpmnModel).execute();
        }
        catch (BusiException busiException) {
            throw busiException;
        }
        catch (Exception e) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0035");
        }
        return bpmnModel;
    }

    /**
     * 解析节点JSON
     * 
     * @param nodeJson 节点JSON对象
     * @param process 流程执行配置
     * @param seqMap 执行步骤
     * @param parentNode 父节点
     * @param marketingDefineId 流程定义ID
     */
    private static void parseNodeJson(JSONObject nodeJson, Process process, Map<String, Integer> seqMap,
        FlowNode parentNode, Long marketingDefineId) throws BusiException {
        Integer seq = seqMap.get("seq");
        nodeJson.put("seq", seq);
        FlowNode innerParentNode = parentNode;

        Integer nodeType = nodeJson.getInteger("nodeType");
        // 根据JSON节点类型，构造相应流程节点:
        if (null == nodeType) {
            if (null == parentNode) {
                ExceptionHandler.publish("NROS-SBC-PROMOTION-0035");
            }
            else {
                SequenceFlow innerEndSequenceFlow = genarateSequenceFlow(
                    PromotionContants.ACTIVITI_SEQUENCE_FLOW_PREFIX + seq, "", parentNode.getId(),
                    "endInclusiveGateway", "", process);
                parentNode.getOutgoingFlows().add(innerEndSequenceFlow);
                // 为流程结束节点添加流入线
                ((InclusiveGateway) process.getFlowElement("endInclusiveGateway")).getIncomingFlows()
                    .add(innerEndSequenceFlow);
                process.addFlowElement(innerEndSequenceFlow);
                seqMap.put("seq", ++seq);

            }
        }
        else {
            // 是否跳过当前时间节点
            boolean skipCurrentTimerNode = false;
            if (MarketingNodeTypeEnum.TIMERNODE.getNodeType().equals(nodeType)) {
                JSONObject timeData = nodeJson.getJSONObject("timeData");
                if (Objects.isNull(timeData)) {
                    ExceptionHandler.publish("NROS-SBC-PROMOTION-0035");
                }
                TimerNode timerNode = new TimerNode(timeData);
                // 当节点类型为时间节点&&为主动营销首节点&&存在周期性设置时,将定时器设置在startEvent，以使流程能正常周期性执行
                if (StringUtil.isNotNull(timerNode.getCrontab())) {
                    StartEvent startEvent = (StartEvent) process.getFlowElement("startEvent");
                    // 增加定时器设置
                    TimerEventDefinition timerEventDefinition = new TimerEventDefinition();
                    timerEventDefinition.setTimeCycle(timerNode.getCrontab());
                    // 设置循环结束时间
                    if (null != timerNode.getTo()) {
                        timerEventDefinition.setEndDate(
                            DateUtil.dateToStr(timerNode.getTo(), DateUtil.DEFAULT_DATE_FORMAT) + "T23:59:59");
                    }
                    // 为开始事件设置周期定时器
                    startEvent.setEventDefinitions(Lists.newArrayList(timerEventDefinition));
                    skipCurrentTimerNode = true;
                }
                /**
                 * String fromDate = timeData.getString("from"); if (!StringUtils.isEmpty(fromDate)) {
                 * IntermediateCatchEvent innerTimerStartFlowNode = new IntermediateCatchEvent();
                 * innerTimerStartFlowNode .setId(PromotionContants.ACTIVITI_TIMER_NODE_PREFIX + seq + "_" +
                 * nodeJson.getString("id")); innerTimerStartFlowNode.setName(nodeJson.getString("nodeDesc"));
                 * TimerEventDefinition timerEventDefinition = new TimerEventDefinition();
                 * timerEventDefinition.setTimeDate(fromDate);
                 * innerTimerStartFlowNode.setEventDefinitions(Lists.newArrayList(timerEventDefinition));
                 * process.addFlowElement(innerTimerStartFlowNode); SequenceFlow innerTimerSequenceFlow = null; if
                 * (parentNode == null) { // 营销活动起始节点 innerTimerSequenceFlow = genarateSequenceFlow(
                 * PromotionContants.ACTIVITI_SEQUENCE_FLOW_PREFIX + innerTimerStartFlowNode.getId(), "", "startEvent",
                 * innerTimerStartFlowNode.getId(), "", process); // 为流程开始节点添加流出线 ((StartEvent)
                 * process.getFlowElement("startEvent")).getOutgoingFlows() .add(innerTimerSequenceFlow); } else {
                 * innerTimerSequenceFlow = genarateSequenceFlow( PromotionContants.ACTIVITI_SEQUENCE_FLOW_PREFIX +
                 * innerTimerStartFlowNode.getId(), "", parentNode.getId(), innerTimerStartFlowNode.getId(), "",
                 * process); // 父节点增加流出线 parentNode.getOutgoingFlows().add(innerTimerSequenceFlow); } // 为当前节点增加流入线
                 * innerTimerStartFlowNode.getIncomingFlows().add(innerTimerSequenceFlow); // 增加当前流程线节点到执行流程
                 * process.addFlowElement(innerTimerSequenceFlow); innerParentNode = innerTimerStartFlowNode; }
                 */
            }
            if (innerParentNode == null) {
                // 如果父节点为空，则将其设置为起始节点
                innerParentNode = (StartEvent) process.getFlowElement("startEvent");
            }

            MarketingNodeTypeEnum currentNodeTypeEnum = skipCurrentTimerNode ? MarketingNodeTypeEnum.EMPTYNODE
                : MarketingNodeTypeEnum.getByNodeType(nodeType);

            FlowNode currentFlowNode = currentNodeTypeEnum.getMarketingNode(nodeJson, innerParentNode.getId(),
                marketingDefineId);
            process.addFlowElement(currentFlowNode);
            // 当前节点为筛选或条件判断分支时，需针对目标用户为空的情况增加结束流程分支线
            if (MarketingNodeTypeEnum.FILTERNODE.getNodeType().equals(nodeType)
                || MarketingNodeTypeEnum.JUDGENODE_Y.getNodeType().equals(nodeType)
                || MarketingNodeTypeEnum.JUDGENODE_N.getNodeType().equals(nodeType)
                || MarketingNodeTypeEnum.GROUPBRANCHNODE.getNodeType().equals(nodeType)) {
                String flowExpression = currentNodeTypeEnum.getFlowExpressionNo();
                SequenceFlow currentJudgeSequenceFlow = genarateSequenceFlow(
                    PromotionContants.ACTIVITI_SEQUENCE_FLOW_PREFIX + seq, "目标用户为空", currentFlowNode.getId(),
                    "endInclusiveGateway", flowExpression, process);
                currentFlowNode.getOutgoingFlows().add(currentJudgeSequenceFlow);
                // 为流程结束节点添加流入线
                ((InclusiveGateway) process.getFlowElement("endInclusiveGateway")).getIncomingFlows()
                    .add(currentJudgeSequenceFlow);
                process.addFlowElement(currentJudgeSequenceFlow);
                seqMap.put("seq", ++seq);
            }

            // 当前节点和父节点连线的条件表达式值
            String currentSequenceFlowExpression = null;
            if (innerParentNode.getId().startsWith(PromotionContants.ACTIVITI_FILTER_NODE_PREFIX)
                || innerParentNode.getId().startsWith(PromotionContants.ACTIVITI_GROUP_BRANCH_NODE_PREFIX)
                || innerParentNode.getId().startsWith(PromotionContants.ACTIVITI_JUDGE_NODE_YES_PREFIX)
                || innerParentNode.getId().startsWith(PromotionContants.ACTIVITI_JUDGE_NODE_NO_PREFIX)) {
                currentSequenceFlowExpression = "${hasTargetUserYes==true}";
            }
            SequenceFlow currentSequenceFlow = genarateSequenceFlow(
                PromotionContants.ACTIVITI_SEQUENCE_FLOW_PREFIX + seq, "", innerParentNode.getId(),
                currentFlowNode.getId(), currentSequenceFlowExpression, process);
            // 父节点增加流出线
            innerParentNode.getOutgoingFlows().add(currentSequenceFlow);
            // 为当前节点增加流入线
            currentFlowNode.getIncomingFlows().add(currentSequenceFlow);
            // 增加当前流程线节点到执行流程
            process.addFlowElement(currentSequenceFlow);
            seqMap.put("seq", ++seq);

            JSONArray jsonArray = nodeJson.getJSONArray("children");
            // 若存在非叶子子节点，递归处理
            if (!CollectionUtils.isEmpty(jsonArray) && (StringUtils.equals(nodeJson.getString("isLast"), "0"))) {
                for (Object childJsonObj : jsonArray) {
                    JSONObject childJson = (JSONObject) childJsonObj;
                    parseNodeJson(childJson, process, seqMap, currentFlowNode, marketingDefineId);
                }
            }
            else {
                // 流程尾节点
                SequenceFlow endSequenceFlow = genarateSequenceFlow(
                    PromotionContants.ACTIVITI_SEQUENCE_FLOW_PREFIX + seq, "", currentFlowNode.getId(),
                    "endInclusiveGateway", "", process);
                // 为流程结束节点添加流入线
                ((InclusiveGateway) process.getFlowElement("endInclusiveGateway")).getIncomingFlows()
                    .add(endSequenceFlow);
                process.addFlowElement(endSequenceFlow);
                seqMap.put("seq", ++seq);
            }
        }

    }

    /**
     * 创建连线
     */
    private static SequenceFlow genarateSequenceFlow(String id, String name, String sourceRef, String tartgetRef,
        String conditionExpression, Process process) {
        SequenceFlow sequenceFlow = new SequenceFlow(sourceRef, tartgetRef);
        sequenceFlow.setId(id);
        if (!StringUtils.isEmpty(name)) {
            sequenceFlow.setName(name);
        }
        // #5.22.0版本移除
        sequenceFlow.setSourceFlowElement(process.getFlowElement(sourceRef));
        sequenceFlow.setTargetFlowElement(process.getFlowElement(tartgetRef));
        if (!StringUtils.isEmpty(conditionExpression)) {
            sequenceFlow.setConditionExpression(conditionExpression);
        }
        return sequenceFlow;
    }

    /**
     * 将流程执行配置JSON转换为营销预览配置JSON
     *
     * @param excuteConfigJson 流程执行配置JSON
     * @return 流程执行配置JSON
     */
    public static JSONObject convertExcuteToPreviewJson(String excuteConfigJson) {
        JSONObject rootJson = new JSONObject();
        JSONObject resultTree = null;
        JSONArray jsonArray = JSON.parseArray(excuteConfigJson);
        // List<JSONObject> trees = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject treeNode = (JSONObject) jsonArray.getJSONObject(i);
            if (null == treeNode.getInteger("parentId")) {
                resultTree = treeNode;
                break;
            }
        }
        if (resultTree != null) {
            findJsonChildren(resultTree, jsonArray);
            JSONArray rootChildren = new JSONArray();
            rootChildren.add(resultTree);
            rootJson.put("children", rootChildren);
        }

        return rootJson;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static JSONObject findJsonChildren(JSONObject treeNode, JSONArray treeNodes) {
        for (int j = 0; j < treeNodes.size(); j++) {
            JSONObject it = (JSONObject) treeNodes.getJSONObject(j);
            if (treeNode.getInteger("id").equals(it.getInteger("parentId"))) {
                if (treeNode.getJSONArray("children") == null) {
                    treeNode.put("children", new JSONArray());
                }
                treeNode.getJSONArray("children").add(findJsonChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    /**
     * 获取一个class类在spring中的默认bean名称
     *
     * @param clazz
     * @return java.lang.String
     * @author PQ
     * @date 2019/6/27
     */
    public static String getClassSpringBeanId(Class clazz) {
        String orgStr = clazz.getSimpleName();
        if (Character.isLowerCase(orgStr.charAt(0))) {
            return orgStr;
        }
        else {
            return (new StringBuilder()).append(Character.toLowerCase(orgStr.charAt(0))).append(orgStr.substring(1))
                .toString();
        }
    }

    /**
     * 组装指定expression表达式，形如：${springbean.execute(JaveExecution,param)}
     *
     * @param clazz
     * @return java.lang.String
     * @author PQ
     * @date 2019/6/27
     */
    public static String generateActivitiExpression(Class clazz) {
        StringBuilder stringBuilder = new StringBuilder("${");
        stringBuilder.append(getClassSpringBeanId(clazz));
        stringBuilder.append(".execute(execution)}");
        // stringBuilder.append(expressionParam.toJSONString().replaceAll("\\\\", "\\\\\\\\"));
        return stringBuilder.toString();
    }

    /**
     * 组装流程节点document内容
     *
     * @param jsonObject
     * @param parentActivityId
     * @param marketingDefineId
     * @return String
     * @author PQ
     * @date 2019/6/28
     */
    public static String generateFlowNodeDocument(String jsonObject, String parentActivityId, Long marketingDefineId) {
        JSONObject documentJsonObject = StringUtil.isNull(jsonObject) ? new JSONObject()
            : JSONObject.parseObject(jsonObject);
        documentJsonObject.put("parentActivityId", parentActivityId);
        documentJsonObject.put("marketingDefineId", marketingDefineId);
        return documentJsonObject.toString();
    }

    /**
     * 将流程定义数据部署到activiti
     *
     * @param bpmnJsonStr json格式流程定义内容
     * @param marketingDefineLd 流程定义ID
     * @return void
     * @author PQ
     * @date 2019/7/2
     */
    public static void deployProcess(String bpmnJsonStr, Long marketingDefineLd) {
        String activitiProcessKey = PromotionContants.ACTIVITI_PROCESS_PREFIX + marketingDefineLd;
        BpmnModel bpmnModel = BpmnModelUtil.getBpmnModelFromJson(bpmnJsonStr, marketingDefineLd);
        deployProcess(bpmnModel, activitiProcessKey);
    }

    /**
     * 部署流程定义
     * 
     * @param bpmnModel
     * @param activitiProcessKey
     * @return void
     * @author PQ
     * @date 2019/7/17
     */
    public static void deployProcess(BpmnModel bpmnModel, String activitiProcessKey) {
        RepositoryService repositoryService = SpringContextUtils.getBean(RepositoryService.class);
        // 创建部署对象
        Deployment deployment = repositoryService.createDeployment()
            .addBpmnModel(activitiProcessKey + PromotionContants.ACTIVITI_PROCESS_FILE_SUFFIX, bpmnModel).deploy(); // 完成部署
        if (Objects.isNull(deployment)) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0013");
        }
        logger.info("BpmnModelUtil.deployProcess()……activiti deployed:{}", deployment.getId());
    }

    /**
     * 启动activiti流程实例
     *
     * @param marketingDefineDTO
     * @return void
     * @author PQ
     * @date 2019/7/2
     */
    public static void startActivitiProcess(MarketingDefineParam marketingDefineDTO) {
        RepositoryService repositoryService = SpringContextUtils.getBean(RepositoryService.class);
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
            .processDefinitionKey(PromotionContants.ACTIVITI_PROCESS_PREFIX + marketingDefineDTO.getId())
            .latestVersion().list();
        if (!org.springframework.util.CollectionUtils.isEmpty(processDefinitionList)) {
            RuntimeService runtimeService = SpringContextUtils.getBean(RuntimeService.class);
            Map<String, Object> variableMap = Maps.newHashMap();
            variableMap.put("marketingDefineId", marketingDefineDTO.getId());
            variableMap.put("marketingType", marketingDefineDTO.getMarketingType());
            variableMap.put("merchantCode", marketingDefineDTO.getMerchantCode());
            //runtimeService.startProcessInstanceByKey(
            //    PromotionContants.ACTIVITI_PROCESS_PREFIX + marketingDefineDTO.getId(), variableMap);
             runtimeService.startProcessInstanceById(processDefinitionList.get(0).getId(), variableMap);
            logger.info("###############startActiviti:{} ####################", processDefinitionList.get(0).getId());
        }
    }

}
