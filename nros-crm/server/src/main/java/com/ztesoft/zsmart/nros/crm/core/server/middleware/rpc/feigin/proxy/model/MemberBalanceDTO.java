package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.crm.core.client.model.dto.BalanceDTO;
import lombok.Data;

import java.util.List;

/**
 * 会员储值DTO
 *
 * @author jiang.yifeng2
 * @version 1.0
 * @since 2019-07-06
 */
@Data
public class MemberBalanceDTO {

    private Long memberId;

    private Long normalBalance;

    private Long giftBalance;

    private List<BalanceDTO> balanceDTOList;

}
