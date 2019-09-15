package com.ztesoft.zsmart.nros.crm.core.start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.ItemService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ProductQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author yangshaoxin
 * @version 1.0
 * @Description 商品Controller
 * @date 2019/4/29 14:08
 */
@SessionController
@RequestMapping("/item")
@Api(value = "商品管理", tags = {"商品管理"})
public class ItemController {

    @Autowired
    public ItemService itemService;

    /**
     * @Description 获取商品列表
     * @Author yangshaoxin
     * @Date 2019/4/29 14:12
     * @Param [productQuery]
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg
     **/
    @PostMapping("/base/product")
    @ApiOperation(value = "获取商品列表", notes = "获取商品列表")
    public ResponseMsg listProduct(@RequestBody ProductQuery productQuery) {
        return itemService.listProduct(productQuery);
    }

}
