package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.param.PageParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2019-04-17
 * Time: 19:36
 */
@Data
public class StoreQuery extends PageParam implements Serializable {

    // 主键
    private Long id;

    // 机构主键
    private Long orgId;

    // 门店名称
    private String name;

    // 时间区间查询条件,开始时间
    private Date startTime;

    // 时间区间查询条件,结束时间
    private Date endTime;

    // 数据状态
    private String state;




}
