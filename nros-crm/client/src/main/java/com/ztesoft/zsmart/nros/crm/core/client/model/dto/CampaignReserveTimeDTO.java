package com.ztesoft.zsmart.nros.crm.core.client.model.dto;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 可预约时间段DTO
 * @author fan.chaolin
 * @date 2019/4/16
 */
@Data
public class CampaignReserveTimeDTO extends BaseModel implements Serializable {

    private Date reserveStartTime;

    private Date reserveEndTime;

    private Long campaignId;

    private static final long serialVersionUID = 1L;
}
