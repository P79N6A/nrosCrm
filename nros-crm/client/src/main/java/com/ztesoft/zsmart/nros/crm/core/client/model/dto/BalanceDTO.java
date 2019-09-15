package com.ztesoft.zsmart.nros.crm.core.client.model.dto;

import com.ztesoft.zsmart.nros.common.model.BaseModel;

import lombok.Data;

/**
 * 储值DTO
 *
 * @author jiang.yifeng2
 * @version 1.0
 * @since 2019-06-04
 */
@Data
public class BalanceDTO extends BaseModel {

    private Long memberId;

    private String balanceCode;

    private String channel;

    private Long normalBalance;

    private Long giftBalance;

}