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


import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.StaffQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.UserService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.UserProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.StaffDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.UserDTO;

/**
 * 会员服务远程调用实现类
 * @author   wangzhe
 * @date     2019/4/9 13:28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserProxy userProxy;

    @Override
    public ResponseMsg listStaffInfo(StaffQuery staffQuery) {
        return userProxy.listStaffInfo(staffQuery);
    }

    @Override
    public ResponseMsg<UserDTO> queryUser(Long id) {
        return userProxy.queryUser(id);
    }

    @Override
    public ResponseMsg<StaffDTO> queryStaffDetail(Long id) {
        return userProxy.queryStaffDetail(id);
    }
}
