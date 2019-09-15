package com.ztesoft.zsmart.nros.crm.core.client.api;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SmsTemplateDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SmsTemplateParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SmsTemplateQuery;

/**
 * @ClassName SmsTemplateService
 * @Description TODO
 * @Author
 * @Date 2019/6/10 16:47
 */
public interface SmsTemplateService {

    /**
     * 查询详情
     * 
     * @param id
     * @return
     */
    SmsTemplateDTO queryDetail(Long id);

    /**
     * 查询
     * 
     * @param smsTemplateQuery
     * @return
     */
    PageInfo<SmsTemplateDTO> queryList(SmsTemplateQuery smsTemplateQuery);

    /**
     * 新增
     * 
     * @param smsTemplateParam
     */
    void add(SmsTemplateParam smsTemplateParam);

    /**
     * 修改
     * 
     * @param smsTemplateParam
     */
    void modify(SmsTemplateParam smsTemplateParam);

    /**
     * 删除
     * 
     * @param id
     */
    void delete(Long id);
}
