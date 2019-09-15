package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

/**
 * @Description:
 * @Auther: luo.yi20
 * @Date: 2019/6/10
 * @Version: 1.0
 * @See: com.ztesoft.zsmart.nros.sbc.nrosmember.client.model.param
 */
@Data
public class ModifyLoginPasswordParams extends BaseModel {

    private Long memberId;

    private String oldPassword;

    private String newPassword;
}
