package com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import java.io.Serializable;

/**
 * 执行动作与渠道关联
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-02
 */
public class ActionnRelationDO extends BaseModel implements Serializable {
    /**
     * 执行动作与渠道关联-动作预制表ID
     */
    private Long actionPresetId;

    /**
     * 执行动作与渠道关联-渠道记录ID
     */
    private Long campaignChannelId;

    /**
     * 执行动作与渠道关联-节点顺序
     */
    private Integer seq;

    /**
     * 执行动作与渠道关联-营销流程实例历史ID
     */
    private Long marketingInstanceHisId;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取：执行动作与渠道关联-动作预制表ID
     *
     * @return 动作预制表ID
     */
    public Long getActionPresetId() {
        return actionPresetId;
    }

    /**
     * 设置：执行动作与渠道关联-动作预制表ID
     *
     * @param actionPresetId 执行动作与渠道关联-动作预制表ID
     */
    public void setActionPresetId(Long actionPresetId) {
        this.actionPresetId = actionPresetId;
    }

    /**
     * 获取：执行动作与渠道关联-渠道记录ID
     *
     * @return 渠道记录ID
     */
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    /**
     * 设置：执行动作与渠道关联-渠道记录ID
     *
     * @param campaignChannelId 执行动作与渠道关联-渠道记录ID
     */
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    /**
     * 获取：执行动作与渠道关联-节点顺序
     *
     * @return 节点顺序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置：执行动作与渠道关联-节点顺序
     *
     * @param seq 执行动作与渠道关联-节点顺序
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取：执行动作与渠道关联-营销流程实例历史ID
     *
     * @return 营销流程实例历史ID
     */
    public Long getMarketingInstanceHisId() {
        return marketingInstanceHisId;
    }

    /**
     * 设置：执行动作与渠道关联-营销流程实例历史ID
     *
     * @param marketingInstanceHisId 执行动作与渠道关联-营销流程实例历史ID
     */
    public void setMarketingInstanceHisId(Long marketingInstanceHisId) {
        this.marketingInstanceHisId = marketingInstanceHisId;
    }
}