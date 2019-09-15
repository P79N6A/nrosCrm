package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询标签定义参数
 *
 * @author yc
 * @version 1.0
 * @since 2019-06-13
 */
@Data
public class TagConfigQuery extends BaseQuery implements Serializable {

    private String value;

    private String status;

    /**
     * id列表
     */
    private List<Long> ids;

}
