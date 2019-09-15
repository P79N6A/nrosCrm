package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.SmsTemplateDO;

import java.util.List;

/**
 *
 *<p>Description<p>
 *@Author will_lee
 *@Date 2019/6/12 10:50
 */

public interface SmsTemplateMapper {
    /**
     * 根据id查询短信模板信息
     * @param id
     * @return
     */
     SmsTemplateDO selectOne(Long id);


    /**
     * 通过短信模板名字查询/或者列表查询
     * @param smsTemplateDO
     * @return
     */
    List<SmsTemplateDO> queryList(SmsTemplateDO smsTemplateDO);

    /**
     * 插入数据库记录
     *
     * @param smsTemplateDO
     * @return int
     */
    int insert(SmsTemplateDO smsTemplateDO);

    /**
     * 根据主键来更新数据库记录
     *
     * @param  smsTemplateDO
     * @return int
     */
    int updateByPrimaryKey(SmsTemplateDO smsTemplateDO);


}
