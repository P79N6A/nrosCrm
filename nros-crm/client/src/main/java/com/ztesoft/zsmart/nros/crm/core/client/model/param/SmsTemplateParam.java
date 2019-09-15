package com.ztesoft.zsmart.nros.crm.core.client.model.param;


import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SMStemplateParam
 * @Description TODO
 * @Author will_lee
 * @Date 2019/6/10 16:57
 **/
@Data
@ApiModel("短信模板参数")
public class SmsTemplateParam extends BaseModel implements Serializable {

    @ApiModelProperty(value = "短信模板id")
    private Long id;

    @ApiModelProperty(value = "短信模板名称", required = true)
    private String templateName;

    @ApiModelProperty(value = "短信模板内容", required = true)
    private String templateContent;

    private static final Long serialVersionId = 1L;
}
