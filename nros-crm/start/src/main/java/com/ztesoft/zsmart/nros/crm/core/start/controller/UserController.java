package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import org.springframework.beans.factory.annotation.Autowired;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.UserService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.StaffDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.UserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.StaffQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户controller
 *
 * @author wang.yaoding
 * @create 2019-4-12 16:58:58
 */
@SessionController
@RequestMapping("/user")
@Api(value = "用户管理", tags = {"用户管理"})
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description 查询用户详情
     * @Author yangshaoxin
     * @Date 2019/4/24 10:12
     * @Param [id]
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<com.ztesoft.zsmart.nros.sbc.crm.cloud.user.model.UserDTO>
     **/
    @GetMapping("/{id}")
    @ApiOperation("查询用户详情")
    public ResponseMsg<UserDTO> queryUser(@PathVariable Long id) {
        return userService.queryUser(id);
    }

    /**
     * @Description 查询员工详情
     * @Author yangshaoxin
     * @Date 2019/4/24 10:14
     * @Param [id]
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<com.ztesoft.zsmart.nros.sbc.crm.cloud.user.model.StaffDTO>
     **/
    @GetMapping("/staff/{id}")
    @ApiOperation("查询用户详情")
    public ResponseMsg<StaffDTO> queryStaffDetail(@PathVariable Long id) {
        return userService.queryStaffDetail(id);
    }

    /**
     * @Description 查询员工列表
     * @Author yangshaoxin
     * @Date 2019/4/24 10:11
     * @Param [staffQuery]
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg
     **/
    @PostMapping
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表", response = StaffDTO.class)
    public ResponseMsg listStaffInfo(@RequestBody StaffQuery staffQuery) {
        return userService.listStaffInfo(staffQuery);
    }
}
