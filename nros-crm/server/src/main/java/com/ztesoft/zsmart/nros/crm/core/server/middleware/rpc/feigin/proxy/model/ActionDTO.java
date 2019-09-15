package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

/**
 * @author wufan
 * @title: OrderLineParam
 * @date 2019/6/2 11:06
 */
@Data
public class ActionDTO extends BaseModel {
    /**
     * 对应：TradeConstants.TradeAction
     */
    private Integer actionId = 1;
    private String actionName;
    private String desc;
    private String targetService;
    private String servicURI;
}
