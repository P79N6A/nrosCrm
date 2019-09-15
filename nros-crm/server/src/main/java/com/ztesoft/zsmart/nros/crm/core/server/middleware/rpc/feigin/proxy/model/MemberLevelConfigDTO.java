package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ztesoft.zsmart.nros.common.model.BaseModel;

import lombok.Data;

/**
 * @description 会员等级配置DTO
 * @author wang.yulin01
 * @date 2019/4/9 16:53
 * @version V1.0
 **/
@Data
public class MemberLevelConfigDTO extends BaseModel {

    private Long categoryId;

    private Integer level;

    private String levelName;

    private Integer minGrowth;

    private Integer maxGrowth;

    private Integer levelDiscount;

    private BigDecimal levelGrowthAward;

    private BigDecimal levelPointAward;

    private Integer pointAmountConvert;

    private Integer pointDeductibleScale;

    private Long maxPointDeductible;

    private String canUsePrivileges;

    private String pointPrivilegeDescription;

    private String growthPrivilegeDescription;

    public Date getGmtCreate() {
        if (this.gmtCreate != null) {
            return (Date) this.gmtCreate.clone();
        }
        return null;
    }

    public void setGmtCreate(Date gmtCreate) {
        if (gmtCreate != null) {
            this.gmtCreate = (Date) gmtCreate.clone();
        }
    }

    public Date getGmtModified() {
        if (this.gmtModified != null) {
            return (Date) this.gmtModified.clone();
        }
        return null;
    }

    public void setGmtModified(Date gmtModified) {

        if (gmtModified != null) {
            this.gmtModified = (Date) gmtModified.clone();
        }
    }
}
