package com.ztesoft.zsmart.nros.crm.core.client.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MemberPointVO {
    /**
     * 总积分
     */
    private Integer totalPoint;

    /**
     * 已使用积分
     */
    private Integer usePoint;

    /**
     * 剩余积分
     */
    private Integer surplusPoint;
}
