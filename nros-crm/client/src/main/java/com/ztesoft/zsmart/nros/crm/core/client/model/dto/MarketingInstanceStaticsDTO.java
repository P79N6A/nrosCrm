package com.ztesoft.zsmart.nros.crm.core.client.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 营销流程实例统计分析DTO
 * 
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-07-24
 */
@Data
public class MarketingInstanceStaticsDTO implements Serializable {
    /**
     * 流程定义ID
     */
    private Long marketingId;

    /**
     * 流程实例ID
     */
    private String marketingInstanceId;

    /**
     * 节点类型
     */
    private String nodeType;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 总目标人次
     */
    private Long totalTargetUser;

    /**
     * 总执行动作统计
     */
    private Long totalTargetAction;

    /**
     * 统计分析明细
     */
    private List<MarketingInstanceStaticsDetailDTO> details;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;
}