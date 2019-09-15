package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.client.api.SmsTemplateService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SmsTemplateDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SmsTemplateParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SmsTemplateQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName SMSTemplateController
 * @Description 短信模板Controller
 * @Author will_lee
 * @Date 2019/6/10 16:39
 */
@SessionController
@RequestMapping("/sms-template")
@Api(value = "短信模板管理", tags = {
    "短信模板管理"
})
public class SmsTemplateController {

    @Autowired
    private SmsTemplateService smsTemplateService;

    /**
     * @Description 短信模板查找/列表
     * @Author will_lee
     * @Date 2019/6/13 14:25
     * @return com\ztesoft\zsmart\nros\base\model\ResponseMsg
     */
    @ApiOperation(value = "按名称查找短信模板", notes = "按名称查找短信模板")
    @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsg queryList(@RequestBody SmsTemplateQuery smsTemplateQuery) {
        ResponseMsg<List<SmsTemplateDTO>> responseMsg = new ResponseMsg<>();

        try {
            PageInfo<SmsTemplateDTO> pageInfo = smsTemplateService.queryList(smsTemplateQuery);
            responseMsg.success().setData(pageInfo.getList()).setPageIndex(pageInfo.getPageNum())
                .setPageSize(pageInfo.getPageSize()).setPages((long) pageInfo.getPages())
                .setTotal((pageInfo.getTotal()));
        }
        catch (BusiException e) {
            responseMsg.fail().setCode(e.getErrorCode()).setMessage(e.getErrorCode());
        }

        return responseMsg;
    }

    /**
     * @Description 短信模板新增
     * @Author will_lee
     * @Date 2019/6/13 14:25
     * @return com\ztesoft\zsmart\nros\base\model\ResponseMsg
     */
    @ApiOperation(value = "新增短信模板", notes = "新增短信模板")
    @PostMapping
    public ResponseMsg add(@RequestBody SmsTemplateParam smsTemplateParam) {
        smsTemplateService.add(smsTemplateParam);
        return new ResponseMsg().success();
    }

    /**
     * @Description 短信模板修改
     * @Author will_lee
     * @Date 2019/6/13 14:25
     * @return com\ztesoft\zsmart\nros\base\model\ResponseMsg
     */
    @ApiOperation(value = "修改短信模板", notes = "修改短信模板")
    @PutMapping
    public ResponseMsg modify(@RequestBody SmsTemplateParam smsTemplateParam) {
        smsTemplateService.modify(smsTemplateParam);
        return new ResponseMsg().success();
    }

    /**
     * @Description 短信模板删除
     * @Author will_lee
     * @Date 2019/6/13 14:21
     * @return com\ztesoft\zsmart\nros\base\model\ResponseMsg
     */
    @ApiOperation(value = "删除短信模板", notes = "删除短信模板")
    @DeleteMapping
    public ResponseMsg delete(@RequestParam Long id) {
        smsTemplateService.delete(id);
        return new ResponseMsg().success();

    }

    /**
     * @Description 获取某条模板详情
     * @Author will_lee
     * @Date 2019/6/15 14:51
     * @return
     * @param id
     */
    // @ApiOperation(value = "获取短信模板详情", notes = "获取短信模板详情")
    @GetMapping(value = "/detail/{id}")
    public ResponseMsg queryDetail(@PathVariable Long id) {
        SmsTemplateDTO smsTemplateDTO = smsTemplateService.queryDetail(id);
        return new ResponseMsg().success().setData(smsTemplateDTO);
    }
}
