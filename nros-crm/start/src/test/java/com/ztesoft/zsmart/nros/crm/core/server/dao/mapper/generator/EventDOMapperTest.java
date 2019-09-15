package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventDO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanxiaokai
 * @date 2019/7/13
 */
public class EventDOMapperTest extends MockitoTest {

    @Mock
    private EventDOMapper eventDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(eventDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, eventDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        EventDO eventDO = getEventDO();
        Mockito.when(eventDOMapper.insert(eventDO)).thenReturn(1);
        Assert.assertEquals(1, eventDOMapper.insert(eventDO));
    }

    private EventDO getEventDO() {
        EventDO eventDO = new EventDO();
        eventDO.setDescription("1");
        eventDO.setEventClass("1");
        eventDO.setEventCode("1");
        eventDO.setEventName("1");
        return eventDO;
    }

    @Test
    public void testInsertSelective() {
        EventDO eventDO = getEventDO();
        Mockito.when(eventDOMapper.insertSelective(eventDO)).thenReturn(1);
        Assert.assertEquals(1, eventDOMapper.insertSelective(eventDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        EventDO eventDO = getEventDO();
        Mockito.when(eventDOMapper.selectByPrimaryKey(1L)).thenReturn(eventDO);
        Assert.assertEquals(eventDO, eventDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        EventDO eventDO = getEventDO();
        Mockito.when(eventDOMapper.updateByPrimaryKeySelective(eventDO)).thenReturn(1);
        Assert.assertEquals(1, eventDOMapper.updateByPrimaryKeySelective(eventDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        EventDO eventDO = getEventDO();
        Mockito.when(eventDOMapper.updateByPrimaryKey(eventDO)).thenReturn(1);
        Assert.assertEquals(1, eventDOMapper.updateByPrimaryKey(eventDO));
    }
}
