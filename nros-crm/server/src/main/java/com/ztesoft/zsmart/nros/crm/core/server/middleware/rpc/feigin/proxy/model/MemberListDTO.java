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
public class MemberListDTO extends BaseModel {
    private  Long id;

    private String headPic;

    private String phone;

    private String name;

    private String gender;

    private Integer point;

    private String channel;

    private Date birthday;

    private Integer level;

    private String faceUrl;

    private Integer growth;

    /**
     * 等级名称
     */
    private String levelName;
}
