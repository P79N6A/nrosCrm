package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.StoreDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.StoreQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yangshaoxin
 * @version 1.0
 * @Description 门店中心接口订阅
 * @date 2019/4/24 9:50
 */
public interface StoreService {

    /**
     * 查询门店
     * 
     * @param storeQuery
     * @return
     */
    ResponseMsg queryStoreByParams(@RequestBody StoreQuery storeQuery);

    /**
     * 查询门店详情
     * 
     * @param id
     * @return
     */
    ResponseMsg<StoreDTO> queryStoreDetailByParams(@PathVariable("id") Long id);

}
