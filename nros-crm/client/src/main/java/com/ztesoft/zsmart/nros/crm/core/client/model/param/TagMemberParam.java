package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("批量新增会员标签参数")
public class TagMemberParam extends BaseModel implements Serializable {

    /**
     * 会员id list
     */
    private List<String> memberIdList;

    /**
     * 标签列表
     */
    private List<String> tagIdList;

    /**
     * 标签类型
     */
    private String tagType;
}
