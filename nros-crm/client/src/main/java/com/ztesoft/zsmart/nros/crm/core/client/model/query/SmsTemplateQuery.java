package com.ztesoft.zsmart.nros.crm.core.client.model.query;


import com.ztesoft.zsmart.nros.common.model.param.PageParam;
import lombok.Data;
import java.io.Serializable;

/**
 * 查询短信模板列表参数
 *
 * @ClassName SmsTemplateQuery
 * @Description TODO
 * @Author will_lee
 * @Date 2019/6/10 17:26
 */
@Data
public class SmsTemplateQuery extends PageParam implements Serializable {

    /**
     * 短信模板名称
     */
    private String templateName;
}
