package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import java.io.Serializable;

import com.ztesoft.zsmart.nros.common.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 会员ID和名称参数对象
 * @author yangshaoxin
 * @version 1.0
 * @date 2019/06/15 15:18
 */
@Data
@ApiModel("会员ID和名称参数对象")
public class MemberIdNameParam extends BaseModel implements Serializable {

    @ApiModelProperty(value = "会员ID", required = true)
    private Long memberId;

    @ApiModelProperty(value = "会员名称", required = true)
    private String memberName;

}
