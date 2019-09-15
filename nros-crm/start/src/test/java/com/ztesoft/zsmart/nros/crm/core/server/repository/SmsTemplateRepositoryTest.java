package com.ztesoft.zsmart.nros.crm.core.server.repository;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SmsTemplateDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SmsTemplateQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.SmsTemplateConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.SmsTemplateDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.SmsTemplateMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.SmsTemplateDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.domain.smstemplate.model.SmsTemplateBO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yuanxiaokai
 * @date 2019/7/15
 */
public class SmsTemplateRepositoryTest extends MockitoTest {

    @Autowired
    @InjectMocks
    private SmsTemplateRepository smsTemplateRepository;

    @Mock
    private SmsTemplateDOMapper smsTemplateDOMapper;

    @Mock
    private SmsTemplateMapper smsTemplateMapper;

    @Test
    public void testQueryList() {
        SmsTemplateQuery smsTemplateQuery = new SmsTemplateQuery();
        SmsTemplateDO smsTemplateDO = SmsTemplateConvertor.INSTANCE.queryToDO(smsTemplateQuery);
        List<SmsTemplateDO> smsTemplateDOList = Lists.newArrayList(smsTemplateDO);
        Mockito.when(smsTemplateMapper.queryList(smsTemplateDO)).thenReturn(smsTemplateDOList);

        PageInfo<SmsTemplateDO> smsTemplateDOPageInfo = new PageInfo<>(smsTemplateDOList);
        PageInfo<SmsTemplateDTO> smsTemplateDTOPageInfo = SmsTemplateConvertor.INSTANCE
            .doPageToDTO(smsTemplateDOPageInfo);
        Assert.assertEquals(smsTemplateDTOPageInfo.toString(),
            smsTemplateRepository.queryList(smsTemplateQuery).toString());
    }

    @Test
    public void testAdd() {
        SmsTemplateBO smsTemplateBO = new SmsTemplateBO();
        Mockito.when(smsTemplateDOMapper.insertSelective(SmsTemplateConvertor.INSTANCE.boToDO(smsTemplateBO)))
            .thenReturn(1);
        smsTemplateRepository.add(smsTemplateBO);
    }

    @Test
    public void testModify() {
        SmsTemplateBO smsTemplateBO = new SmsTemplateBO();
        Mockito.when(smsTemplateDOMapper.updateByPrimaryKey(SmsTemplateConvertor.INSTANCE.boToDO(smsTemplateBO)))
            .thenReturn(1);
        smsTemplateRepository.modify(smsTemplateBO);
    }

    @Test
    public void testDelete() {
        Mockito.when(smsTemplateDOMapper.deleteByPrimaryKey(1L)).thenReturn(1);
        smsTemplateRepository.delete(1L);
    }

    @Test
    public void testQueryDetail() {
        SmsTemplateDO smsTemplateDO = new SmsTemplateDO();
        Mockito.when(smsTemplateMapper.selectOne(1L)).thenReturn(smsTemplateDO);
        Assert.assertEquals(SmsTemplateConvertor.INSTANCE.doToDTO(smsTemplateDO),
            smsTemplateRepository.queryDetail(1L));
    }

}
