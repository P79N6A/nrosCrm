package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignFeedbackDO;

/**
 * 活动反馈
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-02
 */
public interface CampaignFeedbackDOMapper {
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
    int insert(CampaignFeedbackDO record);

    /**
     * 插入数据库记录
     *
     * @param record record
     * @return int
     */
    int insertSelective(CampaignFeedbackDO record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id id
     * @return com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignFeedbackDO
     */
    CampaignFeedbackDO selectByPrimaryKey(Long id);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(CampaignFeedbackDO record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(CampaignFeedbackDO record);
}