package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.param.PageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2019-04-23
 * Time: 16:06
 */
@Data
public class StaffQuery extends PageParam implements Serializable {

    private String staffName;


    private String phone;

    private String state;

}
