package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: luo.yi20
 * @Date: 2019/4/26
 * @Version: 1.0
 * @See: com.ztesoft.zsmart.nros.sbc.nrosmember.member.model
 */
@Data
public class MemberDTO extends BaseModel {

    private String name;

    private String memberStatus;

    private String memberType;

    private Integer level;

    private String channel;

    private Integer growth;

    private Integer point;

    private String province;

    private String city;

    private String county;

    private String address;

    private String phone;

    private String gender;

    private Date birthday;

    private String mailbox;

    private String identity;

    private String loginPwd;

    private String payPassword;

    private String posName;

    private String posAddress;

    private Date posDateTime;

    private String qq;

    private String headUrl;

    private String identityFrontUrl;

    private String identityBackUrl;

    private Long categoryId;
}
