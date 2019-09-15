package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.GrowthRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.TagMemberParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.MemberService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.BalanceRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberBalanceDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberListDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.PointRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.PointUseRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagConfigDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.BalanceRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.GrowthRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberModifyParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberRegisterParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ModifyLoginPasswordParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.MemberPointVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员controller
 *
 * @author litao
 * @date 2019/6/11
 */
@SessionController
@RequestMapping("/member")
@Api(value = "会员管理", tags = "会员管理")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 会员列表查询
     *
     * @param memberQuery 查询条件
     * @return ResponseMsg<List   <   MemberListDTO>>
     */
    @PostMapping("/list-member")
    @ApiOperation("会员列表查询")
    public ResponseMsg<List<MemberListDTO>> listMember(@RequestBody MemberQuery memberQuery) {
        return memberService.queryMemberList(memberQuery);
    }

    /**
     * 批量新增会员标签关系
     *
     * @param tagMemberParam param
     * @return ResponseMsg<?>
     */
    @PostMapping("/tags-members")
    @ApiOperation("批量新增会员标签关系")
    public ResponseMsg<?> tagsMembers(@RequestBody TagMemberParam tagMemberParam) {
        List<TagParams> tagParams = new ArrayList<>();
        for (String memberId : tagMemberParam.getMemberIdList()) {
            for (String tagId : tagMemberParam.getTagIdList()) {
                TagParams tagParam = new TagParams();
                tagParam.setMemberId(Long.valueOf(memberId));
                tagParam.setTagId(Long.valueOf(tagId));
                tagParam.setTagType(tagMemberParam.getTagType());
                tagParams.add(tagParam);
            }
        }
        return memberService.saveMemberTagBatch(tagParams);
    }

    /**
     * 删除会员标签关联关系
     *
     * @param tagParams 查询条件
     * @return ResponseMsg<?>
     */
    @DeleteMapping("/delete-member-tag")
    @ApiOperation("删除会员标签关联关系")
    public ResponseMsg<?> deleteMemberTag(@RequestBody TagParams tagParams) {
        return memberService.modifyMemberTag(tagParams);
    }

    /**
     * 通过会员id查询会员详情
     *
     * @param memberId 会员id
     * @return ResponseMsg<MemberDetailDTO>
     */
    @GetMapping("/member-detail")
    @ApiOperation("通过会员id查询会员详情")
    public ResponseMsg<MemberDetailDTO> getDetailById(@RequestParam Long memberId) {
        return memberService.getDetailById(memberId);

    }

    /**
     * 通过会员id查询标签列表
     *
     * @param query 查询条件
     * @return ResponseMsg<List   <   TagDTO>>
     */
    @PostMapping("/tags-member-id")
    @ApiOperation("通过会员id查询标签列表")
    public ResponseMsg<List<TagDTO>> qyTagByMember(@RequestBody TagQuery query) {
        return memberService.qyTagByMember(query);
    }

    /**
     * 查询积分使用记录
     *
     * @param query query
     * @return ResponseMsg<List   <   PointUseRecordDTO>>
     */
    @PostMapping("/point-use-record")
    @ApiOperation("查询积分使用记录")
    public ResponseMsg<List<PointUseRecordDTO>> findPointUseRecord(@RequestBody PointRecordQuery query) {
        return memberService.findPointUseRecord(query);
    }

    /**
     * 查询积分增加记录
     *
     * @param query 查询条件
     * @return ResponseMsg<List   <   PointRecordDTO>>
     */
    @PostMapping(value = "/find-point-record")
    @ApiOperation("查询积分记录")
    public ResponseMsg<List<PointRecordDTO>> findPointRecord(@RequestBody PointRecordQuery query) {
        return memberService.findPointRecord(query);
    }

    /**
     * 查询会员余额
     *
     * @param memberId 会员id
     * @return ResponseMsg<BalanceDTO>
     */
    @GetMapping("/member-balance")
    @ApiOperation("查询会员余额")
    public ResponseMsg<MemberBalanceDTO> findMemberBalance(@RequestParam("memberId") Long memberId) {
        return memberService.findMemberBalance(memberId);
    }

    /**
     * 查询成长值记录
     *
     * @param query 查询条件
     * @return ResponseMsg
     */
    @PostMapping("/growth-record")
    @ApiOperation("查询成长值记录")
    public ResponseMsg<List<GrowthRecordDTO>> findGrowthRecord(@RequestBody GrowthRecordQuery query) {
        return memberService.findGrowthRecord(query);
    }

    /**
     * 查询充值消费记录
     *
     * @param query query
     * @return ResponseMsg<List   <   BalanceRecordDTO>>
     */
    @PostMapping("/balance-record-list")
    @ApiOperation("查询充值消费记录")
    public ResponseMsg<List<BalanceRecordDTO>> findBalanceRecordList(@RequestBody BalanceRecordQuery query) {
        return memberService.findBalanceRecordList(query);
    }

    /**
     * 查询当前积分情况
     *
     * @param memberId 会员id
     * @return ResponseMsg<MemberPointVO>
     */
    @GetMapping("/use-detail-point")
    @ApiOperation("查询当前积分情况")
    public ResponseMsg<MemberPointVO> findDetailPoint(@RequestParam Long memberId) {
        int totalPoint = memberService.findTotalPoint(memberId).getData();
        int totalUsePoint = memberService.findTotalUsePoint(memberId).getData();
        return ResponseMsg.build(new MemberPointVO().setTotalPoint(totalPoint).setUsePoint(totalUsePoint)
                .setSurplusPoint(totalPoint - totalUsePoint)).success();
    }

    /**
     * 查询所有有效的标签列表
     *
     * @param query query
     * @return ResponseMsg<TagConfigDTO>
     */
    @PostMapping(value = "tag-config-list")
    @ApiOperation("查询所有有效的标签列表")
    public ResponseMsg<List<TagConfigDTO>> qyTagConfigList(@RequestBody TagConfigQuery query) {
        return memberService.qyTagConfigList(query);
    }

    /**
     * 会员注册
     *
     * @param param param
     * @return ResponseMsg<Long>
     */
    @PostMapping("/register")
    @ApiOperation("会员注册")
    public ResponseMsg<MemberDTO> register(@RequestBody MemberRegisterParams param) {
        return memberService.register(param);
    }

    /**
     * 会员修改
     *
     * @param param param
     * @return ResponseMsg<Long>
     */
    @PostMapping("/modify")
    @ApiOperation("会员修改")
    public ResponseMsg<Long> modify(@RequestBody MemberModifyParams param) {
        return memberService.modify(param);
    }

    /**
     * 会员注销
     *
     * @param memberId 会员id
     * @return ResponseMsg
     */
    @DeleteMapping("/delete")
    @ApiOperation("会员注销")
    public ResponseMsg delete(@RequestParam(value = "memberId") Long memberId) {
        return memberService.delete(memberId);
    }

    /**
     * 修改登陆密码
     *
     * @param params params
     * @return ResponseMsg<Integer>
     */
    @PostMapping("/modify-login-password")
    @ApiOperation("修改登陆密码")
    public ResponseMsg<Integer> modifyLoginPassword(@RequestBody ModifyLoginPasswordParams params) {
        return memberService.modifyLoginPassword(params);
    }

    /**
     * 重置登陆密码
     *
     * @param params params
     * @return ResponseMsg<Integer>
     */
    @PostMapping("/reset-login-password")
    @ApiOperation("修改登陆密码")
    public ResponseMsg<Integer> resetLoginPassword(@RequestBody ModifyLoginPasswordParams params) {
        return memberService.resetLoginPassword(params);
    }

}
