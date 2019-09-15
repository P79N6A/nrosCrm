package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhou.xiaofeng
 * @description 活动删除参数
 * @date 2019-06-12
 */
@Data
@ApiModel("活动删除对象")
public class CampaignDeleteParam extends BaseModel implements Serializable {

    @ApiModelProperty(value = "活动ID", example = "活动ID")
    private Long id;
}
