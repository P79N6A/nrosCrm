package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 可预约时间段参数
 * @author fan.chaolin
 * @date 2019/4/15
 */
@Data
@ApiModel("活动可预约时间段参数")
public class CampaignReserveTimeParam extends BaseModel implements Serializable {

    /**
     * 预约开始时间
     */
    private Date reserveStartTime;

    /**
     * 预约结束时间
     */
    private Date reserveEndTime;

    @ApiModelProperty(value = "活动ID")
    private Long campaignId;

    private static final long serialVersionUID = 1L;

}
