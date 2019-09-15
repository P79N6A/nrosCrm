package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.StaffQuery;
import org.springframework.cloud.openfeign.FeignClient;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.StaffDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户中心接口订阅
 * 
 * @author yangshaoxin
 * @version 1.0
 * @date 2019/4/24 9:49
 */
@FeignClient(value = "user")
public interface UserProxy {

    /**
     * 查询员工列表
     * 
     * @author yangshaoxin
     * @date 2019/4/24 10:11
     * @param staffQuery 查询条件
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg
     */
    @PostMapping(value = "/nrosapi/user/v1/user")
    ResponseMsg listStaffInfo(@RequestBody StaffQuery staffQuery);

    /**
     * 查询用户详情
     * 
     * @author yangshaoxin
     * @date 2019/4/24 10:12
     * @param id 主键
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<com.ztesoft.zsmart.nros.sbc.crm.cloud.user.model.UserDTO>
     */
    @GetMapping(value = "/nrosapi/user/v1/user/{id}")
    ResponseMsg<UserDTO> queryUser(@PathVariable("id") Long id);

    /**
     * 查询员工详情
     * 
     * @author yangshaoxin
     * @date 2019/4/24 10:14
     * @param id 主键
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<com.ztesoft.zsmart.nros.sbc.crm.cloud.user.model.StaffDTO>
     */
    @GetMapping(value = "/nrosapi/user/v1/user/staff/{id}")
    ResponseMsg<StaffDTO> queryStaffDetail(@PathVariable("id") Long id);
}
