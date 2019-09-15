package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.ActionnRelationDO;
import lombok.extern.slf4j.Slf4j;
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
public class ActionnRelationDOMapperTest extends MockitoTest {

    @Mock
    private ActionnRelationDOMapper actionnRelationDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(actionnRelationDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, actionnRelationDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        ActionnRelationDO actionnRelationDO = getActionnRelationDO();
        Mockito.when(actionnRelationDOMapper.insert(actionnRelationDO)).thenReturn(1);
        Assert.assertEquals(1, actionnRelationDOMapper.insert(actionnRelationDO));
    }

    @Test
    public void testInsertSelective() {
        ActionnRelationDO actionnRelationDO = getActionnRelationDO();
        Mockito.when(actionnRelationDOMapper.insertSelective(actionnRelationDO)).thenReturn(1);
        Assert.assertEquals(1, actionnRelationDOMapper.insertSelective(actionnRelationDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        ActionnRelationDO actionnRelationDO = getActionnRelationDO();
        Mockito.when(actionnRelationDOMapper.selectByPrimaryKey(1L)).thenReturn(actionnRelationDO);
        Assert.assertEquals(actionnRelationDO, actionnRelationDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        ActionnRelationDO actionnRelationDO = getActionnRelationDO();
        Mockito.when(actionnRelationDOMapper.updateByPrimaryKeySelective(actionnRelationDO)).thenReturn(1);
        Assert.assertEquals(1, actionnRelationDOMapper.updateByPrimaryKeySelective(actionnRelationDO));
    }

    private ActionnRelationDO getActionnRelationDO() {
        ActionnRelationDO actionnRelationDO = new ActionnRelationDO();
        actionnRelationDO.setActionPresetId(1L);
        actionnRelationDO.setCampaignChannelId(1L);
        actionnRelationDO.setMarketingInstanceHisId(1L);
        actionnRelationDO.setSeq(1);
        return actionnRelationDO;
    }

    @Test
    public void testUpdateByPrimaryKey() {
        ActionnRelationDO actionnRelationDO = getActionnRelationDO();
        Mockito.when(actionnRelationDOMapper.updateByPrimaryKey(actionnRelationDO)).thenReturn(1);
        Assert.assertEquals(1, actionnRelationDOMapper.updateByPrimaryKey(actionnRelationDO));
    }
}
