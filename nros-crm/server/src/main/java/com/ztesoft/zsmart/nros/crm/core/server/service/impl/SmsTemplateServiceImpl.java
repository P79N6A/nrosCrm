package com.ztesoft.zsmart.nros.crm.core.server.service.impl;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.api.SmsTemplateService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SmsTemplateDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SmsTemplateParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SmsTemplateQuery;
import com.ztesoft.zsmart.nros.crm.core.server.domain.smstemplate.SmsTemplateDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @ClassName SmsTemplateServiceImpl
 * @Description TODO
 * @Author will_lee
 * @Date 2019/6/12 9:26
 **/
@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(SmsTemplateServiceImpl.class);

    @Autowired
    private SmsTemplateDomain smsTemplateDomain;


    @Override
    public SmsTemplateDTO queryDetail(Long id) {
        return smsTemplateDomain.queryDetail(id);
    }

    @Override
    public PageInfo<SmsTemplateDTO> queryList(SmsTemplateQuery smsTemplateQuery) {
        return smsTemplateDomain.queryList(smsTemplateQuery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SmsTemplateParam smsTemplateParam) {
        smsTemplateDomain.add(smsTemplateParam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(SmsTemplateParam smsTemplateParam) {
        this.logger.info("this is the beginning of implement of smsTemplate modify ");
        smsTemplateDomain.modify(smsTemplateParam);
        this.logger.info("this is the end implement of smsTemplate modify");
    }

    @Override
    public void delete(Long id) {
        smsTemplateDomain.delete(id);
    }
}
