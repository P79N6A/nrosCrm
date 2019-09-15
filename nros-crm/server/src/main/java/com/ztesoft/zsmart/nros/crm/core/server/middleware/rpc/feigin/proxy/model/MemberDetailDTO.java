package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 会员详情出参
 * @Auther: luo.yi20
 * @Date: 2019/4/9
 * @Version: 1.0
 * @See: com.ztesoft.zsmart.nros.sbc.nrosmember.service.model
 */
@Data
public class MemberDetailDTO extends BaseModel {
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

    private String educationalStatus;

    private String annualIncome;

    private String industry;

    private String guideId;

    private String outerStoreId;

    private String loginPwd;

    private String payPassword;

    private String bindThirdType;

    private String bindThirdId;

    private String faceId;

    private String faceUrl;

    private String isVip;

    private String posName;

    private String posAddress;

    private Date posDateTime;

    private Long recommenderId;

    private String qq;

    private String headUrl;

    private String identityFrontUrl;

    private String identityBackUrl;

    private Long categoryId;

    /**
     * 后添加的，不要勿删！
     */
    private Long normalBalance;
    private Long giftBalance;
    private Long allBalance;
    private String levelName;
}