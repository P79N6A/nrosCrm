package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.CommonFunctions;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.crm.core.client.api.InviteCampaignService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignEditDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignDeleteList;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignFeedBackParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignStartOrStopParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.InviteRewardParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveInviteCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.QueryInviteCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.UserService;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.InviteCampaignDetailVO;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.InviteCampaignEditVO;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.InviteCampaignVO;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.InviteDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动controller
 * @date 2019-04-12
 */
@SessionController
@RequestMapping("/invite-campaign")
@Api(value = "邀请活动", tags = {
    "邀请活动"
})
public class InviteCampaignController {

    /**
     * 邀请活动service
     */
    @Autowired
    private InviteCampaignService inviteCampaignService;

    /**
     * 用户订阅服务service
     */
    @Autowired
    private UserService userService;

    @ApiOperation(value = "按名称查找邀请活动", notes = "按名称查找邀请活动")
    @GetMapping(value = "/list")
    public ResponseMsg<List<InviteCampaignVO>> listInviteCampaignByName(
        QueryInviteCampaignQuery queryInviteCampaignQuery) {
        PageInfo<InviteCampaignDTO> inviteCampaignDTOPageInfo = inviteCampaignService
            .listInviteActivity(queryInviteCampaignQuery);

        // 将DTO转VO
        List<InviteCampaignVO> inviteCampaignVOList = ConvertUtil.listEntity2DTO(inviteCampaignDTOPageInfo.getList(),
            InviteCampaignVO.class);
        // 根据创建人id去用户中心查名称 目前调不通
        // inviteCampaignVOList.forEach(item -> {
        // ResponseMsg<UserDTO> userDTOResponseMsg = userService.queryUser(item.getCreator());
        // if (userDTOResponseMsg.getData() != null) {
        // String creatorName = userDTOResponseMsg.getData().getUserName();
        // item.setCreatorName(creatorName);
        // }
        // });
        ResponseMsg<List<InviteCampaignVO>> responseMsg = new ResponseMsg<>(inviteCampaignVOList);
        responseMsg.setData(inviteCampaignVOList);
        responseMsg.setPageIndex(inviteCampaignDTOPageInfo.getPageNum())
            .setPageSize(inviteCampaignDTOPageInfo.getPageSize()).setTotal(inviteCampaignDTOPageInfo.getTotal());
        return responseMsg.success();
    }

    @ApiOperation(value = "创建邀请活动", notes = "创建邀请活动")
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsg createInviteCampaign(@RequestBody SaveInviteCampaignParam saveInviteCampaignParam) {
        return CommonFunctions.runSupplier(() -> inviteCampaignService.createInviteCampaign(saveInviteCampaignParam),
            "创建邀请活动失败");
    }

    @ApiOperation(value = "更新邀请活动", notes = "更新邀请活动")
    @RequestMapping(value = "/modify", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMsg updateInviteCampaign(@RequestBody SaveInviteCampaignParam saveInviteCampaignParam) {
        return CommonFunctions.runSupplier(() -> inviteCampaignService.updateInviteCampaign(saveInviteCampaignParam),
            "更新邀请活动失败");

    }

    @ApiOperation(value = "邀请活动详情查询", notes = "通过活动id查询")
    @GetMapping(value = "/detail")
    ResponseMsg queryInviteCampaignDetailById(@RequestParam(required = true) Long campaignId) {
        InviteCampaignDetailDTO campaignDetailDTO = inviteCampaignService.queryInviteCampaignDetailById(campaignId);

        InviteCampaignDetailVO campaignDetailVO = ConvertUtil.beanCopy(campaignDetailDTO, InviteCampaignDetailVO.class);
        return CommonFunctions.runSupplier(() -> campaignDetailVO, "查询邀请活动详情失败");

    }

    @ApiOperation(value = "活动编辑前查询奖励等信息", notes = "通过id查询")
    @GetMapping(value = "/edit-query")
    ResponseMsg queryEditDetailById(@RequestParam(required = true) Long id) {
        InviteCampaignEditDTO campaignEditDTO = inviteCampaignService.queryEditDetailById(id);

        InviteCampaignEditVO campaignEditVO = ConvertUtil.beanCopy(campaignEditDTO, InviteCampaignEditVO.class);
        return CommonFunctions.runSupplier(() -> campaignEditVO, "编辑邀请活动查询失败");

    }

    @ApiOperation(value = "邀请活动明细列表查询", notes = "邀请活动明细列表查询")
    @GetMapping(value = "/list-detail")
    ResponseMsg<List<InviteCampaignVO>> listInviteDetailByCampaignId(
        QueryInviteCampaignQuery queryInviteCampaignQuery) {
        PageInfo pageInfo = inviteCampaignService.listInviteDetail(queryInviteCampaignQuery);
        List<InviteDetailVO> inviteDetailVOList = ConvertUtil.listEntity2DTO(pageInfo.getList(), InviteDetailVO.class);
        pageInfo.setList(inviteDetailVOList);
        return CommonFunctions.runSupplierByPage(() -> pageInfo, "邀请活动明细列表查询失败");
    }

    /**
     * 删除邀请活动 通过活动ID删除
     *
     * @param campaignDeleteList
     */
    @ApiOperation(value = "删除邀请活动", notes = "删除邀请活动")
    @DeleteMapping(value = "/delete")
    ResponseMsg deleteInviteCampaign(@RequestBody CampaignDeleteList campaignDeleteList) {
        inviteCampaignService.deleteInviteCampaign(campaignDeleteList);
        ResponseMsg responseMsg = new ResponseMsg();
        return responseMsg.success();
    }

    /**
     * 活动启用与暂停
     *
     * @param campaignStartOrStopParam
     * @return
     */
    @ApiOperation(value = "活动启用停用", notes = "活动启用停用")
    @PutMapping(value = "/start-stop")
    ResponseMsg startOrStopCampaign(@RequestBody CampaignStartOrStopParam campaignStartOrStopParam) {
        return CommonFunctions.runSupplier(() -> inviteCampaignService.startOrStopCampaign(campaignStartOrStopParam),
            "活动状态变更失败");
    }

    @ApiOperation(value = "活动点击", notes = "活动点击量计数")
    @PostMapping(value = "/click")
    ResponseMsg campaignClickerStatistics(@RequestBody CampaignFeedBackParam campaignFeedBackParam) {
        inviteCampaignService.clickCampaign(campaignFeedBackParam);
        return new ResponseMsg().success();
    }

    @ApiOperation(value = "邀请活动奖励发放", notes = "邀请活动奖励发放")
    @PostMapping(value = "/reward")
    ResponseMsg sendReward(@RequestBody InviteRewardParam rewardParam) {
        return CommonFunctions.runSupplier(() -> {
            inviteCampaignService.sendReward(rewardParam);
            return new ResponseMsg().success();
        }, "邀请活动奖励发放失败");
    }


}
