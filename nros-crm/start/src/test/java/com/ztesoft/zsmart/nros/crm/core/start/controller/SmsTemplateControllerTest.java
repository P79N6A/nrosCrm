package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SmsTemplateDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SmsTemplateParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SmsTemplateQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.SmsTemplateDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.SmsTemplateMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.SmsTemplateDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.repository.SmsTemplateRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 短信模板单元测试
 */
public class SmsTemplateControllerTest extends MockitoTest {

    @Autowired
    private SmsTemplateController smsTemplateController;

    @Mock
    private SmsTemplateDOMapper smsTemplateDOMapper;
    @Mock
    private SmsTemplateMapper smsTemplateMapper;
    @InjectMocks
    @Autowired
    private SmsTemplateRepository smsTemplateRepository;

    @Test
    public void addTest() {
        SmsTemplateParam smsTemplateParam = new SmsTemplateParam();
        smsTemplateParam.setTemplateName("测试模板");
        smsTemplateParam.setTemplateContent("测试模板内容");

        when(smsTemplateDOMapper.insertSelective(any(SmsTemplateDO.class))).thenReturn(1);

        ResponseMsg responseMsg = smsTemplateController.add(smsTemplateParam);
        assertEquals(responseMsg.isSuccess(), true);
        verify(smsTemplateDOMapper).insertSelective(any(SmsTemplateDO.class));
    }

    @Test
    public void deleteTest() {
        Long id = -1L;
        when(smsTemplateDOMapper.deleteByPrimaryKey(id)).thenReturn(1);
        ResponseMsg responseMsg = smsTemplateController.delete(id);
        assertEquals(responseMsg.isSuccess(), true);
        verify(smsTemplateDOMapper).deleteByPrimaryKey(id);
    }

    @Test
    public void modifyTest() {
        Long id = -1L;
        SmsTemplateParam smsTemplateParam = new SmsTemplateParam();
        smsTemplateParam.setTemplateName("测试模板");
        smsTemplateParam.setTemplateContent("测试模板内容");
        smsTemplateParam.setId(id);

        when(smsTemplateMapper.updateByPrimaryKey(any(SmsTemplateDO.class))).thenReturn(1);

        ResponseMsg responseMsg = smsTemplateController.modify(smsTemplateParam);
        assertEquals(responseMsg.isSuccess(), true);
        verify(smsTemplateMapper).updateByPrimaryKey(any(SmsTemplateDO.class));
    }

    @Test
    public void queryDetailTest() {
        Long id = 1L;

        SmsTemplateDO smsTemplateDO = new SmsTemplateDO();
        smsTemplateDO.setId(id);
        when(smsTemplateMapper.selectOne(id)).thenReturn(smsTemplateDO);

        ResponseMsg responseMsg = smsTemplateController.queryDetail(id);
        SmsTemplateDTO rtnData = (SmsTemplateDTO) responseMsg.getData();
        assertEquals(rtnData.getId(), id);
        verify(smsTemplateMapper).selectOne(id);
    }

    @Test
    public void queryListTest() {
        SmsTemplateQuery smsTemplateQuery = new SmsTemplateQuery();

        List<SmsTemplateDO> smsTemplateDOList = new ArrayList<>();
        SmsTemplateDO smsTemplateDO = new SmsTemplateDO();
        smsTemplateDO.setId(1L);
        smsTemplateDOList.add(smsTemplateDO);

        when(smsTemplateMapper.queryList(any(SmsTemplateDO.class))).thenReturn(smsTemplateDOList);

        ResponseMsg responseMsg = smsTemplateController.queryList(smsTemplateQuery);
        assertTrue(responseMsg.getTotal() > 0);
        verify(smsTemplateMapper).queryList(any(SmsTemplateDO.class));
    }

}
