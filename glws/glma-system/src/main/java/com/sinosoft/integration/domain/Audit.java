package com.sinosoft.integration.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/3 17:51
 * 审核信息
 */
public class Audit extends BaseEntity {
    private int id;
    private String auditUser;
    private String auditOrg;
    private String auditResult;
    private String auditContent;
    private Date auditDate;
    private int normalInspectionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getAuditOrg() {
        return auditOrg;
    }

    public void setAuditOrg(String auditOrg) {
        this.auditOrg = auditOrg;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public int getNormalInspectionId() {
        return normalInspectionId;
    }

    public void setNormalInspectionId(int normalInspectionId) {
        this.normalInspectionId = normalInspectionId;
    }
}
