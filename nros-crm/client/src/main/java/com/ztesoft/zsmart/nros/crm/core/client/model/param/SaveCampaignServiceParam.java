package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 报名活动报名、签到参数
 *
 * @author yangkai
 * @date 2019-06-17
 *
 */
@Data
@ApiModel("保存活动详情参数")
public class SaveCampaignServiceParam extends BaseModel implements Serializable {

    /**
     * 报名姓名
     */
    @ApiModelProperty(value = "报名姓名")
    private String signName;

    /**
     * 报名电话
     */
    @ApiModelProperty(value = "报名电话")
    private String signPhone;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Long campaignId;

    /**
     * 签到码
     */
    @ApiModelProperty(value = "签到码")
    private String signInCode;

    /**
     * 是否已签到
     */
    @ApiModelProperty(value = "是否已签到")
    private String isSignIn;

    /**
     * 预约门店id
     */
    @ApiModelProperty(value = "预约门店id")
    private Long reservationStoreId;

    /**
     * 预约时间段id
     */
    @ApiModelProperty(value = "预约时间段id")
    private Long reservationTimeId;
}
