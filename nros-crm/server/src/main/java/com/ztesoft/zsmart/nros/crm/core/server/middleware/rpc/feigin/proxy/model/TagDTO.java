package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

/**
 * @author yc
 * @version V1.0
 * @description 会员标签DTO
 * @date 2019/6/13
 **/
@Data
public class TagDTO extends BaseModel {
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

    /**
     * 标签定义DTO
     */
    private TagConfigDTO tagConfigDTO;
}
