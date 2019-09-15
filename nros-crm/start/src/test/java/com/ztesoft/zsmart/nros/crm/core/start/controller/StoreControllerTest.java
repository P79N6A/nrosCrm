package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.StoreServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.StoreProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.StoreDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.StoreQuery;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * 门店单元测试
 */
public class StoreControllerTest extends MockitoTest {

    @Autowired
    private StoreController storeController;

    /**
     * mock测试添加
     */
    @Mock
    private StoreProxy storeProxy;
    @InjectMocks
    @Autowired
    private StoreServiceImpl storeService;

    @Test
    public void queryStoreByParamsTest() {
        StoreQuery storeQuery = new StoreQuery();

        ResponseMsg rtn = new ResponseMsg();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(1L);
        rtn.setData(pageInfo);

        when(storeProxy.queryStoreByParams(storeQuery)).thenReturn(rtn);

        ResponseMsg responseMsg = storeController.queryStoreByParams(storeQuery);

        assertEquals(responseMsg.getData(), pageInfo);
    }

    @Test
    public void queryStoreDetailByParamsTest() {
        Long id = 1L;
        String address = "beijing";
        ResponseMsg<StoreDTO> rtn = new ResponseMsg<>();
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setAddress(address);
        rtn.setData(storeDTO);
        when(storeProxy.queryStoreDetailByParams(id)).thenReturn(rtn);

        ResponseMsg<StoreDTO> responseMsg = storeController.queryStoreDetailByParams(id);

        assertEquals(responseMsg.getData().getAddress(), address);
    }


}
