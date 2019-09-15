package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceTemplateDO;

/**
 * 定义事件轨迹内容的模板
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-02
 */
public interface EventTraceTemplateDOMapper {
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
    int insert(EventTraceTemplateDO record);

    /**
     * 插入数据库记录
     *
     * @param record record
     * @return int
     */
    int insertSelective(EventTraceTemplateDO record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id id
     * @return com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceTemplateDO
     */
    EventTraceTemplateDO selectByPrimaryKey(Long id);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(EventTraceTemplateDO record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(EventTraceTemplateDO record);
}