package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 会员注册入参
 * @Auther: luo.yi20
 * @Date: 2019/4/9
 * @Version: 1.0
 * @See: com.ztesoft.zsmart.nros.sbc.nrosmember.service.model
 */
@Data
public class MemberRegisterParams {

    private Long categoryId;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "会员名称")
    private String name;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "邮箱")
    private String mailbox;

    @ApiModelProperty(value = "身份证号码")
    private String identity;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String county;

    @ApiModelProperty(value = "会员来源渠道")
    private String channel;

    @ApiModelProperty(value = "QQ号")
    private String qq;

    @ApiModelProperty(value = "登陆密码")
    private String loginPwd;

    @ApiModelProperty(value = "头像")
    private String headUrl;

    @ApiModelProperty(value = "证件正面照URL")
    private String identityFrontUrl;

    @ApiModelProperty(value = "证件反面照URL")
    private String identityBackUrl;

    @ApiModelProperty(value = "POS名称")
    private String posName;

    @ApiModelProperty(value = "POS地址")
    private String posAddress;

    @ApiModelProperty(value = "POS注册时间")
    private Date posDateTime;

    @ApiModelProperty(value = "支付密码")
    private String payPassword;
}
