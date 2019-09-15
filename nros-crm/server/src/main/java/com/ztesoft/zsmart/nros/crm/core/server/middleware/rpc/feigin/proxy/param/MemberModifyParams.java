package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 会员修改入参
 * @Auther: luo.yi20
 * @Date: 2019/4/9
 * @Version: 1.0
 * @See: com.ztesoft.zsmart.nros.sbc.nrosmember.service.model
 */
@Data
public class MemberModifyParams extends BaseModel {

    /**
     * 会员ID
     */
    private Long memberId;

    private String phone;

    private String nickName;

    private Date birthday;

    private String gender;

    /**
     * 头像URL
     */
    private String headPic;

    private String identity;

    private String province;

    private String city;

    private String county;

    private String posAddress;

    private Date posDateTime;
}
