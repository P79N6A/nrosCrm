package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.UserServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.UserProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.StaffDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.UserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.StaffQuery;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest extends MockitoTest {

    @Autowired
    private UserController userController;


    /**
     * mock测试添加
     */
    @Mock
    private UserProxy userProxy;
    @InjectMocks
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void listStaffInfoTest() {
        StaffQuery staffQuery = new StaffQuery();
        ResponseMsg<String> rtn = new ResponseMsg("test");

        when(userProxy.listStaffInfo(staffQuery)).thenReturn(rtn);

        ResponseMsg staff = userController.listStaffInfo(staffQuery);

        assertEquals(staff.getData().toString(), "test");
    }

    @Test
    public void queryStaffDetailTest() {
        Long staffId = 50L;
        ResponseMsg<StaffDTO> rtn = new ResponseMsg<>();
        StaffDTO staff = new StaffDTO();
        staff.setPhone("187");
        rtn.setData(staff);
        when(userProxy.queryStaffDetail(staffId)).thenReturn(rtn);

        ResponseMsg<StaffDTO> responseMsg = userController.queryStaffDetail(staffId);
        StaffDTO staffDTO = responseMsg.getData();

        assertEquals(staffDTO.getPhone(), "187");
    }

    @Test
    public void queryUserTest() {
        Long staffId = 50L;
        ResponseMsg<UserDTO> rtn = new ResponseMsg<>();
        UserDTO staff = new UserDTO();
        staff.setPhone("187");
        rtn.setData(staff);
        when(userProxy.queryUser(staffId)).thenReturn(rtn);

        ResponseMsg<UserDTO> responseMsg = userController.queryUser(staffId);

        assertEquals(responseMsg.getData().getPhone(), "187");
    }
}
