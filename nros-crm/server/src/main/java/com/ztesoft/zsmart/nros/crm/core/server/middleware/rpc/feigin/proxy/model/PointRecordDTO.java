package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
  * @description 积分记录DTO
  * @author wang.yulin01
  * @date 2019/6/2 13:20
  * @version V1.0
**/
@Data
public class PointRecordDTO extends BaseModel implements Serializable {
    /**
     * PointRecordDO-member_id
     */
    private Long memberId;

    /**
     * PointRecordDO-point_type
     */
    private String pointType;

    /**
     * PointRecordDO-point
     */
    private Integer point;

    /**
     * PointRecordDO-used_point
     */
    private Integer usedPoint;

    /**
     * PointRecordDO-left_point
     */
    private Integer leftPoint;

    /**
     * PointRecordDO-biz_order
     */
    private String bizOrder;

    /**
     * PointRecordDO-eff_date
     */
    private Date effDate;

    /**
     * PointRecordDO-exp_date
     */
    private Date expDate;

    /**
     * PointRecordDO-description
     */
    private String description;

    /**
     * 渠道
     */
    private String channel;

    public Date getEffDate() {
        if (this.effDate != null) {
            return (Date) this.effDate.clone();
        }
        return null;
    }

    public void setEffDate(Date effDate) {
        if (effDate != null) {
            this.effDate = (Date) effDate.clone();
        }
    }

    public Date getExpDate() {
        if (this.expDate != null) {
            return (Date) this.expDate.clone();
        }
        return null;
    }

    public void setExpDate(Date expDate) {
        if (expDate != null) {
            this.expDate = (Date) expDate.clone();
        }
    }
}
