package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.NodeExecuteRecordDO;
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
public class NodeExecuteRecordDOMapperTest extends MockitoTest {

    @Mock
    private NodeExecuteRecordDOMapper nodeExecuteRecordDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(nodeExecuteRecordDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, nodeExecuteRecordDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        NodeExecuteRecordDO nodeExecuteRecordDO = getNodeExecuteRecordDO();
        Mockito.when(nodeExecuteRecordDOMapper.insert(nodeExecuteRecordDO)).thenReturn(1);
        Assert.assertEquals(1, nodeExecuteRecordDOMapper.insert(nodeExecuteRecordDO));
    }

    private NodeExecuteRecordDO getNodeExecuteRecordDO() {
        NodeExecuteRecordDO nodeExecuteRecordDO = new NodeExecuteRecordDO();
        nodeExecuteRecordDO.setNodeType("1");
        nodeExecuteRecordDO.setNodeId("1");
        nodeExecuteRecordDO.setParam("1");
        nodeExecuteRecordDO.setResult("");
        nodeExecuteRecordDO.setNodeName("1");
        nodeExecuteRecordDO.setMarketingInstanceId("1");
        return nodeExecuteRecordDO;
    }

    @Test
    public void testInsertSelective() {
        NodeExecuteRecordDO nodeExecuteRecordDO = getNodeExecuteRecordDO();
        Mockito.when(nodeExecuteRecordDOMapper.insertSelective(nodeExecuteRecordDO)).thenReturn(1);
        Assert.assertEquals(1, nodeExecuteRecordDOMapper.insertSelective(nodeExecuteRecordDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        NodeExecuteRecordDO nodeExecuteRecordDO = getNodeExecuteRecordDO();
        Mockito.when(nodeExecuteRecordDOMapper.selectByPrimaryKey(1L)).thenReturn(nodeExecuteRecordDO);
        Assert.assertEquals(nodeExecuteRecordDO, nodeExecuteRecordDOMapper.selectByPrimaryKey(1L));
        nodeExecuteRecordDOMapper.selectByPrimaryKey(1L);
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        NodeExecuteRecordDO nodeExecuteRecordDO = getNodeExecuteRecordDO();
        Mockito.when(nodeExecuteRecordDOMapper.updateByPrimaryKeySelective(nodeExecuteRecordDO)).thenReturn(1);
        Assert.assertEquals(1, nodeExecuteRecordDOMapper.updateByPrimaryKeySelective(nodeExecuteRecordDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        NodeExecuteRecordDO nodeExecuteRecordDO = getNodeExecuteRecordDO();
        Mockito.when(nodeExecuteRecordDOMapper.updateByPrimaryKey(nodeExecuteRecordDO)).thenReturn(1);
        Assert.assertEquals(1, nodeExecuteRecordDOMapper.updateByPrimaryKey(nodeExecuteRecordDO));
    }
}
