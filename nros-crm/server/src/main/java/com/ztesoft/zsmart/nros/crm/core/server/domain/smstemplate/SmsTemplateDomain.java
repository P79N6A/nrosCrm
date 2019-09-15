package com.ztesoft.zsmart.nros.crm.core.server.domain.smstemplate;


import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.crm.core.server.domain.smstemplate.model.SmsTemplateBO;
import org.apache.commons.lang3.StringUtils;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SmsTemplateDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SmsTemplateParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SmsTemplateQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.SmsTemplateConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.repository.SmsTemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @ClassName SmsTemplateDomain
 * @Description 短信模板
 * @Author will_lee
 * @Date 2019/6/12 9:14
 **/
@Component
public class SmsTemplateDomain {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(SmsTemplateDomain.class);

    /**
     *
     */
    @Autowired
    private SmsTemplateRepository smsTemplateRepository;


    public PageInfo<SmsTemplateDTO> queryList(SmsTemplateQuery smsTemplateQuery) {
        return smsTemplateRepository.queryList(smsTemplateQuery);
    }


    public void add(SmsTemplateParam smsTemplateParam) {
        logger.info("test the args of the smsTemplateParam");
        //参数校验
        if (StringUtils.isBlank(smsTemplateParam.getTemplateName())) {
            ExceptionHandler.publish("NROS-SBC-CRM-SMSTEMPLATE-0001");
        }
        if (StringUtils.isBlank(smsTemplateParam.getTemplateContent())) {
            ExceptionHandler.publish("NROS-SBC-CRM-SMSTEMPLATE-0002");
        }
        //参数对象转BO
        SmsTemplateBO smsTemplateBO = SmsTemplateConvertor.INSTANCE.paramToBO(smsTemplateParam);
        logger.info("handle business of smsTemplate(add)");
        smsTemplateRepository.add(smsTemplateBO);
        logger.info("finish business of smsTemplate(add)");
    }

    public void modify(SmsTemplateParam smsTemplateParam) {
        //参数校验
        logger.info(smsTemplateParam.toString());
        logger.info("test the args of the smsTemplateParam");
        if (StringUtils.isBlank(smsTemplateParam.getTemplateName())) {
            ExceptionHandler.publish("NROS-SBC-CRM-SMSTEMPLATE-0001");
        }
        if (StringUtils.isBlank(smsTemplateParam.getTemplateContent())) {
            ExceptionHandler.publish("NROS-SBC-CRM-SMSTEMPLATE-0002");
        }
        //参数对象转BO
        SmsTemplateBO smsTemplateBO = SmsTemplateConvertor.INSTANCE.paramToBO(smsTemplateParam);
        logger.info("handle business of smsTemplate(modify)");
        smsTemplateRepository.modify(smsTemplateBO);
        //todo
        logger.info("finish business of smsTemplate(modify)");
    }

    public void delete(Long id) {
        smsTemplateRepository.delete(id);
    }

    public SmsTemplateDTO queryDetail(Long id) {
        return smsTemplateRepository.queryDetail(id);

    }
}
