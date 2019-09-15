package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;


/**
 * BalanceRecordDTO
 *
 * @author jiang.yifeng2
 * @version 1.0
 * @since 2019-06-21
 */
@Data
public class BalanceRecordDTO extends BaseModel {

    /**
     * 会员ID
     */
    private Long memberId;

    private String balanceCode;

    /**
     * 记录类型 <br/>
     * RECHARGE 充值 </br>
     * REVOKE_RECHARGE 撤销充值 </br>
     * CONSUME 消费 </br>
     * REVOKE_CONSUME撤销消费 </br>
     * REFUND_ALL 账户清零
     */
    private String recordType;

    /**
     * 普通金额
     */
    private String normalAmount;

    /**
     * 赠送金额
     */
    private String giftAmount;

    /**
     * 来源渠道
     */
    private String channel;

    /**
     * 业务单号
     */
    private String bizId;
}