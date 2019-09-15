package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;

import lombok.Data;

/**
 * GrowthRecordQuery
 *
 * @author jiang.yifeng2
 * @version 1.0
 * @since 2019-06-28
 */
@Data
public class GrowthRecordQuery extends BaseQuery {

    private Long memberId;

    private String bizOrder;
}

