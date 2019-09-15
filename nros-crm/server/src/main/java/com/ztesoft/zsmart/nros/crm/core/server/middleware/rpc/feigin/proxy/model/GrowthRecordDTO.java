package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
  * @description 成长值记录DTO
  * @author wang.yulin01
  * @date 2019/6/2 22:05
  * @version V1.0
**/
@Data
public class GrowthRecordDTO extends BaseModel implements Serializable {
    /**
     * GrowthRecordDO-member_id
     */
    private Long memberId;

    /**
     * GrowthRecordDO-growth_type
     */
    private String growthType;

    /**
     * GrowthRecordDO-growth
     */
    private Integer growth;

    /**
     * GrowthRecordDO-biz_order
     */
    private String bizOrder;

    /**
     * GrowthRecordDO-eff_date
     */
    private Date effDate;

    /**
     * GrowthRecordDO-exp_date
     */
    private Date expDate;

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
