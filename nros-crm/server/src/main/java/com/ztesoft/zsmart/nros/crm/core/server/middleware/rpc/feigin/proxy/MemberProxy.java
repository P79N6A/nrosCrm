package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.GrowthRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.BalanceRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberBalanceDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberLevelConfigDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberListDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.PointRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.PointUseRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagConfigDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.BalanceRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.GrowthRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberDeleteParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberModifyParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberRegisterParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ModifyLoginPasswordParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointUpdateParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 会员中心接口订阅
 *
 * @author wangzhe
 * @date 2019/4/12 14:14
 */
@FeignClient(value = "member")
public interface MemberProxy {

    /**
     * 更新积分记录
     *
     * @param params params
     * @return ResponseMsg<Long>
     */
    @PostMapping(value = "/nrosapi/member/v1/point/add-point")
    ResponseMsg<Long> savePointRecord(@RequestBody PointUpdateParams params);

    /**
     * 批量更新积分记录
     *
     * @param params params
     * @return ResponseMsg
     * @author PQ
     * @date 2019/6/21
     */
    @PostMapping(value = "/nrosapi/member/v1/point/batch-add-point")
    ResponseMsg saveBatchPointRecord(@RequestBody List<PointUpdateParams> params);

    /**
     * 根据会员ID查询会员信息
     *
     * @param memberId 会员id
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<com.ztesoft.zsmart.nros.sbc.crm.cloud.member.model.MemberDetailDTO>
     * @author yangshaoxin
     * @date 2019/4/28 11:10
     */
    @GetMapping(value = "/nrosapi/member/v1/member/get-by-member-id")
    ResponseMsg<MemberDetailDTO> getDetailById(@RequestParam("memberId") Long memberId);

    /**
     * 会员列表查询
     *
     * @param memberQuery memberQuery
     * @return ResponseMsg<List                                                                                                                               <                                                                                                                               MemberListDTO>>
     */
    @GetMapping(value = "/nrosapi/member/v1/member/query-member-list")
    ResponseMsg<List<MemberListDTO>> queryMemberList(@RequestBody MemberQuery memberQuery);

    /**
     * 批量保存会员标签
     *
     * @param list list
     * @return ResponseMsg
     */
    @PostMapping(value = "/nrosapi/member/v1/tag/save-tag-batch")
    ResponseMsg saveMemberTagBatch(@RequestBody List<TagParams> list);

    /**
     * 删除会员标签的关系（软删除,将status改为X）
     *
     * @param params params
     * @return ResponseMsg
     */
    @PostMapping(value = "/nrosapi/member/v1/tag/modify-tag")
    ResponseMsg modifyMemberTag(@RequestBody TagParams params);

    /**
     * 通过会员id查询标签列表
     *
     * @param query query
     * @return ResponseMsg<List                                                                                                                               <                                                                                                                               TagDTO>>
     */
    @PostMapping(value = "/nrosapi/member/v1/tag/find-tag-by-member")
    ResponseMsg<List<TagDTO>> qyTagByMember(@RequestBody TagQuery query);

    /**
     * 查询积分使用记录
     *
     * @param query query
     * @return ResponseMsg<List                                                                                                                               <                                                                                                                               PointUseRecordDTO>>
     */
    @PostMapping(value = "/nrosapi/member/v1/point/find-point-use-record")
    ResponseMsg<List<PointUseRecordDTO>> findPointUseRecord(@RequestBody PointRecordQuery query);

    /**
     * 查询积分记录
     *
     * @param query query
     * @return ResponseMsg<List                                                                                                                               <                                                                                                                               PointRecordDTO>>
     */
    @PostMapping(value = "/nrosapi/member/v1/point/find-point-record")
    ResponseMsg<List<PointRecordDTO>> findPointRecord(@RequestBody PointRecordQuery query);

