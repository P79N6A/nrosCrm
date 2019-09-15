package com.ztesoft.zsmart.nros.crm.core.server.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.core.repository.BaseRepository;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SmsTemplateDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SmsTemplateQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.SmsTemplateConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.SmsTemplateDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.SmsTemplateMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.SmsTemplateDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.domain.smstemplate.model.SmsTemplateBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName SmsTemplateRepository
 * @Description TODO
 * @Author will_lee
 * @Date 2019/6/10 20:37
 **/
@Repository
public class SmsTemplateRepository implements BaseRepository {
    private Logger logger = LoggerFactory.getLogger(SmsTemplateRepository.class);

    @Autowired
    private SmsTemplateDOMapper smsTemplateDOMapper;

    @Autowired
    private SmsTemplateMapper smsTemplateMapper;

    public void setSmsTemplateMapper(SmsTemplateMapper smsTemplateMapper) {
        this.smsTemplateMapper = smsTemplateMapper;
    }

    public void setSmsTemplateDOMapper(SmsTemplateDOMapper smsTemplateDOMapper) {
        this.smsTemplateDOMapper = smsTemplateDOMapper;
    }

    public PageInfo<SmsTemplateDTO> queryList(SmsTemplateQuery smsTemplateQuery) {
        // 开始分页
        PageHelper.startPage(smsTemplateQuery.getPageIndex(), smsTemplateQuery.getPageSize());
        // Query对象转DO
        SmsTemplateDO smsTemplateDO = SmsTemplateConvertor.INSTANCE.queryToDO(smsTemplateQuery);
        // 查询短信模板列表
        logger.info("begin database handling of smsTemplate(query)");
        List<SmsTemplateDO> smsTemplateDOList = smsTemplateMapper.queryList(smsTemplateDO);
        logger.info("end database handling of smsTemplate(query)");

        // 构建分页对象
        PageInfo<SmsTemplateDO> smsTemplateDOPageInfo = new PageInfo<>(smsTemplateDOList);
        // DOPage转DTOPage
        PageInfo<SmsTemplateDTO> smsTemplateDTOPageInfo = SmsTemplateConvertor.INSTANCE
            .doPageToDTO(smsTemplateDOPageInfo);

        // todo
        return smsTemplateDTOPageInfo;
    }

    public void add(SmsTemplateBO smsTemplateBO) {
        // BO转DO
        SmsTemplateDO smsTemplateDO = SmsTemplateConvertor.INSTANCE.boToDO(smsTemplateBO);
        logger.info("begin database handling of smsTemplate(add)");
        smsTemplateDOMapper.insertSelective(smsTemplateDO);
        logger.info("end database handling of smsTemplate(add)");

    }

    public void modify(SmsTemplateBO smsTemplateBO) {
        // BO转DO
        SmsTemplateDO smsTemplateDO = SmsTemplateConvertor.INSTANCE.boToDO(smsTemplateBO);
        logger.info("begin database handling of smsTemplate(modify)");
        smsTemplateMapper.updateByPrimaryKey(smsTemplateDO);
        logger.info("end database handling of smsTemplate(modify)");
    }

    public void delete(Long id) {
        smsTemplateDOMapper.deleteByPrimaryKey(id);
    }

    public SmsTemplateDTO queryDetail(Long id) {
        logger.info("begin database handling of smsTemplate(selectOne)");
        return SmsTemplateConvertor.INSTANCE.doToDTO(smsTemplateMapper.selectOne(id));
    }
}
