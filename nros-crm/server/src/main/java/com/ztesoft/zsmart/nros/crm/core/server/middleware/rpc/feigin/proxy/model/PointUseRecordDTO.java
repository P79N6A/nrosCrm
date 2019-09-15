package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

/**
  * @description 积分使用记录DTO
  * @author wang.yulin01
  * @date 2019/6/2 14:05
  * @version V1.0
**/
@Data
public class PointUseRecordDTO extends BaseModel implements Serializable {
    /**
     * PointUseRecordDO-member_id
     */
    private Long memberId;

    /**
     * PointUseRecordDO-use_point
     */
    private Integer usePoint;

    /**
     * PointUseRecordDO-biz_order
     */
    private String bizOrder;

    /**
     * PointUseRecordDO-description
     */
    private String description;

    /**
     * PointUseRecordDO-point_type
     */
    private String pointType;
}
