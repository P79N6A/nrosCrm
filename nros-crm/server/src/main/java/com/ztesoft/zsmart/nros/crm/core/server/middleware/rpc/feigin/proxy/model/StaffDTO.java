package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2019-04-23
 * Time: 16:05
 */
@Data
public class StaffDTO implements Serializable {
    /**
     * StaffDO-staff_id
     */
    private Long staffId;

    /**
     * StaffDO-staff_name
     */
    private String staffName;

    /**
     * StaffDO-staff_code
     */
    private String staffCode;

    /**
     * StaffDO-email
     */
    private String email;

    /**
     * StaffDO-phone
     */
    private String phone;

    /**
     * StaffDO-address
     */
    private String address;

    /**
     * StaffDO-created_date
     */
    private Date createdDate;

    /**
     * StaffDO-state
     */
    private String state;

    /**
     * StaffDO-state_date
     */
    private Date stateDate;

    /**
     * StaffDO-user_id
     */
    private Integer userId;

    /**
     * StaffDO-ext_str_01
     */
    private String extStr01;

    /**
     * StaffDO-ext_str_02
     */
    private String extStr02;

    /**
     * StaffDO-ext_str_03
     */
    private String extStr03;

    /**
     * StaffDO-ext_str_04
     */
    private String extStr04;

    /**
     * StaffDO-ext_str_05
     */
    private String extStr05;

    /**
     * StaffDO-ext_str_06
     */
    private String extStr06;

    /**
     * StaffDO-ext_str_07
     */
    private String extStr07;

    /**
     * StaffDO-ext_str_08
     */
    private String extStr08;

    /**
     * StaffDO-ext_str_09
     */
    private String extStr09;

    /**
     * StaffDO-ext_str_10
     */
    private String extStr10;

    /**
     * StaffDO-ext_str_11
     */
    private String extStr11;

    /**
     * StaffDO-ext_str_12
     */
    private String extStr12;

    /**
     * StaffDO-ext_num_01
     */
    private Integer extNum01;

    /**
     * StaffDO-ext_num_02
     */
    private Integer extNum02;

    /**
     * StaffDO-ext_dat_01
     */
    private Date extDat01;

    /**
     * StaffDO-update_date
     */
    private Date updateDate;

    /**
     * StaffDO-ext_dat_02
     */
    private Date extDat02;


    //机构名称
    private String orgName;

    //机构主键
    private Long orgId;

    //职位名称
    private String jobName;

    //职位主键
    private Long jobId;


}
