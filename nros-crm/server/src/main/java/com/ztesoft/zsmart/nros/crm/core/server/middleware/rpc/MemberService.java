package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc;

import java.util.List;

import com.ztesoft.zsmart.nros.crm.core.client.model.dto.GrowthRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.BalanceRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberBalanceDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.PointRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.PointUseRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagConfigDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberModifyParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberRegisterParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ModifyLoginPasswordParams;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberListDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.BalanceRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.GrowthRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointUpdateParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;

/**
 * 会员服务远程调用接口定义
 * 
 * @author wangzhe
 * @date 2019/6/1 14:49
 */
public interface MemberService {

    /**
     * 更新积分记录
     * 
     * @param params params
     * @return ResponseMsg<Long>
     */
    ResponseMsg<Long> updatePointRecord(@RequestBody PointUpdateParams params);

    /**
     * 批量更新积分记录
     *
     * @param params params
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg
     * @author PQ
     * @date 2019/6/21
     */
    ResponseMsg batchUpdatePointRecord(@RequestBody List<PointUpdateParams> params);

    /**
     * 根据会员ID查询会员信息
     * 
     * @author yangshaoxin
     * @date 2019/4/28 11:10d
     * @param memberId memberId
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<com.ztesoft.zsmart.nros.sbc.crm.cloud.member.model.MemberDetailDTO>
     */
    ResponseMsg<MemberDetailDTO> getDetailById(@RequestParam("memberId") Long memberId);

    /**
     * 会员列表查询
     *
     * @param memberQuery memberQuery
     * @return ResponseMsg<List<MemberListDTO>>
     */
    ResponseMsg<List<MemberListDTO>> queryMemberList(@RequestBody MemberQuery memberQuery);

    /**
     * 根据标签查询会员列表
     *
     * @param tagId 标签id
     * @return ResponseMsg<List<MemberListDTO>>
     */
    ResponseMsg<List<MemberListDTO>> queryMemberByTag(@RequestParam("tagId") Long tagId);

    /**
     * 批量保存会员标签
     *
     * @param list list
     * @return ResponseMsg
     */
    ResponseMsg saveMemberTagBatch(@RequestBody List<TagParams> list);

    /**
     * 删除会员标签的关系（软删除,将status改为X）
     *
     * @param params params
     * @return ResponseMsg
     */
    ResponseMsg modifyMemberTag(@RequestBody TagParams params);

    /**
     * 通过会员id查询标签列表
     *
     * @param query query
     * @return ResponseMsg<List<TagDTO>>
     */
    ResponseMsg<List<TagDTO>> qyTagByMember(@RequestBody TagQuery query);

    /**
     * 查询积分使用记录
     *
     * @param query query
     * @return ResponseMsg<List<PointUseRecordDTO>>
     */
    ResponseMsg<List<PointUseRecordDTO>> findPointUseRecord(PointRecordQuery query);

    /**
     * 查询积分增加记录
     *
     * @param query query
     * @return ResponseMsg<List<PointRecordDTO>>
     */
    ResponseMsg<List<PointRecordDTO>> findPointRecord(PointRecordQuery query);

    /**
     * 查询会员余额
     *
     * @param memberId 会员id
     * @return ResponseMsg<BalanceDTO>
     */
    ResponseMsg<MemberBalanceDTO> findMemberBalance(@RequestParam("memberId") Long memberId);

    /**
     * 查询成长值记录
     *
     * @param query query
     * @return ResponseMsg<List<GrowthRecordDTO>>
     */
    ResponseMsg<List<GrowthRecordDTO>> findGrowthRecord(GrowthRecordQuery query);

    /**
     * 查询充值消费记录
     *
     * @param query query
     * @return ResponseMsg<List<BalanceRecordDTO>>
     */
    ResponseMsg<List<BalanceRecordDTO>> findBalanceRecordList(BalanceRecordQuery query);

    /**
     * 查询历史使用总积分
     *
     * @param memberId 会员id
     * @return ResponseMsg<Integer>
     */
    ResponseMsg<Integer> findTotalUsePoint(@RequestParam("memberId") Long memberId);

    /**
     * 查询历史总积分
     *
     * @param memberId 会员id
     * @return ResponseMsg<Integer>
     */
    ResponseMsg<Integer> findTotalPoint(@RequestParam("memberId") Long memberId);

    /**
     * 查询所有有效的标签列表
     * 
     * @param query query
     * @return ResponseMsg<TagConfigDTO>
     */
    ResponseMsg<List<TagConfigDTO>> qyTagConfigList(TagConfigQuery query);

    /**
     * 会员注册
     *
     * @param param param
     * @return ResponseMsg<Long>
     */
    ResponseMsg<MemberDTO> register(MemberRegisterParams param);

    /**
     * 会员修改
     *
     * @param param param
     * @return ResponseMsg<Long>
     */
    ResponseMsg<Long> modify(MemberModifyParams param);

    /**
     * 会员注销
     *
     * @param memberId 会员id
     * @return ResponseMsg
     */
    ResponseMsg delete(Long memberId);

    /**
     * 修改登陆密码
     *
     * @param params params
     * @return ResponseMsg<Integer>
     */
    ResponseMsg<Integer> modifyLoginPassword(ModifyLoginPasswordParams params);

    /**
     * 重置登录密码
     * @param params
     * @return
     */
    ResponseMsg<Integer> resetLoginPassword(ModifyLoginPasswordParams params);
}
