/**
 * [Product]
 * nrospromotion
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * BpmnModelUtilTest.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019/6/3   PQ         最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.middleware.util;

import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.BpmnModelUtil;

public class BpmnModelUtilTest {

    // 测试将前台JSON格式流程定义数据转换为标准BpmnModel数据
    @Test
    public void testBpmnModelConvert() {
        String json = "{\"children\":[{\"nodeName\":\"设置执行时间\",\"timeData\":{\"scheduleWay\":1,\"intervalUnit\":1,"
            + "\"interval\":1},\"periodType\":0,\"isLast\":0,\"children\":[{\"nodeName\":\"筛选目标客户\",\"isLast\":0,"
            + "\"groupId\":878,\"count\":\"258\",\"nodeType\":2,\"title\":\"测试050801\",\"parentId\":2,"
            + "\"condition\":[{\"contactgroup:contact_group_type\":\" ='static'\"}],"
            + "\"param\":{\"groupType\":\"STATIC\",\"staticGroupIds\":878},\"children\":[{\"nodeName\":\"发送短信\","
            + "\"actionType\":0,\"isLast\":0,\"param\":{\"sms_template_id\":\"\",\"selectType\":0,"
            + "\"copy\":\"17372776725\",\"content\":\"11111111http://t.cn/RfXmEdM退订回T【同仁堂健康】\","
            + "\"campaign_url\":\"http://t.cn/RfXmEdM\",\"h5_template_id\":\"\"},"
            + "\"children\":[{\"nodeName\":\"分组执行\",\"groupCount\":4,\"param\":[{\"rate\":\"20\",\"groupId\":1},"
            + "{\"rate\":\"20\",\"groupId\":2},{\"rate\":\"30\",\"groupId\":3},{\"rate\":\"30\",\"groupId\":4}],"
            + "\"isLast\":0,\"children\":[{\"nodeName\":\"1\",\"isLast\":0,\"rate\":\"20\","
            + "\"children\":[{\"nodeName\":\"发送短信\",\"actionType\":0,\"isLast\":1,"
            + "\"param\":{\"sms_template_id\":\"\",\"selectType\":\"\",\"copy\":\"17372776725\","
            + "\"content\":\"${name}测试个性化内容退订回T【同仁堂健康】\",\"campaign_url\":\"\",\"h5_template_id\":\"\"},"
            + "\"smsContent\":\"${name}测试个性化内容\",\"nodeDesc\":\"${name}测试个性化内容\",\"id\":10,\"nodeType\":3,"
            + "\"title\":\"\",\"seq\":8,\"parentId\":6}],\"groupId\":1,\"nodeDesc\":\"比例20%\",\"id\":6,"
            + "\"nodeType\":5,\"seq\":4,\"parentId\":5,\"groupWay\":0,\"svgIndex\":1},{\"nodeName\":\"2\","
            + "\"isLast\":0,\"rate\":\"20\",\"children\":[{\"nodeName\":\"赠送积分\",\"actionType\":3,"
            + "\"param\":{\"protect_date_type\":\"RIGHTNOW\",\"valid_date_type\":\"LASTED\",\"points\":\"17\"},"
            + "\"isLast\":1,\"nodeDesc\":\"增加17积分\",\"id\":11,\"nodeType\":3,\"seq\":9,\"parentId\":7}],"
            + "\"groupId\":2,\"nodeDesc\":\"比例20%\",\"id\":7,\"nodeType\":5,\"seq\":5,\"parentId\":5,\"groupWay\":0,"
            + "\"svgIndex\":2},{\"nodeName\":\"3\",\"isLast\":0,\"rate\":\"30\","
            + "\"children\":[{\"nodeName\":\"添加优惠券\",\"actionType\":4,\"param\":{\"couponType\":\"CRM\","
            + "\"card_id\":139},\"isLast\":1,\"nodeDesc\":\"满5元减4.99元\",\"id\":12,\"nodeType\":3,\"seq\":10,"
            + "\"parentId\":8}],\"groupId\":3,\"nodeDesc\":\"比例30%\",\"id\":8,\"nodeType\":5,\"seq\":6,"
            + "\"parentId\":5,\"groupWay\":0,\"svgIndex\":3},{\"nodeName\":\"4\",\"isLast\":0,\"rate\":\"30\","
            + "\"children\":[{\"nodeName\":\"打标签\",\"actionType\":6,\"param\":{\"tagIds\":[\"6\"],"
            + "\"tags\":[\"测试1\"]},\"isLast\":1,\"nodeDesc\":\"打标签\",\"id\":13,\"nodeType\":3,\"seq\":11,"
            + "\"parentId\":9}],\"groupId\":4,\"nodeDesc\":\"比例30%\",\"id\":9,\"nodeType\":5,\"seq\":7,"
            + "\"parentId\":5,\"groupWay\":0,\"svgIndex\":4}],\"nodeDesc\":\"\",\"id\":5,\"nodeType\":4,\"seq\":3,"
            + "\"groupWay\":0,\"parentId\":4}],\"smsContent\":\"11111111http://t.cn/RfXmEdM\","
            + "\"nodeDesc\":\"11111111http://t.cn/RfXmEdM\",\"id\":4,\"nodeType\":3,\"title\":\"\",\"seq\":2,"
            + "\"parentId\":3}],\"contactSelectType\":0,\"appletId\":\"9403b7a2-f185-4c09-950f-d67719ad2ff9\","
            + "\"nodeDesc\":\"测试050801\",\"id\":3,\"filterType\":1,\"seq\":1}],\"nodeDesc\":\"立即执行\",\"id\":2,"
            + "\"nodeType\":1,\"params\":{\"type\":\"time\"},\"seq\":0}]}";
        BpmnModel bpmnModel = BpmnModelUtil.getBpmnModelFromJson(json, 3L);
        System.out.println(BpmnModelUtil.getBpmnModelStrFromModel(bpmnModel));

        // 验证bpmnModel 是否是正确的bpmn xml文件
        ProcessValidatorFactory processValidatorFactory = new ProcessValidatorFactory();
        ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();
        // 验证失败信息的封装ValidationError
        List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
        Assert.assertEquals(0, validate.size());
    }

    // 测试JSON数据转换方法
    @Test
    public void testConvertExcuteToPreviewJson() {
        String marketingConfigJson = "[{\"nodeName\":\"设置执行时间\",\"nodeDesc\":\"立即执行\",\"nodeType\":1,\"seq\":0,"
            + "\"timeData\":{\"scheduleWay\":1,\"interval\":1,\"intervalUnit\":1},\"id\":2,\"parentId\":null,"
            + "\"isLast\":0,\"periodType\":0,\"params\":{\"type\":\"time\"}},{\"seq\":1,\"nodeType\":2,"
            + "\"nodeName\":\"筛选目标客户\",\"nodeDesc\":\"测试050801\","
            + "\"appletId\":\"9403b7a2-f185-4c09-950f-d67719ad2ff9\",\"contactSelectType\":0,\"id\":3,\"parentId\":2,"
            + "\"condition\":[{\"contactgroup:contact_group_type\":\" ='static'\"}],"
            + "\"param\":{\"groupType\":\"STATIC\",\"staticGroupIds\":878},\"isLast\":0,\"filterType\":1,"
            + "\"count\":\"258\",\"groupId\":878,\"title\":\"测试050801\"},{\"seq\":2,\"nodeType\":3,"
            + "\"nodeName\":\"发送短信\",\"nodeDesc\":\"11111111http://t.cn/RfXmEdM\",\"actionType\":0,\"title\":\"\","
            + "\"smsContent\":\"11111111http://t.cn/RfXmEdM\",\"id\":4,\"parentId\":3,\"isLast\":0,"
            + "\"param\":{\"content\":\"11111111http://t.cn/RfXmEdM退订回T【同仁堂健康】\",\"copy\":\"17372776725\","
            + "\"sms_template_id\":\"\",\"campaign_url\":\"http://t.cn/RfXmEdM\",\"selectType\":0,"
            + "\"h5_template_id\":\"\"}},{\"seq\":3,\"nodeType\":4,\"nodeName\":\"分组执行\",\"nodeDesc\":\"\","
            + "\"groupCount\":4,\"groupWay\":0,\"param\":[{\"groupId\":1,\"rate\":\"20\"},{\"groupId\":2,"
            + "\"rate\":\"20\"},{\"groupId\":3,\"rate\":\"30\"},{\"groupId\":4,\"rate\":\"30\"}],\"id\":5,"
            + "\"parentId\":4,\"isLast\":0},{\"seq\":4,\"nodeType\":5,\"nodeName\":\"1\",\"nodeDesc\":\"比例20%\","
            + "\"id\":6,\"parentId\":5,\"isLast\":0,\"groupWay\":0,\"groupId\":1,\"rate\":\"20\",\"svgIndex\":1},"
            + "{\"seq\":5,\"nodeType\":5,\"nodeName\":\"2\",\"nodeDesc\":\"比例20%\",\"id\":7,\"parentId\":5,"
            + "\"isLast\":0,\"groupWay\":0,\"groupId\":2,\"rate\":\"20\",\"svgIndex\":2},{\"seq\":6,\"nodeType\":5,"
            + "\"nodeName\":\"3\",\"nodeDesc\":\"比例30%\",\"id\":8,\"parentId\":5,\"isLast\":0,\"groupWay\":0,"
            + "\"groupId\":3,\"rate\":\"30\",\"svgIndex\":3},{\"seq\":7,\"nodeType\":5,\"nodeName\":\"4\","
            + "\"nodeDesc\":\"比例30%\",\"id\":9,\"parentId\":5,\"isLast\":0,\"groupWay\":0,\"groupId\":4,"
            + "\"rate\":\"30\",\"svgIndex\":4},{\"seq\":8,\"nodeType\":3,\"nodeName\":\"发送短信\","
            + "\"nodeDesc\":\"${name}测试个性化内容\",\"actionType\":0,\"title\":\"\",\"smsContent\":\"${name}测试个性化内容\","
            + "\"id\":10,\"parentId\":6,\"isLast\":1,\"param\":{\"content\":\"${name}测试个性化内容退订回T【同仁堂健康】\","
            + "\"copy\":\"17372776725\",\"sms_template_id\":\"\",\"campaign_url\":\"\",\"selectType\":\"\","
            + "\"h5_template_id\":\"\"}},{\"seq\":9,\"nodeType\":3,\"nodeName\":\"赠送积分\",\"nodeDesc\":\"增加17积分\","
            + "\"actionType\":3,\"param\":{\"protect_date_type\":\"RIGHTNOW\",\"valid_date_type\":\"LASTED\","
            + "\"points\":\"17\"},\"id\":11,\"parentId\":7,\"isLast\":1},{\"seq\":10,\"nodeType\":3,"
            + "\"nodeName\":\"添加优惠券\",\"nodeDesc\":\"满5元减4.99元\",\"actionType\":4,\"param\":{\"card_id\":139,"
            + "\"couponType\":\"CRM\"},\"id\":12,\"parentId\":8,\"isLast\":1},{\"seq\":11,\"nodeType\":3,"
            + "\"nodeName\":\"打标签\",\"nodeDesc\":\"打标签\",\"actionType\":6,\"param\":{\"tags\":[\"测试1\"],"
            + "\"tagIds\":[\"6\"]},\"id\":13,\"parentId\":9,\"isLast\":1}]";
        JSONObject resultJson = BpmnModelUtil.convertExcuteToPreviewJson(marketingConfigJson);
        System.out.println(resultJson.toString());
    }

}
