package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.NodeFilterUserDO;
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
public class NodeFilterUserDOMapperTest extends MockitoTest {

    @Mock
    private NodeFilterUserDOMapper nodeFilterUserDOMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        Mockito.when(nodeFilterUserDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        Assert.assertEquals(1, nodeFilterUserDOMapper.deleteByPrimaryKey(1L));
    }

    @Test
    public void testInsert() {
        NodeFilterUserDO nodeFilterUserDO = getNodeFilterUserDO();
        Mockito.when(nodeFilterUserDOMapper.insert(nodeFilterUserDO)).thenReturn(1);
        Assert.assertEquals(1, nodeFilterUserDOMapper.insert(nodeFilterUserDO));
    }

    private NodeFilterUserDO getNodeFilterUserDO() {
        NodeFilterUserDO nodeFilterUserDO = new NodeFilterUserDO();
        nodeFilterUserDO.setNodeId("1");
        nodeFilterUserDO.setCustomerGroupId(1L);
        nodeFilterUserDO.setOpenId("1");
        nodeFilterUserDO.setPhone("");
        nodeFilterUserDO.setMemberId(1L);
        nodeFilterUserDO.setJudge(null);
        nodeFilterUserDO.setMarketingInstanceId("1");
        return nodeFilterUserDO;
    }

    @Test
    public void testInsertSelective() {
        NodeFilterUserDO nodeFilterUserDO = getNodeFilterUserDO();
        Mockito.when(nodeFilterUserDOMapper.insertSelective(nodeFilterUserDO)).thenReturn(1);
        Assert.assertEquals(1, nodeFilterUserDOMapper.insertSelective(nodeFilterUserDO));
    }

    @Test
    public void testSelectByPrimaryKey() {
        NodeFilterUserDO nodeFilterUserDO = getNodeFilterUserDO();
        Mockito.when(nodeFilterUserDOMapper.selectByPrimaryKey(1L)).thenReturn(nodeFilterUserDO);
        Assert.assertEquals(nodeFilterUserDO, nodeFilterUserDOMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        NodeFilterUserDO nodeFilterUserDO = getNodeFilterUserDO();
        Mockito.when(nodeFilterUserDOMapper.updateByPrimaryKeySelective(nodeFilterUserDO)).thenReturn(1);
        Assert.assertEquals(1, nodeFilterUserDOMapper.updateByPrimaryKeySelective(nodeFilterUserDO));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        NodeFilterUserDO nodeFilterUserDO = getNodeFilterUserDO();
        Mockito.when(nodeFilterUserDOMapper.updateByPrimaryKey(nodeFilterUserDO)).thenReturn(1);
        Assert.assertEquals(1, nodeFilterUserDOMapper.updateByPrimaryKey(nodeFilterUserDO));
    }
}
