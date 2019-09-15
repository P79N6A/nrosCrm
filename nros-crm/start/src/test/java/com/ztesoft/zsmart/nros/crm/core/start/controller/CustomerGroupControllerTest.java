package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.CustomerGroupServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.CustomerGroupProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagDeleteParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yuanxiaokai
 * @date 2019/7/9
 */

public class CustomerGroupControllerTest extends MockitoTest {

    @Autowired
    private CustomerGroupController customerGroupController;

    @Autowired
    @InjectMocks
    private CustomerGroupServiceImpl customerGroupService;

    @Mock
    private CustomerGroupProxy customerGroupProxy;

    @Test
    public void testQueryTagConfigList() {
        TagConfigQuery tagConfigQuery = new TagConfigQuery();
        ResponseMsg responseMsg = new ResponseMsg();
        Mockito.when(customerGroupProxy.qyTagConfigList(tagConfigQuery)).thenReturn(responseMsg);
        Assert.assertEquals(responseMsg, customerGroupController.queryTagConfigList(tagConfigQuery));
    }

    @Test
    public void testSaveTag() {
        TagConfigParams tagParams = new TagConfigParams();
        ResponseMsg responseMsg = new ResponseMsg();
        Mockito.when(customerGroupProxy.saveTag(new TagConfigParams())).thenReturn(responseMsg);
        Assert.assertEquals(responseMsg, customerGroupController.saveTag(tagParams));
    }

    @Test
    public void testDelTag() {
        TagConfigParams tagParams = new TagConfigParams();
        ResponseMsg responseMsg = new ResponseMsg();
        TagDeleteParams tagParams1 = new TagDeleteParams();
        Mockito.when(customerGroupProxy.delTag(tagParams1)).thenReturn(responseMsg);
        Assert.assertEquals(responseMsg, customerGroupController.delTag(tagParams));
    }

    @Test
    public void testModifyTag() {
        TagConfigParams tagParams = new TagConfigParams();
        ResponseMsg responseMsg = new ResponseMsg();
        Mockito.when(customerGroupProxy.modifyTag(tagParams)).thenReturn(responseMsg);
        Assert.assertEquals(responseMsg, customerGroupController.modifyTag(tagParams));
    }

    @Test
    public void testQueryTagById() {
        ResponseMsg responseMsg = new ResponseMsg();
        Mockito.when(customerGroupProxy.queryTagById(1L)).thenReturn(responseMsg);
        Assert.assertEquals(responseMsg, customerGroupController.queryTagById(1L));
    }

    @Test
    public void testQueryMenbersListById() {
        TagQuery tagQuery = new TagQuery();
        ResponseMsg responseMsg = new ResponseMsg();
        Mockito.when(customerGroupProxy.queryMenbersListById(tagQuery)).thenReturn(responseMsg);
        Assert.assertEquals(responseMsg, customerGroupController.queryMenbersListById(tagQuery));
    }

}
