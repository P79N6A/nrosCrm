package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * @author zhou.xiaofeng
 * @description 活动审核状态枚举类
 * @date 2019-04-15
 */

public enum AuditStatusEnum {

    /**
     * 设计中
     */
    DESIGNING("设计中", "0"),

    /**
     * 审核中
     */
    INAUDIT("审核中", "1"),

    /**
     * 审核不通过
     */
    REFUSED("审核不通过", "2"),

    /**
     * 未开始
     */
    NOTSTART("未开始", "3"),

    /**
     * 进行中
     */
    INPROCESS("进行中", "4"),

    /**
     * 暂停中
     */
    PAUSE("暂停中", "5"),

    /**
     * 已结束
     */
    FINISH("已结束", "6"),

    /**
     * 取消预约
     */
    CANCEL_RESERVATION("取消预约", "7");

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String state;

    AuditStatusEnum(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public static String getName(String state) {
        for (AuditStatusEnum c : AuditStatusEnum.values()) {
            if (c.getState().equals(state)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    public String getState() {
        return state;
    }

    /*public void setState(String state) {
        this.state = state;
    }*/
}
