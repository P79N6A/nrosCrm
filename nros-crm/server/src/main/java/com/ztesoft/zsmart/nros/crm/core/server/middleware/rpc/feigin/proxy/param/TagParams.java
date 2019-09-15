package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

/**
 * @author yc
 * @version V1.0
 * @description 会员标签参数
 * @date 2019/6/13
 **/
@Data
public class TagParams extends BaseModel {
    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 标签id
     */
    private Long tagId;

    /**
     * 打标签类型
     */
    private String tagType;

    //标签名
    private String value;

    //标签描述
    private String  description;

    //标签状态
    private String  status;
}
