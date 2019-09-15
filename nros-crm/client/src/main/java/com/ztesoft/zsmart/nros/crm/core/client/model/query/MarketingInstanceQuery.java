package com.ztesoft.zsmart.nros.crm.core.client.model.query;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 营销流程定义执行实例查询
 *
 * @author PQ
 * @date 2019/7/24
 */
@Data
@ApiModel("营销流程实例统计分析查询参数")
public class MarketingInstanceQuery implements Serializable {

    /**
     * 营销流程定义ID
     */
    @ApiModelProperty(value = "营销流程定义ID", required = true)
    private Long marketingId;

    /**
     * 
     */
    @ApiModelProperty(value = "营销流程执行实例ID", required = true)
    private String marketingInstanceId;

    /**
     * 执行节点类型
     */
    private String executeNodeType;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

}