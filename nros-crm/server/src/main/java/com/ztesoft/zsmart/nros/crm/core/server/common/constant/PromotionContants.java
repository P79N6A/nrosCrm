package com.ztesoft.zsmart.nros.crm.core.server.common.constant;

/**
 * 常量定义
 * 
 * @author PQ
 * @date 2019/4/19
 */
public class PromotionContants {
    /**
     * 注册渠道
     */
    // 不限制渠道
    public static final Long REGISTRY_TYPE_NOLIMIT = 0L;

    // 默认渠道 微信注册
    public static final Long REGISTRY_TYPE_DEFAULT = 16L;

    // 系统自动操作用户
    public static final Long SYSTEM_AUTO_OPERATOR = -1L;

    // 积分变化说明：注册送积分
    public static final String POINTS_REASON_NAME_MARKETING = "送积分营销活动";

    // activiti bpmnModel自定义
    public static final String ACTIVITI_PROCESS_PREFIX = "activitiProcess_";

    public static final String ACTIVITI_PROCESS_FILE_SUFFIX = ".bpmn";

    public static final String ACTIVITI_SERVICE_TASK_NODE_PREFIX = "serviceTask_";

    public static final String ACTIVITI_SEQUENCE_FLOW_PREFIX = "seqFlow_";

    public static final String ACTIVITI_TIMER_NODE_PREFIX = "timerNode_";

    public static final String ACTIVITI_EVENT_NODE_PREFIX = "eventNode_";

    public static final String ACTIVITI_FILTER_NODE_PREFIX = "filterNode_";

    public static final String ACTIVITI_ACTION_NODE_PREFIX = "actionNode_";

    public static final String ACTIVITI_GROUP_MASTER_NODE_PREFIX = "groupMasterNode_";

    public static final String ACTIVITI_GROUP_BRANCH_NODE_PREFIX = "groupBranchNode_";

    public static final String ACTIVITI_JUDGE_NODE_PREFIX = "judgeNode_";

    public static final String ACTIVITI_JUDGE_NODE_YES_PREFIX = "judgeYesNode_";

    public static final String ACTIVITI_JUDGE_NODE_NO_PREFIX = "judgeNoNode_";

    // 阿里网关发送短信手机号码数量限制
    public static final Integer ALI_SEND_SMS_LIMIT_SIZE = 1000;

    // 阿里网关批量发送短信手机号码数量限制
    public static final Integer ALI_SEND_BATCH_SMS_LIMIT_SIZE = 100;

    // 最大分页大小限制
    public static final Integer MAX_QUERY_PAGE_SIZE = 2000;

}
