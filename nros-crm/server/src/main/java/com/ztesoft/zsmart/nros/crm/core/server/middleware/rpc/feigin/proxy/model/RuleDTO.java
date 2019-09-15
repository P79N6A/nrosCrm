package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 促销规则参数
 * @author   wangzhe
 * @date     2019/6/2 23:48
 */
@Data
public class RuleDTO extends BaseModel implements Serializable {
    /**
     * 促销规则-规则名称
     */
    private String name;

    /**
     * 促销规则-是否多个条件:[0]否,[1]是
     */
    private String multipleCondition;

    /**
     * 促销规则-条件是否循环:[0]不循环,[1]循环
     */
    private String conditionLoop;

    /**
     * 促销规则-优惠类型:[1]满折,[2]满减,[3]满赠,[4]满换,[5]满返,[6]满免,[7]运费,[8]单品特价;
     */
    private String discountType;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 条件列表
     */
    private List<ConditionDTO> conditionList;
}