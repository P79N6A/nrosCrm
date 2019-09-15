package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 营销流程定义保存参数
 * 
 * @author wangzhe
 * @date 2019/4/17 15:58
 */
@Data
@ApiModel("营销流程定义保存参数")
public class MarketingDefineParam extends BaseModel implements Serializable {

    /**
     * 营销流程定义-标识
     */
    @ApiModelProperty(value = "营销流程ID", example = "123")
    private Long id;

    /**
     * 营销流程定义-模板ID
     */
    @ApiModelProperty(value = "模板ID", example = "模板ID")
    private Long templateId;

    /**
     * 营销流程定义-保存时是否启用:[0]保存为草稿,[1]保存并启用
     */
    @ApiModelProperty(value = "保存时是否启用", example = "保存时是否启用:[0]保存为草稿,[1]保存并启用")
    private String isEnable;

    /**
     * 营销流程定义-营销流程名称
     */
    @ApiModelProperty(value = "营销流程名称", example = "营销活动名称")
    private String marketingName;

    /**
     * 营销流程定义-营销流程备注
     */
    @ApiModelProperty(value = "营销流程备注", example = "营销活动目标")
    private String marketingRemark;

    /**
     * 营销流程定义-订单分析起始
     */
    @ApiModelProperty(value = "订单分析起始", example = "2019-04-10 19:12:28")
    private Date analysisStart;

    /**
     * 营销流程定义-订单分析结束
     */
    @ApiModelProperty(value = "订单分析结束", example = "2019-04-19 19:12:28")
    private Date analysisEnd;

    /**
     * 营销流程定义-是否相对日期:[0]否,[1]是
     */
    @ApiModelProperty(value = "是否相对日期:[0]否,[1]是", example = "2019-04-19 19:12:28")
    private String isRelative;

    /**
     * 营销流程定义-向前推移天数
     */
    @ApiModelProperty(value = "向前推移天数")
    private Integer beforeCount;

    /**
     * 营销流程定义-向后推移天数
     */
    @ApiModelProperty(value = "向后推移天数")
    private Integer afterCount;

    /**
     * 营销流程定义-营销类型:[1]主动营销,[2]事件营销
     */
    @ApiModelProperty(value = "营销类型", required = true, example = "营销类型:[1]主动营销,[2]事件营销")
    private String marketingType;

    /**
     * 营销流程定义-营销配置json信息
     */
    @ApiModelProperty(value = "营销配置json信息", example = "营销配置json信息")
    private String marketingConfigJson;

    /**
     * 营销流程定义-流程执行配置json信息
     */
    @ApiModelProperty(value = "流程执行配置json信息", example = "流程执行配置json信息")
    private String excuteConfigJson;

    /**
     * 营销流程定义-是否永久有效:[0]否,[1]是
     */
    @ApiModelProperty(value = "是否永久有效", example = "是否永久有效:[0]否,[1]是")
    private String isAlwaysValid;

    /**
     * 营销流程定义-开始时间
     */
    @ApiModelProperty(value = "开始时间", example = "2019-04-10 19:12:28")
    private Date starttime;

    /**
     * 营销流程定义-结束时间
     */
    @ApiModelProperty(value = "结束时间", example = "2019-04-19 19:12:28")
    private Date finishtime;

    /**
     * 营销流程定义-触发频次类型:[0]不限制,[1]每个客户仅能触发一次,[2]每个客户在一段时间内仅触发一次
     */
    @ApiModelProperty(value = "触发频次类型", example = "触发频次类型:[0]不限制,[1]每个客户仅能触发一次,[2]每个客户在一段时间内仅触发一次")
    private String frequenceType;

    /**
     * 营销流程定义-频次数量
     */
    @ApiModelProperty(value = "频次数量", example = "频次数量")
    private Integer frequenceCount;

    /**
     * 营销流程定义-频次单位:[0]天,[1]周,[2]月
     */
    @ApiModelProperty(value = "频次单位", example = "频次单位:[0]天,[1]周,[2]月")
    private String frequenceUnit;

    /**
     * 营销流程定义-营销流程状态:[0]设计中,[1]生效,[2]失效
     */
    @ApiModelProperty(value = "营销流程状态", example = "营销流程状态:[0]设计中,[1]生效,[2]失效")
    private String marketingStatus;
}