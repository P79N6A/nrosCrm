package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.SmsTemplateDO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanxiaokai
 * @date 2019/7/13
 */
public class SmsTemplateDOMapperTest extends MockitoTest {

    @Mock
    private SmsTemplateDOMapper smsTemplateDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(smsTemplateDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, smsTemplateDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        SmsTemplateDO smsTemplateDO = getSmsTemplateDO();
        Mockito.when(smsTemplateDOMapper.insert(smsTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, smsTemplateDOMapper.insert(smsTemplateDO));
    }

    private SmsTemplateDO getSmsTemplateDO() {
        SmsTemplateDO smsTemplateDO = new SmsTemplateDO();
        smsTemplateDO.setTemplateName("1");
        smsTemplateDO.setTemplateContent("1");
        return smsTemplateDO;
    }

    @Test
    public void testInsertSelective() {
        SmsTemplateDO smsTemplateDO = getSmsTemplateDO();
        Mockito.when(smsTemplateDOMapper.insertSelective(smsTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, smsTemplateDOMapper.insertSelective(smsTemplateDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        SmsTemplateDO smsTemplateDO = getSmsTemplateDO();
        Mockito.when(smsTemplateDOMapper.selectByPrimaryKey(1L)).thenReturn(smsTemplateDO);
        Assert.assertEquals(smsTemplateDO, smsTemplateDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        SmsTemplateDO smsTemplateDO = getSmsTemplateDO();
        Mockito.when(smsTemplateDOMapper.updateByPrimaryKeySelective(smsTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, smsTemplateDOMapper.updateByPrimaryKeySelective(smsTemplateDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        SmsTemplateDO smsTemplateDO = getSmsTemplateDO();
        Mockito.when(smsTemplateDOMapper.updateByPrimaryKey(smsTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, smsTemplateDOMapper.updateByPrimaryKey(smsTemplateDO));
    }
}
