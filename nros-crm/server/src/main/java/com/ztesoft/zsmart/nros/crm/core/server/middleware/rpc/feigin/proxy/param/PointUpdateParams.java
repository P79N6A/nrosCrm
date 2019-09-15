package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @description 积分变更接口参数
 * @author wang.yulin01
 * @date 2019/4/15 14:28
 * @version V1.0
 **/
@Data
public class PointUpdateParams extends BaseModel {

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 变化的积分
     */
    private Integer point;

    /**
     * 积分变化时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date effDate;

    /**
     * 积分失效时间-为空则永久有效
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expDate;

    /**
     * 积分变化渠道
     */
    private String channel;

    /**
     * 订单编号
     */
    private String bizOrder;

    /**
     * 积分变化说明
     */
    private String description;
}
