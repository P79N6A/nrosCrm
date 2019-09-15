package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.NodeExecuteRecordDO;

/**
 * 活动节点执行记录
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-07-25
 */
public interface NodeExecuteRecordDOMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录
     *
     * @param record record
     * @return int
     */
    int insert(NodeExecuteRecordDO record);

    /**
     * 插入数据库记录
     *
     * @param record record
     * @return int
     */
    int insertSelective(NodeExecuteRecordDO record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id id
     * @return com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.NodeExecuteRecordDO
     */
    NodeExecuteRecordDO selectByPrimaryKey(Long id);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(NodeExecuteRecordDO record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(NodeExecuteRecordDO record);
}