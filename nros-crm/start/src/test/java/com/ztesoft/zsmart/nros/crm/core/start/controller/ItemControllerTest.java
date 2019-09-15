package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.ItemServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.ItemProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ProductQuery;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yuanxiaokai
 * @date 2019/7/10
 */
public class ItemControllerTest extends MockitoTest {

    @Autowired
    @InjectMocks
    private ItemController itemController;

    @Autowired
    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemProxy itemProxy;

    @Test
    public void testListProduct() {
        ProductQuery productQuery = new ProductQuery();
        ResponseMsg responseMsg = new ResponseMsg();
        Mockito.when(itemProxy.listProduct(productQuery)).thenReturn(responseMsg);
        Assert.assertEquals(responseMsg,itemController.listProduct(productQuery));
    }

}
