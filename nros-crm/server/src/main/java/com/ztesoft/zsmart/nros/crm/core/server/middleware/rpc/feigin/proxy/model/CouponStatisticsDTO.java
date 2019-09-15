package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 优惠券实例DTO
 * @author wangzhe
 * @date 2019-04-09
 */
@Data
public class CouponStatisticsDTO implements Serializable {

    /**
     * 优惠券核销总量
     */
    private Long checkedCount;

    /**
     * 优惠券总量
     */
    private Long reservedCount;

    /**
     * 优惠券核销与总量统计列表
     */
    private List<CouponReservedAndCheckedDTO> couponReservedAndCheckedDTOList;

}
