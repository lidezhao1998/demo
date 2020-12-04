package com.ruoyi.zaihai.enums;

/**
 * 审核类型
 *
 * @author sudogndong
 * @date 2020-07-10
 */

public enum  FlowType {

    //地面调查数据
    DMTC("1", "地面调查"),
    //鼠害防治数据
    SHFZ("2", "鼠害防治"),
    //虫害防治数据
    CHFZ("3", "虫害防治"),
    //农药使用数据
    NYSY("4", "农药使用"),
    //防治年报数据
    FZNB("5", "防治年报");

    private final String status;

    private final String name;

    FlowType(String status, String name) {
        this.status = status;
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
