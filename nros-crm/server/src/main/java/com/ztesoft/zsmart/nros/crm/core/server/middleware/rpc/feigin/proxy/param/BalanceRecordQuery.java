package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import lombok.Data;

import java.util.List;

/**
 * BalanceRecordQuery
 *
 * @author jiang.yifeng2
 * @version 1.0
 * @since 2019-06-21
 */
@Data
public class BalanceRecordQuery extends BaseQuery {

    private Long memberId;

    private String balanceCode;

    private String recordType;

    private String normalAmount;

    private String giftAmount;

    private String channel;

    private String bizId;

    private String status;

    private List<String> recordTypes;
}