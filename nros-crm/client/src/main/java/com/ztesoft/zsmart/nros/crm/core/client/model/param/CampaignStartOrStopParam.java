package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhou.xiaofeng
 * @description 活动启用停止参数
 * @date 2019-06-11
 */
@Data
@ApiModel("活动启用停用参数")
public class CampaignStartOrStopParam extends BaseModel {
    @ApiModelProperty(value = "活动编码", required = true)
    private Long id;
    @ApiModelProperty(value = "活动状态", required = true, example = "[0]设计中,[1]审核中,[2]审核不通过,[3]未开始,[4]进行中,[5]暂停中,[6]已结束")
    private String campaignState;
}
