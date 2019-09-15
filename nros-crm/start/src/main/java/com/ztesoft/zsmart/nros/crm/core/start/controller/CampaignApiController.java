package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.annotation.AppSecretController;
import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.CommonFunctions;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.crm.core.client.api.InviteCampaignService;
import com.ztesoft.zsmart.nros.crm.core.client.api.SignCampaignService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.CampaignServiceDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.InviteRewardParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveCampaignServiceParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveInviteCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.QueryInviteCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.InviteCampaignVO;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.SignCampaignVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhou.xiaofeng
 * @description
 * @date 2019-06-10
 */

@AppSecretController
@RequestMapping("/campaign")
@Api(value = "营销活动对外API", tags = {
        "营销活动对外API"
})
public class CampaignApiController {

    /**
     * 邀请活动service
     */
    @Autowired
    private InviteCampaignService inviteCampaignService;

    @Autowired
    private SignCampaignService signCampaignService;


    @ApiOperation(value = "按名称查找邀请活动", notes = "按名称查找邀请活动")
    @GetMapping(value = "/list")
    public ResponseMsg<List<InviteCampaignVO>> listInviteCampaignByName(
            QueryInviteCampaignQuery queryInviteCampaignQuery) {
        PageInfo<InviteCampaignDTO> inviteCampaignDTOPageInfo = inviteCampaignService
                .listInviteActivity(queryInviteCampaignQuery);

        // 将DTO转VO
        List<InviteCampaignVO> inviteCampaignVOList = ConvertUtil.listEntity2DTO(inviteCampaignDTOPageInfo.getList(),
                InviteCampaignVO.class);
        ResponseMsg<List<InviteCampaignVO>> responseMsg = new ResponseMsg<>(inviteCampaignVOList);
        responseMsg.setData(inviteCampaignVOList);
        responseMsg.setPageIndex(inviteCampaignDTOPageInfo.getPageNum())
                .setPageSize(inviteCampaignDTOPageInfo.getPageSize()).setTotal(inviteCampaignDTOPageInfo.getTotal());
        return responseMsg.success();
    }

    @ApiOperation(value = "更新邀请活动", notes = "更新邀请活动")
    @RequestMapping(value = "/modify", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsg updateInviteCampaign(@RequestBody SaveInviteCampaignParam saveInviteCampaignParam) {
        return CommonFunctions.runSupplier(() -> inviteCampaignService.updateInviteCampaign(saveInviteCampaignParam),
                "更新邀请活动失败");

    }

    @ApiOperation(value = "邀请活动分享接口", notes = "邀请活动分享")
    @GetMapping(value = "/share")
    ResponseMsg<InviteCampaignDTO> shareCampaignById(@RequestParam(required = true) Long campaignId) {
        InviteCampaignDTO campaignDTO = inviteCampaignService.shareCampaignById(campaignId);
        return CommonFunctions.runSupplier(() -> campaignDTO, "邀请活动分享失败");
    }

    @ApiOperation(value = "邀请活动奖励发放", notes = "邀请活动奖励发放")
    @PostMapping(value = "/reward")
    ResponseMsg sendReward(@RequestBody InviteRewardParam rewardParam) {
        return CommonFunctions.runSupplier(() -> {
            inviteCampaignService.sendReward(rewardParam);
            return new ResponseMsg().success();
        }, "邀请活动奖励发放失败");
    }

    /**
     * 查询报名活动列表
     *
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

    @ApiOperation(value = "报名活动报名接口", notes = "报名活动报名")
    @GetMapping(value = "/sign-up")
    ResponseMsg signUp(@RequestBody SaveCampaignServiceParam saveCampaignServiceParam) {
        CampaignServiceDTO campaignServiceDTO = signCampaignService.signUp(saveCampaignServiceParam);
        return CommonFunctions.runSupplier(() -> campaignServiceDTO, "邀请活动报名失败");
    }

    @ApiOperation(value = "报名活动签到接口", notes = "报名活动签到")
    @GetMapping(value = "/sign-in")
    ResponseMsg signIn(@RequestBody SaveCampaignServiceParam saveCampaignServiceParam) {
        CampaignServiceDTO campaignServiceDTO = signCampaignService.signIn(saveCampaignServiceParam);
        return CommonFunctions.runSupplier(() -> campaignServiceDTO, "邀请活动签到失败");
    }
}
