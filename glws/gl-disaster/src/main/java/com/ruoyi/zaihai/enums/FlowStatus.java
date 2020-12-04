package com.ruoyi.zaihai.enums;
/**
 * 审核状态
 *
 * @author sudogndong
 * @date 2020-07-10
 */
public enum  FlowStatus {

    //草稿
    DRAF("00", "草稿"),
    //流程中
    RUN("01", "审核中"),
    //流程结束
    FINISH("02", "审核完成"),
    //撤销
    REVOCATION("03", "撤回"),
    //退回
    BACK("04", "退回");

    private final String status;

    private final String name;

     FlowStatus(String status, String name) {
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
