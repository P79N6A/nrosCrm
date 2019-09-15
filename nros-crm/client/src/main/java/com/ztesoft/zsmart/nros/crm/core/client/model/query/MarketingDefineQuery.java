package com.ztesoft.zsmart.nros.crm.core.client.model.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 营销定义详情查询参数
 * @author   wangzhe
 * @date     2019/4/17 15:58
 */
@Data
public class MarketingDefineQuery implements Serializable {

    /**
     * 营销流程定义-标识
     */
    private Long id;
}