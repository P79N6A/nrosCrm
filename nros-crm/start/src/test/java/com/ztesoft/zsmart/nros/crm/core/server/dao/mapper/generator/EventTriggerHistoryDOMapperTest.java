package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceTemplateDO;
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
public class EventTriggerHistoryDOMapperTest extends MockitoTest {

    @Mock
    private EventTraceTemplateDOMapper eventTraceTemplateDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(eventTraceTemplateDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, eventTraceTemplateDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        EventTraceTemplateDO eventTraceTemplateDO = getEventTraceTemplateDO();
        Mockito.when(eventTraceTemplateDOMapper.insert(eventTraceTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, eventTraceTemplateDOMapper.insert(eventTraceTemplateDO));
    }

    private EventTraceTemplateDO getEventTraceTemplateDO() {
        EventTraceTemplateDO eventTraceTemplateDO = new EventTraceTemplateDO();
        eventTraceTemplateDO.setTemplateContent("");
        eventTraceTemplateDO.setLang("en");
        eventTraceTemplateDO.setEventId(1L);
        return eventTraceTemplateDO;
    }

    @Test
    public void testInsertSelective() {
        EventTraceTemplateDO eventTraceTemplateDO = getEventTraceTemplateDO();
        Mockito.when(eventTraceTemplateDOMapper.insertSelective(eventTraceTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, eventTraceTemplateDOMapper.insertSelective(eventTraceTemplateDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        EventTraceTemplateDO eventTraceTemplateDO = getEventTraceTemplateDO();
        Mockito.when(eventTraceTemplateDOMapper.selectByPrimaryKey(1L)).thenReturn(eventTraceTemplateDO);
        Assert.assertEquals(eventTraceTemplateDO, eventTraceTemplateDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        EventTraceTemplateDO eventTraceTemplateDO = getEventTraceTemplateDO();
        Mockito.when(eventTraceTemplateDOMapper.updateByPrimaryKeySelective(eventTraceTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, eventTraceTemplateDOMapper.updateByPrimaryKeySelective(eventTraceTemplateDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        EventTraceTemplateDO eventTraceTemplateDO = getEventTraceTemplateDO();
        Mockito.when(eventTraceTemplateDOMapper.updateByPrimaryKey(eventTraceTemplateDO)).thenReturn(1);
        Assert.assertEquals(1, eventTraceTemplateDOMapper.updateByPrimaryKey(eventTraceTemplateDO));
    }
}
