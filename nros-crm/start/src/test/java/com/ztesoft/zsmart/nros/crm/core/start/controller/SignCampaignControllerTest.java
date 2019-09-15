package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignReserveTimeParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveSignCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignInListQuery;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * 报名活动单元测试
 * @author yangkai
 * @date 2019/7/09
 */
public class SignCampaignControllerTest extends MockitoTest {
    protected static final Logger logger = LoggerFactory.getLogger(SignCampaignControllerTest.class);

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysDo(print()).
            addFilter(new CharacterEncodingFilter(),"/*").build();
    }

    /**
     * 修改报名活动单元测试
     * @throws Exception
     */
    @Test
    public void testModify() throws Exception{
        SaveSignCampaignParam saveSignCampaignParam = new SaveSignCampaignParam();
        saveSignCampaignParam.setId(7L);
        saveSignCampaignParam.setName("修改测试活动77777777777");
        saveSignCampaignParam.setSignStartTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-12"));
        saveSignCampaignParam.setSignEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-15"));
        saveSignCampaignParam.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-06-15"));
        saveSignCampaignParam.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-15"));
        saveSignCampaignParam.setButtonText("立即报名");
        saveSignCampaignParam.setCampaignState("5");
        saveSignCampaignParam.setLocation("NYC");
        saveSignCampaignParam.setAttendNumControl(200);
        saveSignCampaignParam.setRichDetail("修改测试活动详情");
        saveSignCampaignParam.setRichDigest("修改测试活动摘要");
        saveSignCampaignParam.setIsSignInOpen("1");
        saveSignCampaignParam.setContactPhone("13522222222");
        saveSignCampaignParam.setRichPic("xxxxxxxxxxxxxxx");
        saveSignCampaignParam.setSubmitTitle("修改报名成功");
        saveSignCampaignParam.setSubmitDescription("活动已成功报名，请留意活动信息");
        List<CampaignReserveTimeParam> campaignReserveTimeList = new ArrayList<>();
        CampaignReserveTimeParam campaignReserveTimeParam = new CampaignReserveTimeParam();
        campaignReserveTimeParam.setReserveStartTime(new SimpleDateFormat("HH:mm:ss").parse("14:00:00"));
        campaignReserveTimeParam.setReserveEndTime(new SimpleDateFormat("HH:mm:ss").parse("16:00:00"));
        campaignReserveTimeList.add(campaignReserveTimeParam);
        saveSignCampaignParam.setCampaignReserveTimeParamList(campaignReserveTimeList);
        mockMvc.perform(MockMvcRequestBuilders.put("/sign-campaign")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSONObject.toJSONString(saveSignCampaignParam)))
            .andReturn();
    }

    /**
     * 新增报名活动
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception{
        SaveSignCampaignParam saveSignCampaignParam = new SaveSignCampaignParam();
        saveSignCampaignParam.setName("奈良奈良");
        saveSignCampaignParam.setSignStartTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-12"));
        saveSignCampaignParam.setSignEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-15"));
        saveSignCampaignParam.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-12"));
        saveSignCampaignParam.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-15"));
        saveSignCampaignParam.setButtonText("立即报名");
        saveSignCampaignParam.setCampaignState("2");
        saveSignCampaignParam.setLocation("NYC");
        saveSignCampaignParam.setAttendNumControl(1200);
        saveSignCampaignParam.setRichDetail("测试活动详情");
        saveSignCampaignParam.setRichDigest("测试活动摘要");
        saveSignCampaignParam.setIsSignInOpen("1");
        saveSignCampaignParam.setContactPhone("13212345678");
        saveSignCampaignParam.setRichPic("xxxxxxxxxxxxxxx");
        saveSignCampaignParam.setSubmitTitle("报名成功");
        saveSignCampaignParam.setIsAuditOpen("1");
        saveSignCampaignParam.setSubmitDescription("活动已成功报名，请留意活动信息");
        List<CampaignReserveTimeParam> campaignReserveTimeList = new ArrayList<>();
        CampaignReserveTimeParam campaignReserveTimeParam = new CampaignReserveTimeParam();
        campaignReserveTimeParam.setReserveStartTime(new SimpleDateFormat("HH:mm:ss").parse("10:00:00"));
        campaignReserveTimeParam.setReserveEndTime(new SimpleDateFormat("HH:mm:ss").parse("12:00:00"));
        campaignReserveTimeList.add(campaignReserveTimeParam);
        saveSignCampaignParam.setCampaignReserveTimeParamList(campaignReserveTimeList);
        mockMvc.perform(MockMvcRequestBuilders.post("/sign-campaign")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSONObject.toJSONString(saveSignCampaignParam)))
            .andReturn();
    }

    /**
     * 报名活动列表单元测试
     * @throws Exception
     */
    @Test
    public void testSignCampaignList() throws Exception {
        SignCampaignQuery signCampaignQuery = new SignCampaignQuery();
        Map<String , Object> map = new HashMap<>();
        map.put("userName", "admin");
        map.put("userId", "1");
        JSONObject creator = new JSONObject(map);
        signCampaignQuery.setCreator(creator);
        mockMvc.perform(MockMvcRequestBuilders.post("/sign-campaign/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(signCampaignQuery)))
                .andReturn();
    }

    /**
     * 获取编辑页面参数单元测试
     * @throws Exception
     */
    @Test
    public void testEdit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/sign-campaign/{id}", 125L));
    }

    /**
     * 报名活动详情单元测试
     * @throws Exception
     */
    @Test
    public void testDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/sign-campaign/detail/{campaignId}", 89L)).andReturn();
    }

    @Test
    public void testSignList() throws Exception {
        SignInListQuery signInListQuery = new SignInListQuery();
        signInListQuery.setCampaignId(89L);
        signInListQuery.setIsSignIn("1");
        mockMvc.perform(MockMvcRequestBuilders.post("/sign-campaign/sign-list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(signInListQuery)))
                .andReturn();
    }
}
