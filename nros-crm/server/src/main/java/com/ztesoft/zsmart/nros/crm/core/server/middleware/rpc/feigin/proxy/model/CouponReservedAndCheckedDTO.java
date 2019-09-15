package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import lombok.Data;

/**
 * 优惠券核销与领取数量统计
 * @author   wangzhe
 * @date     2019/6/13 15:46
 */
@Data
public class CouponReservedAndCheckedDTO {

    /**
     * 活动节点代码
     */
    private String activeNodeCode;

    /**
     * 核销数量
     */
    private Long checkedCount;

    /**
     * 领取数量
     */
    private Long reservedCount;

    private static final long serialVersionUID = 1L;

}
