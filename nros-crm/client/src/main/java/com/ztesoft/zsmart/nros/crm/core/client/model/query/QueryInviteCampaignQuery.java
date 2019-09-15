package com.ztesoft.zsmart.nros.crm.core.client.model.query;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动查询参数
 * @date 2019-04-11
 */
@Data
public class QueryInviteCampaignQuery extends BaseQuery implements Serializable {

    @ApiModelProperty(value = "活动名称", dataType = "String", example = "邀请新用户送券")
    private String name;

    @ApiModelProperty(value = "活动编码", dataType = "Long")
    private Long campaignId;

    @ApiModelProperty(value = "注册起始时间", dataType = "String")
    private String registerStartTime;

    @ApiModelProperty(value = "注册结束时间", dataType = "String")
    private String registerEndTime;

}
