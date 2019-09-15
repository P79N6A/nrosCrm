package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceDO;
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
public class EventTraceDOMapperTest extends MockitoTest {

    @Mock
    private EventTraceDOMapper eventTraceDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(eventTraceDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, eventTraceDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        EventTraceDO eventTraceDO = getEventTraceDO();
        Mockito.when(eventTraceDOMapper.insert(eventTraceDO)).thenReturn(1);
        Assert.assertEquals(1, eventTraceDOMapper.insert(eventTraceDO));
    }

    private EventTraceDO getEventTraceDO() {
        EventTraceDO eventTraceDO = new EventTraceDO();
        eventTraceDO.setEventId(1L);
        eventTraceDO.setMemberId(1L);
        eventTraceDO.setLang("en");
        eventTraceDO.setContent("");
        return eventTraceDO;
    }

    @Test
    public void testInsertSelective() {
        EventTraceDO eventTraceDO = getEventTraceDO();
        Mockito.when(eventTraceDOMapper.insertSelective(eventTraceDO)).thenReturn(1);
        Assert.assertEquals(1, eventTraceDOMapper.insertSelective(eventTraceDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        EventTraceDO eventTraceDO = getEventTraceDO();
        Mockito.when(eventTraceDOMapper.selectByPrimaryKey(1L)).thenReturn(eventTraceDO);
        Assert.assertEquals(eventTraceDO, eventTraceDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        EventTraceDO eventTraceDO = getEventTraceDO();
        Mockito.when(eventTraceDOMapper.updateByPrimaryKeySelective(eventTraceDO)).thenReturn(1);
        Assert.assertEquals(1, eventTraceDOMapper.updateByPrimaryKeySelective(eventTraceDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        EventTraceDO eventTraceDO = getEventTraceDO();
        Mockito.when(eventTraceDOMapper.updateByPrimaryKey(eventTraceDO)).thenReturn(1);
        Assert.assertEquals(1, eventTraceDOMapper.updateByPrimaryKey(eventTraceDO));
    }
}
