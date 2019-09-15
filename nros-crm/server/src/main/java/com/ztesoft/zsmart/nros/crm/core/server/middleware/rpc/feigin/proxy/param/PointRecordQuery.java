package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import lombok.Data;


/**
  * @description 会员有效积分查询参数
  * @author wang.yulin01
  * @date 2019/4/15 18:54
  * @version V1.0
**/
@Data
public class PointRecordQuery extends BaseQuery {

    private Long memberId;

    private String bizOrder;
}
