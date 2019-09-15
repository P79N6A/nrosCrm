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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.ItemService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.ItemProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ProductQuery;

/**
 * 会员服务远程调用实现类
 * @author   wangzhe
 * @date     2019/4/9 13:28
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemProxy itemProxy;

    @Override public ResponseMsg listProduct(ProductQuery productQuery) {
        return itemProxy.listProduct(productQuery);
    }
}
