package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.SortFieldEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.SortTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * 会员列表Query
 *
 * @author litao
 * @date 2019/6/12
 */
@Data
public class MemberQuery extends BaseQuery {

    /**
     * 姓名
     */
    private String name;

    /**
     * 人脸ID列表
     */
    private List<String> faceIds;

    /**
     * id列表
     */
    private List<Long> ids;

    /**
     * 电话
     */
    private String phone;

    /**
     * 排序字段
     */
    private SortFieldEnum sortField;

    /**
     * 排序类型
     */
    private SortTypeEnum sortType;

}
