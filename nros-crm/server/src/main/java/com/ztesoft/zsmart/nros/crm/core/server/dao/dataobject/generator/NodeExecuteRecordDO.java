package com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import java.io.Serializable;

/**
 * 活动节点执行记录
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-07-25
 */
public class NodeExecuteRecordDO extends BaseModel implements Serializable {
    /**
     * 活动节点执行记录-node_id
     */
    private String nodeId;

    /**
     * 活动节点执行记录-node_type
     */
    private String nodeType;

    /**
     * 活动节点执行记录-node_name
     */
    private String nodeName;

    /**
     * 活动节点执行记录-marketing_id
     */
    private Long marketingId;

    /**
     * 活动节点执行记录-marketing_instance_id
     */
    private String marketingInstanceId;

    /**
     * 活动节点执行记录-param
     */
    private String param;

    /**
     * 活动节点执行记录-result
     */
    private String result;

    /**
     * 活动节点执行记录-target_user_count
     */
    private Long targetUserCount;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取：活动节点执行记录-node_id
     *
     * @return node_id
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 设置：活动节点执行记录-node_id
     *
     * @param nodeId
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    /**
     * 获取：活动节点执行记录-node_type
     *
     * @return node_type
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 设置：活动节点执行记录-node_type
     *
     * @param nodeType
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType == null ? null : nodeType.trim();
    }

    /**
     * 获取：活动节点执行记录-node_name
     *
     * @return node_name
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 设置：活动节点执行记录-node_name
     *
     * @param nodeName
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    /**
     * 获取：活动节点执行记录-marketing_id
     *
     * @return marketing_id
     */
    public Long getMarketingId() {
        return marketingId;
    }

    /**
     * 设置：活动节点执行记录-marketing_id
     *
     * @param marketingId
     */
    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    /**
     * 获取：活动节点执行记录-marketing_instance_id
     *
     * @return marketing_instance_id
     */
    public String getMarketingInstanceId() {
        return marketingInstanceId;
    }

    /**
     * 设置：活动节点执行记录-marketing_instance_id
     *
     * @param marketingInstanceId
     */
    public void setMarketingInstanceId(String marketingInstanceId) {
        this.marketingInstanceId = marketingInstanceId == null ? null : marketingInstanceId.trim();
    }

    /**
     * 获取：活动节点执行记录-param
     *
     * @return param
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置：活动节点执行记录-param
     *
     * @param param
     */
    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    /**
     * 获取：活动节点执行记录-result
     *
     * @return result
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置：活动节点执行记录-result
     *
     * @param result
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    /**
     * 获取：活动节点执行记录-target_user_count
     *
     * @return target_user_count
     */
    public Long getTargetUserCount() {
        return targetUserCount;
    }

    /**
     * 设置：活动节点执行记录-target_user_count
     *
     * @param targetUserCount
     */
    public void setTargetUserCount(Long targetUserCount) {
        this.targetUserCount = targetUserCount;
    }
}