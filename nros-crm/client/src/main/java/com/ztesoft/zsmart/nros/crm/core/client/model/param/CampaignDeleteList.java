package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhou.xiaofeng
 * @description 删除活动参数
 * @date 2019-06-11
 */
@Data
@ApiModel("活动删除列表")
public class CampaignDeleteList extends BaseModel {
    @ApiModelProperty(value = "活动编码列表", required = true)
    List<Long> campaignIds;
}