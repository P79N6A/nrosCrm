package com.ztesoft.zsmart.nros.crm.core.client.model.query;

import com.ztesoft.zsmart.nros.common.model.param.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 营销流程定义列表查询
 * 
 * @author fan.chaolin
 * @date 2019/4/18
 */
@Data
@ApiModel("营销流程定义列表查询参数")
public class MarketingDefineListQuery extends PageParam implements Serializable {

    /**
     * 营销流程定义-营销流程名称
     */
    @ApiModelProperty(value = "营销流程名称", example = "营销流程名称")
    private String marketingName;

    /**
     * 营销流程定义-营销流程状态:[0]设计中,[1]生效,[2]失效
     */
    @ApiModelProperty(value = "营销流程状态", example = "营销流程状态:[0]设计中,[1]生效,[2]失效")
    private String marketingStatus;

    /**
     * 营销流程定义-营销类型:[1]主动营销,[2]事件营销
     */
    @ApiModelProperty(value = "营销类型", example = "营销类型:[1]主动营销,[2]事件营销")
    private String marketingType;
}
