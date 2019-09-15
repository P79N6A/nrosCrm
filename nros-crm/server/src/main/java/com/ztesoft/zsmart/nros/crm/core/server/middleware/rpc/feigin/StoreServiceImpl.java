/**
 * [Product]
 *     crm
 * [Copyright]
 *     Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 *     CouponServiceImpl.java
 * [History]
 *     Version  Date      Author     Content
 *     -------- --------- ---------- ------------------------
 *     1.0.0    2019年3月27日   zhouyl5    最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin;


import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.StoreQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.StoreService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.StoreProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.StoreDTO;

/**
 * 会员服务远程调用实现类
 * @author   wangzhe
 * @date     2019/4/9 13:28
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreProxy storeProxy;

    @Override public ResponseMsg queryStoreByParams(StoreQuery storeQuery) {
        return storeProxy.queryStoreByParams(storeQuery);
    }

    @Override public ResponseMsg<StoreDTO> queryStoreDetailByParams(Long id) {
        return storeProxy.queryStoreDetailByParams(id);
    }
}
