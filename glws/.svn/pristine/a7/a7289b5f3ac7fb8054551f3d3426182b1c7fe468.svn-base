package com.ruoyi.system.domain.gis;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  gis_Features
 * 
 * @author ruoyi
 */
public class GisFeatures extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** shpFileID */
    private Long id;

    /** 父shpFileID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    private String name;

    /** 显示顺序 */
    private String orderNum;

    /** shpFile状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    private String parentName;

    private String server_name;

    @Override
    public String toString() {
        return "GisFeatures{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", ancestors='" + ancestors + '\'' +
                ", name='" + name + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", parentName='" + parentName + '\'' +
                ", server_name='" + server_name + '\'' +
                '}';
    }

    public GisFeatures() {
    }

    public GisFeatures(Long id, Long parentId, String ancestors, String name, String orderNum, String status, String delFlag, String parentName, String server_name ) {
        this.id = id;
        this.parentId = parentId;
        this.ancestors = ancestors;
        this.name = name;
        this.orderNum = orderNum;
        this.status = status;
        this.delFlag = delFlag;
        this.parentName = parentName;
        this.server_name = server_name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

}
