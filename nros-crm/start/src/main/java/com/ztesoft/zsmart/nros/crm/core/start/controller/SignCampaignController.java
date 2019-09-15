package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.CommonFunctions;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.crm.core.client.api.SignCampaignService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.CampaignServiceDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignStartOrStopParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveSignCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignInListQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.SignCampaignDetailVO;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.SignCampaignVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 报名活动
 *
 * @author fan.chaolin
 * @date 2019/4/12
 */
@SessionController
@RequestMapping("/sign-campaign")
@Api(value = "报名活动", tags = {"报名活动"})
public class SignCampaignController {

    @Autowired
    private SignCampaignService signCampaignService;

    /**
     * 修改报名活动信息
     * @param saveSignCampaignParam
     * @return
     */
    @ApiOperation("修改报名活动信息")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Long.class)})
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsg modifySignCampaign(@RequestBody SaveSignCampaignParam saveSignCampaignParam) {
        return CommonFunctions.runSupplier(() -> signCampaignService.modifySignCampaign(saveSignCampaignParam), "修改报名活动失败");
    }

    /**
     * 创建报名活动
     * @param saveSignCampaignParam
     * @return
     */
    @ApiOperation(value = "创建报名活动", notes = "创建报名活动")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsg createSignCampaign(@RequestBody SaveSignCampaignParam saveSignCampaignParam) {
        return CommonFunctions.runSupplier(() -> signCampaignService.createSignCampaign(saveSignCampaignParam), "创建报名活动失败");
    }

    /**
     * 查询报名活动列表
     * @param signCampaignQuery
     * @return
     */
    @ApiOperation("多条件查询报名活动列表")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = SignCampaignVO.class)})
    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsg queryList(@RequestBody SignCampaignQuery signCampaignQuery) {
        PageInfo<SignCampaignDTO> pageInfo = signCampaignService.queryList(signCampaignQuery);
        return CommonFunctions.runSupplierByPage(() -> pageInfo, "查询报名活动列表失败");
    }

    /**
     * 获取编辑页面参数
     * @param id 活动id
     * @return
     */
    @ApiOperation(value = "获取编辑页面参数", notes = "获取编辑页面参数")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = SignCampaignVO.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseMsg getSignCampaign(@RequestParam(required = true) Long id) {
        SignCampaignDTO signCampaignDTO = signCampaignService.getSignCampaign(id);
        SignCampaignVO signCampaignVO = ConvertUtil.beanCopy(signCampaignDTO, SignCampaignVO.class);
        return CommonFunctions.runSupplier(() -> signCampaignVO, "获取编辑页面参数失败");
    }

    /**
     * 查询报名活动详情
     * @param campaignId
     * @return
     */
    @ApiOperation(value = "报名活动详情查询", notes = "通过活动id查询")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = SignCampaignDetailVO.class)})
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    ResponseMsg querySignCampaignDetailById(@RequestParam(required = true) Long campaignId) {
        SignCampaignDetailDTO campaignDetailDTO = signCampaignService.querySignCampaignDetailById(campaignId);
        SignCampaignDetailVO campaignDetailVO = ConvertUtil.beanCopy(campaignDetailDTO, SignCampaignDetailVO.class);
        return CommonFunctions.runSupplier(() -> campaignDetailVO, "查询邀请活动详情失败");
    }

    /**
     * 查询报名活动报名用户列表
     * @param signInListQuery
     * @return
     */
    @ApiOperation(value = "报名活动报名用户列表", notes = "通过活动id查询")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/sign-list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseMsg querySignList(@RequestBody SignInListQuery signInListQuery) {
        PageInfo<CampaignServiceDTO> campaignServiceDTOPageInfo = signCampaignService.querySignList(signInListQuery);
        return CommonFunctions.runSupplierByPage(() -> campaignServiceDTOPageInfo, "查询报名列表失败");
    }

    /**
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "更改报名活动状态", notes = "启用或暂停")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @RequestMapping(value = "/start-or-stop", method = RequestMethod.POST)
    ResponseMsg modifyCampaignState(@RequestBody CampaignStartOrStopParam param) {
        return CommonFunctions.runSupplier(() -> signCampaignService.modifyCampaignState(param), "更改报名活动状态失败");
    }

    /**
     * 通过活动id删除报名活动
     * @param id 活动id
     * @return
     */
    @ApiOperation(value = "删除报名活动")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @RequestMapping(method = RequestMethod.DELETE)
    ResponseMsg deleteSignCampaign(@RequestParam Long id) {
        return CommonFunctions.runSupplier(() -> signCampaignService.deleteSignCampaign(id), "删除报名活动失败");
    }
}

