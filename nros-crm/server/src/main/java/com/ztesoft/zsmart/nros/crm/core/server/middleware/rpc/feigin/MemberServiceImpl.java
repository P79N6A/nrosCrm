package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin;

import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.CommonFunctions;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.GrowthRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.SortFieldEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.SortTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.MemberService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.MemberProxy;
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
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 会员服务远程调用实现类
 *
 * @author wangzhe
 * @date 2019/4/9 13:28
 */
@Service
@Setter
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberProxy memberProxy;

    @Override
    public ResponseMsg<Long> updatePointRecord(PointUpdateParams params) {
        return memberProxy.savePointRecord(params);
    }

    @Override
    public ResponseMsg batchUpdatePointRecord(List<PointUpdateParams> params) {
        return memberProxy.saveBatchPointRecord(params);
    }

    @Override
    public ResponseMsg<MemberDetailDTO> getDetailById(Long memberId) {
        ResponseMsg<MemberDetailDTO> responseMsg = memberProxy.getDetailById(memberId);
        if (Objects.isNull(responseMsg.getData())) {
            ExceptionHandler.publish("NROS-SBC-CRM-MEMBER-0001");
        }

        // 会员等级名称
        List<MemberLevelConfigDTO> memberLevelConfigDTOList = new ArrayList<>();
        ResponseMsg<List<MemberLevelConfigDTO>> listResponseMsg = memberProxy.getLevelConfigList();
        if (listResponseMsg != null) {
            memberLevelConfigDTOList = listResponseMsg.getData();
        }
        if (responseMsg.getData().getLevel() != null) {
            responseMsg.getData()
                .setLevelName(getLevelName(responseMsg.getData().getLevel(), memberLevelConfigDTOList));
        }

        // 账户余额
        MemberBalanceDTO memberBalanceDTO = memberProxy.findMemberBalance(memberId).getData();
        responseMsg.getData().setNormalBalance(memberBalanceDTO.getNormalBalance());
        responseMsg.getData().setGiftBalance(memberBalanceDTO.getGiftBalance());
        responseMsg.getData().setAllBalance(memberBalanceDTO.getNormalBalance() + memberBalanceDTO.getGiftBalance());

        return responseMsg;
    }

    @Override
    public ResponseMsg<List<MemberListDTO>> queryMemberList(MemberQuery memberQuerye) {
        memberQuerye.setSortField(SortFieldEnum.GROWTH);
        memberQuerye.setSortType(SortTypeEnum.DESC);
        ResponseMsg<List<MemberListDTO>> responseMsg = memberProxy.queryMemberList(memberQuerye);
        if (responseMsg != null) {
            List<MemberListDTO> listDTOS = responseMsg.getData();
            if (!CollectionUtils.isEmpty(listDTOS)) {
                List<MemberLevelConfigDTO> memberLevelConfigDTOList = new ArrayList<>();
                ResponseMsg<List<MemberLevelConfigDTO>> listResponseMsg = memberProxy.getLevelConfigList();
                if (listResponseMsg != null) {
                    memberLevelConfigDTOList = listResponseMsg.getData();
                }
                for (MemberListDTO memberListDTO : listDTOS) {
                    if (memberListDTO.getLevel() != null) {
                        memberListDTO.setLevelName(getLevelName(memberListDTO.getLevel(), memberLevelConfigDTOList));
                    }
                }
                responseMsg.setData(listDTOS);
            }
        }
        return responseMsg;
    }

    private String getLevelName(Integer level, List<MemberLevelConfigDTO> memberLevelConfigDTOList) {
        String levelName = "";
        if (!CollectionUtils.isEmpty(memberLevelConfigDTOList)) {
            for (MemberLevelConfigDTO memberLevelConfigDTO : memberLevelConfigDTOList) {
                if (level.intValue() == memberLevelConfigDTO.getLevel().intValue()) {
                    levelName = memberLevelConfigDTO.getLevelName();
                    break;
                }
            }
        }
        return levelName;
    }

    @Override
    public ResponseMsg<List<MemberListDTO>> queryMemberByTag(Long tagId) {
        int pageIndex = 1;
        TagQuery tagQuery = new TagQuery();
        tagQuery.setTagId(tagId);
        tagQuery.setPageIndex(pageIndex);
        tagQuery.setPageSize(PromotionContants.MAX_QUERY_PAGE_SIZE);
        ResponseMsg<List<MemberListDTO>> responseMsg = memberProxy.qyMemberByTag(tagQuery);
        Long totalRecord = responseMsg.getTotal();
        List<MemberListDTO> memberListDtoList = responseMsg.getData();
        // TODO:@PQ 单一标签下用户数体量？分页循环加载方式，海量数据情况下是否会存在问题？
        while (totalRecord > memberListDtoList.size()) {
            tagQuery.setPageIndex(++pageIndex);
            memberListDtoList.addAll(memberProxy.qyMemberByTag(tagQuery).getData());
        }
        return CommonFunctions.runSupplierByList(() -> memberListDtoList, "根据标签查询会员列表失败！");
    }

    @Override
    public ResponseMsg saveMemberTagBatch(List<TagParams> list) {
        return memberProxy.saveMemberTagBatch(list);
    }

    @Override
    public ResponseMsg modifyMemberTag(TagParams params) {
        return memberProxy.modifyMemberTag(params);
    }

    @Override
    public ResponseMsg<List<TagDTO>> qyTagByMember(TagQuery query) {
        return memberProxy.qyTagByMember(query);
    }

    @Override
    public ResponseMsg<List<PointUseRecordDTO>> findPointUseRecord(PointRecordQuery query) {
        return memberProxy.findPointUseRecord(query);
    }

    @Override
    public ResponseMsg<List<PointRecordDTO>> findPointRecord(PointRecordQuery query) {
        return memberProxy.findPointRecord(query);
    }

    @Override
    public ResponseMsg<MemberBalanceDTO> findMemberBalance(Long memberId) {
        return memberProxy.findMemberBalance(memberId);
    }

    @Override
    public ResponseMsg<List<GrowthRecordDTO>> findGrowthRecord(GrowthRecordQuery query) {
        return memberProxy.findGrowthRecord(query);
    }

    @Override
    public ResponseMsg<List<BalanceRecordDTO>> findBalanceRecordList(BalanceRecordQuery query) {
        // 展示 普通金额+赠送金额
        ResponseMsg<List<BalanceRecordDTO>> responseMsg = memberProxy.findBalanceRecordList(query);
        List<BalanceRecordDTO> data = responseMsg.getData();
        if (CollectionUtils.isEmpty(data)) {
            return responseMsg;
        }
        data.forEach(balanceRecordDTO -> {
            if (Objects.equals("RECHARGE", balanceRecordDTO.getRecordType())) {
                if (!StringUtils.isEmpty(balanceRecordDTO.getNormalAmount())
                    && !StringUtils.isEmpty(balanceRecordDTO.getGiftAmount())) {
                    balanceRecordDTO.setNormalAmount(String.valueOf(Long.valueOf(balanceRecordDTO.getNormalAmount())
                        + Long.valueOf(balanceRecordDTO.getGiftAmount())));
                }
            }
        });
        return responseMsg;
    }

    @Override
    public ResponseMsg<Integer> findTotalUsePoint(Long memberId) {
        return memberProxy.findTotalUsePoint(memberId);
    }

    @Override
    public ResponseMsg<Integer> findTotalPoint(Long memberId) {
        return memberProxy.findTotalPoint(memberId);
    }

    @Override
    public ResponseMsg<List<TagConfigDTO>> qyTagConfigList(TagConfigQuery query) {
        return memberProxy.qyTagConfigList(query);
    }

    @Override
    public ResponseMsg<MemberDTO> register(MemberRegisterParams param) {
        return memberProxy.register(param);
    }

    @Override
    public ResponseMsg<Long> modify(MemberModifyParams param) {
        return memberProxy.modify(param);
    }

    @Override
    public ResponseMsg delete(Long memberId) {
        ResponseMsg<MemberBalanceDTO> memberBalance = memberProxy.findMemberBalance(memberId);
        MemberBalanceDTO memberBalanceDTO = memberBalance.getData();
        MemberDeleteParam memberDeleteParam = new MemberDeleteParam();
        memberDeleteParam.setId(memberId);
        // 无账户 可以注销
        if (Objects.isNull(memberBalanceDTO)) {
            memberProxy.removeMember(memberDeleteParam);
            return ResponseMsg.buildSuccess();
        }
        if (memberBalanceDTO.getNormalBalance() > 0 || memberBalanceDTO.getGiftBalance() > 0) {
            return ResponseMsg.buildFail("1", "当前会员余额不为0，禁止注销！");
        }
        // 无储值可以注销
        memberProxy.removeMember(memberDeleteParam);
        return ResponseMsg.buildSuccess();
    }

    @Override
    public ResponseMsg<Integer> modifyLoginPassword(ModifyLoginPasswordParams params) {
        // params.setOldPassword(BCrypt.hashpw(params.getNewPassword(), BCrypt.gensalt()));
        return memberProxy.modifyLoginPassword(params);
    }

    @Override
    public ResponseMsg<Integer> resetLoginPassword(ModifyLoginPasswordParams params) {
        return memberProxy.resetLoginPassword(params);
    }
}
