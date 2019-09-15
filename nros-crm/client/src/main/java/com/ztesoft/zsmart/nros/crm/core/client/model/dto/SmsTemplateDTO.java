package com.ztesoft.zsmart.nros.crm.core.client.model.dto;


import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SmsTemplateDTO
 * @Description 短信模板传输对象
 * @Author will_lee
 * @Date 2019/6/10 20:00
 **/
@Data
public class SmsTemplateDTO extends BaseModel implements Serializable {

    private Long id;
    /**
     * 短信模板名称
     */
    private String templateName;

    /**
     * 短信模板内容
     */
    private String templateContent;

    private static final long SerialVersionUID = 1L;
}
