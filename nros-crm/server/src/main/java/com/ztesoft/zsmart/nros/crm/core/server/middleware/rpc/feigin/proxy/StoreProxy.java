package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.StoreDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.StoreQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yangshaoxin
 * @version 1.0
 * @Description 门店中心接口订阅
 * @date 2019/4/24 9:50
 */
@FeignClient(value = "basedata")
public interface StoreProxy {

    /**
     * 查询门店
     * 
     * @param storeQuery
     * @return
     */
    @GetMapping(value = "/nrosapi/basedata/v1/org/store/page")
    ResponseMsg queryStoreByParams(@RequestBody StoreQuery storeQuery);

    /**
     * 查询门店详情
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "/nrosapi/basedata/v1/org/store/{id}")
    ResponseMsg<StoreDTO> queryStoreDetailByParams(@PathVariable("id") Long id);

}
