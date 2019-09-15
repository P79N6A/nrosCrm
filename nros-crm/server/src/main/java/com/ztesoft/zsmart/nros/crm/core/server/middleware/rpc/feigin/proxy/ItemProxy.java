package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ProductQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yangshaoxin
 * @version 1.0
 * @Description 商品中心接口订阅客户端
 * @date 2019/4/29 14:03
 */
@FeignClient(value = "item")
public interface ItemProxy {

    /**
     *  查询商品主档
     * @Author yangshaoxin
     * @Date 2019/4/29 14:07
     * @param productQuery
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg
     */
    @PostMapping(value = "/nrosapi/item/v1/base/product")
    ResponseMsg listProduct(@RequestBody ProductQuery productQuery);

}