    /**
     * 查询会员余额
     *
     * @param memberId 会员id
     * @return ResponseMsg<BalanceDTO>
     */
    @GetMapping(value = "/nrosapi/member/v1/member/balance/find-member-balance-by-member-id")
    ResponseMsg<MemberBalanceDTO> findMemberBalance(@RequestParam(value = "memberId") Long memberId);

    /**
     * 查询成长值记录
     *
     * @param query query
     * @return ResponseMsg<List                                                                                                                               <                                                                                                                               GrowthRecordDTO>>
     */
    @PostMapping("/nrosapi/member/v1/growth/find-growth-record")
    ResponseMsg<List<GrowthRecordDTO>> findGrowthRecord(@RequestBody GrowthRecordQuery query);

    /**
     * 查询充值消费记录
     *
     * @param query query
     * @return ResponseMsg<List                                                                                                                               <                                                                                                                               BalanceRecordDTO>>
     */
    @PostMapping("/nrosapi/member/v1/member/balance/find-balance-record-for-page")
    ResponseMsg<List<BalanceRecordDTO>> findBalanceRecordList(@RequestBody BalanceRecordQuery query);

    /**
     * 查询历史使用总积分
     *
     * @param memberId 会员id
     * @return ResponseMsg<Integer>
     */
    @GetMapping("/nrosapi/member/v1/point/find-total-use-point")
    ResponseMsg<Integer> findTotalUsePoint(@RequestParam("memberId") Long memberId);

    /**
     * 查询历史获取总积分
     *
     * @param memberId 会员id
     * @return ResponseMsg<Integer>
     */
    @GetMapping("/nrosapi/member/v1/point/find-total-point")
    ResponseMsg<Integer> findTotalPoint(@RequestParam("memberId") Long memberId);

    /**
     * 根据标签查询会员列表
     *
     * @param tagQuery tagQuery
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg
     * @author PQ
     * @date 2019/6/24
     */
    @PostMapping("/nrosapi/member/v1/tag/find-member-by-tag")
    ResponseMsg<List<MemberListDTO>> qyMemberByTag(@RequestBody TagQuery tagQuery);

    /**
     * 查询所有有效的标签列表
     *
     * @param query query
     * @return ResponseMsg<TagConfigDTO>
     */
    @PostMapping("/nrosapi/member/v1/tag-config/find-tag-config-list")
    ResponseMsg<List<TagConfigDTO>> qyTagConfigList(@RequestBody TagConfigQuery query);

    /**
     * 会员注册
     *
     * @param param param
     * @return ResponseMsg<Long>
     */
    @PostMapping("/nrosapi/member/v1/member/register")
    ResponseMsg<MemberDTO> register(MemberRegisterParams param);

    /**
     * 会员修改
     *
     * @param param param
     * @return ResponseMsg<Long>
     */
    @PostMapping("/nrosapi/member/v1/member/modify")
    ResponseMsg<Long> modify(MemberModifyParams param);

    /**
     * 会员注销
     *
     * @param memberDeleteParam 会员id
     * @return ResponseMsg
     */
    @DeleteMapping("/nrosapi/member/v1/member/remove-member")
    ResponseMsg removeMember(@RequestBody MemberDeleteParam memberDeleteParam);

    /**
     * 修改登陆密码
     *
     * @param params params
     * @return ResponseMsg<Integer>
     */
    @PostMapping("/nrosapi/member/v1/member/modify-login-password")
    ResponseMsg<Integer> modifyLoginPassword(@RequestBody ModifyLoginPasswordParams params);

    /**
     * 重置登录密码
     *
     * @param params
     * @return
     */
    @PostMapping("/nrosapi/member/v1/member/reset-login-password")
    ResponseMsg<Integer> resetLoginPassword(@RequestBody ModifyLoginPasswordParams params);

    /**
     * 查询等级配置
     *
     * @return
     */
    @GetMapping("/nrosapi/member/v1/member-level-config/list")
    ResponseMsg<List<MemberLevelConfigDTO>> getLevelConfigList();
}