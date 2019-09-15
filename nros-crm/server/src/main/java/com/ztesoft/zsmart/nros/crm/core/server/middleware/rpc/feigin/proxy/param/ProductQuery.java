package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangshaoxin
 * @version 1.0
 * @Description
 * @date 2019/4/29 14:03
 */
@Data
public class ProductQuery extends PageParam implements Serializable {

    @ApiModelProperty(value = "商品分类ID")
    private String categoryId;

    @ApiModelProperty(value = "商品类型")
    private String productType;

    @ApiModelProperty(value = "创建人ID")
    private String createUserId;

    @ApiModelProperty(value = "搜索条件(模糊搜索商品编号或商品名称)")
    private String condition;

    @ApiModelProperty(value = "渠道ID")
    private Long channelId;

    private Integer upAndDown;

    public ProductQuery() {

    }

    public ProductQuery(int pageNo, int pageSize) {
        this.pageIndex = pageNo;
        this.pageSize = pageSize;
    }

}
