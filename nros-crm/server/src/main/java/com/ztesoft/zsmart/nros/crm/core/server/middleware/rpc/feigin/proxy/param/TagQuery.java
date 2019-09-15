package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import lombok.Data;

/**
 * 查询会员标签参数
 *
 * @author yc
 * @version 1.0
 * @since 2019-06-13
 */
@Data
public class TagQuery extends BaseQuery {

    private Long memberId;

    private Long tagId;

    private String tagType;
}
