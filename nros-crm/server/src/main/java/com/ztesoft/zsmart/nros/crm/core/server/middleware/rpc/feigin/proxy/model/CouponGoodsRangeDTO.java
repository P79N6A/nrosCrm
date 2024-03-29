package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券商品范围
 * @author   wangzhe
 * @date     2019/6/10 15:10
 */
@Data
public class CouponGoodsRangeDTO extends BaseModel implements Serializable {
    /**
     * 优惠券商品范围-商品范围编码
     */
    private String rangeCode;

    /**
     * 优惠券商品范围-商品范围名称
     */
    private String rangeName;

    /**
     * 优惠券商品范围-优惠券编码
     */
    private String couponCode;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;
}