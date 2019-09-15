package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc;

import org.springframework.web.bind.annotation.RequestBody;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ProductQuery;

/**
 * @author yangshaoxin
 * @version 1.0
 * @Description 商品中心接口订阅客户端
 * @date 2019/4/29 14:03
 */
public interface ItemService {

    /**
     * 查询商品主档
     * 
     * @Author yangshaoxin
     * @Date 2019/4/29 14:07
     * @param productQuery
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg
     */
    ResponseMsg listProduct(@RequestBody ProductQuery productQuery);

}
