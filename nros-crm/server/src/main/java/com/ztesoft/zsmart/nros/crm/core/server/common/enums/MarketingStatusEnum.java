package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * 营销流程状态枚举类
 * 
 * @author wangzhe
 * @date 2018/12/10 9:08
 */
public enum MarketingStatusEnum {
    /**
     * 设计中
     */
    IN_DESIGN("0", "设计中"),
    /**
     * 生效
     */
    ENABLE("1", "生效"),

    /**
     * 失效
     */
    DISABLE("2", "失效");

    /**
     * state
     */
    private String state;

    /**
     * name
     */
    private String name;

    /**
     * 构造方法
     * 
     * @param state
     * @param name
     */
    MarketingStatusEnum(String state, String name) {
        this.name = name;
        this.state = state;
    }

    /**
     * 获取name
     * 
     * @param state state
     * @return name
     */
    private static String getName(String state) {
        for (MarketingStatusEnum c : MarketingStatusEnum.values()) {
            if (c.getState().equals(state)) {
                return c.name;
            }
        }
        return null;
    }

    /**
     * 获取name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 获取state
     * 
     * @return state
     */
    public String getState() {
        return state;
    }
}
