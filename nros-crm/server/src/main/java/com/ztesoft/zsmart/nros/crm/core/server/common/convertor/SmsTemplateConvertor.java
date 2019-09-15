package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SmsTemplateDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SmsTemplateParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SmsTemplateQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.SmsTemplateDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.smstemplate.model.SmsTemplateBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * Description
 * <p>
 * 短信模板类型转换器
 * 
 * @Author will_lee
 * @Date 2019/6/12 11:21
 */
@Mapper
public interface SmsTemplateConvertor
    extends IConvertor<SmsTemplateParam, SmsTemplateQuery, SmsTemplateDTO, SmsTemplateBO, SmsTemplateDO> {

    SmsTemplateConvertor INSTANCE = Mappers.getMapper(SmsTemplateConvertor.class);

    /**
     * DOPage转DTOPage
     * 
     * @Author will_lee
     * @param smsTemplateDOPageInfo
     * @return PageInfo<smsTemplateDTO>
     */
    PageInfo<SmsTemplateDTO> doPageToDTO(PageInfo<SmsTemplateDO> smsTemplateDOPageInfo);
}
