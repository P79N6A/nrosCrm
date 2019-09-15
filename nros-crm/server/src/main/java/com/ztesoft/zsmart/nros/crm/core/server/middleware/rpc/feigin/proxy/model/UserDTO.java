package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户DTO
 *
 * @author bin.yu
 * @create 2019-04-23 10:27
 **/
@Data
public class UserDTO implements Serializable {

    /**
     * T_BFM_USER_ID_SEQ-user_id
     */
    private Long userId;

    /**
     * T_BFM_USER_ID_SEQ-user_name
     */
    private String userName;

    /**
     * T_BFM_USER_ID_SEQ-email
     */
    private String email;

    /**
     * T_BFM_USER_ID_SEQ-phone
     */
    private String phone;

    /**
     * T_BFM_USER_ID_SEQ-工号,login
     */
    private String userCode;

    /**
     * T_BFM_USER_ID_SEQ-pwd
     */
    private String pwd;

    /**
     * T_BFM_USER_ID_SEQ-address
     */
    private String address;

    /**
     * T_BFM_USER_ID_SEQ-memo
     */
    private String memo;

    /**
     * T_BFM_USER_ID_SEQ-user_eff_date
     */
    private Date userEffDate;

    /**
     * T_BFM_USER_ID_SEQ-空表示永不实效
     */
    private Date userExpDate;

    /**
     * T_BFM_USER_ID_SEQ-created_date
     */
    private Date createdDate;

    /**
     * T_BFM_USER_ID_SEQ-A-在用，X-失效
     */
    private String state;

    /**
     * T_BFM_USER_ID_SEQ-state_date
     */
    private Date stateDate;

    /**
     * T_BFM_USER_ID_SEQ-是否锁定，'Y'-锁定，'N'-没有锁定，null表示'N'
     */
    private String isLocked;

    /**
     * T_BFM_USER_ID_SEQ-密码过期时间，空表示永不过期
     */
    private Date pwdExpDate;

    /**
     * T_BFM_USER_ID_SEQ-登录失败次数，空表示0
     */
    private Integer loginFail;

    /**
     * T_BFM_USER_ID_SEQ-unlock_date
     */
    private Date unlockDate;

    /**
     * T_BFM_USER_ID_SEQ-portal_id
     */
    private Integer portalId;

    /**
     * T_BFM_USER_ID_SEQ-update_date
     */
    private Date updateDate;

}
