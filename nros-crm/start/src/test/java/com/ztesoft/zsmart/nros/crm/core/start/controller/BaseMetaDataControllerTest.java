package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.BaseMetaDataServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.BaseMetaDataProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.EnumConfigDTO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanxiaokai
 * @date 2019/7/9
 */
public class BaseMetaDataControllerTest extends MockitoTest {

    @Autowired
    private BaseMetaDataController baseMetaDataController;

    @InjectMocks
    @Autowired
    private BaseMetaDataServiceImpl baseMetaDataService;

    @Mock
    private BaseMetaDataProxy baseMetaDataProxy;

    @Test
    public void testListChannelEnumConfig() {
        List<EnumConfigDTO> data = new ArrayList<>(1);
        EnumConfigDTO enumConfigDTO = new EnumConfigDTO();
        enumConfigDTO.setFieldCode("test");
        enumConfigDTO.setFieldName("test");
        data.add(enumConfigDTO);
        Mockito.when(baseMetaDataProxy.listChannelEnumConfig()).thenReturn(ResponseMsg.build(data));
        ResponseMsg<List<EnumConfigDTO>> responseMsg = baseMetaDataController.listChannelEnumConfig();
        Assert.assertEquals(data, responseMsg.getData());
    }

}
