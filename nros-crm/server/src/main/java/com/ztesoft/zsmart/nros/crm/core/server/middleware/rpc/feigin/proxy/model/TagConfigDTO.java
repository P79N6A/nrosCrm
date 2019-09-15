package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

/**
 * @author yc
 * @version V1.0
 * @description 标签DTO
 * @date 2019/6/13
 **/
@Data
public class TagConfigDTO extends BaseModel {

    /**
     * 标签名
     */
    private String value;

    /**
     * 标签描述
     */
    private String description;

}
