package com.ztesoft.zsmart.nros.crm.core.server.service;


import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.api.SmsTemplateService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SmsTemplateDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SmsTemplateParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SmsTemplateQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @ClassNameSms TemplateTest
 * @Description 单元测试
 * @Author will_lee
 * @Date 2019/6/14 17:29
 **/
public class SmsTemplateServiceTest extends MockitoTest {


    @Autowired
    private SmsTemplateService smsTemplateService;

    @Test
    public void deleteSmsTemplate(){
        Long id = 14L;
        smsTemplateService.delete(id);
    }

    @Test
    public void addSmsTemplate(){
        SmsTemplateParam smsTemplateParam = new SmsTemplateParam();
        smsTemplateParam.setTemplateName("短信认证模板");
        smsTemplateParam.setTemplateContent("这一块的功能需要修改，不知道后期会是什么样子！");
        smsTemplateService.add(smsTemplateParam);
    }
    @Test
    public void queryList(){
        SmsTemplateQuery smsTemplateQuery = new SmsTemplateQuery();
        smsTemplateQuery.setTemplateName("123");
        PageInfo<SmsTemplateDTO> pageInfo = smsTemplateService.queryList(smsTemplateQuery);
        System.out.println(pageInfo);
    }

    @Test
    public void modify(){
        SmsTemplateParam smsTemplateParam = new SmsTemplateParam();
        smsTemplateParam.setId(13L);
        smsTemplateParam.setTemplateName("验证码短信");
        smsTemplateParam.setTemplateContent("这是您的验证码${code},请保管好，不要告知他们！");
        smsTemplateService.modify(smsTemplateParam);
    }
    @Test
    public void selectOne(){
        SmsTemplateDTO smsTemplateDTO = smsTemplateService.queryDetail(1L);
        System.out.println(smsTemplateDTO);
    }
}
