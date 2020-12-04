package com.sinosoft.integration.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * shpFile表 sys_shpFile
 * 
 * @author ruoyi
 */
public class SysShpFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** shpFileID */
    private Long shpFileId;

    /** 父shpFileID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** shpFile名称 */
    private String shpFileName;

    /** 显示顺序 */
    private String orderNum;

    /** shpFile状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 父shpFile名称 */
    private String parentName;
    /**
     * 图层颜色
     */
    private String shpColor;

    /**
     * 详细地址
     */
    private String shpFilePath;
    /*文件名称
     */
    private String fileName;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getShpFileId() {
        return shpFileId;
    }

    public void setShpFileId(Long shpFileId) {
        this.shpFileId = shpFileId;
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

    public String getShpFileName() {
        return shpFileName;
    }

    public void setShpFileName(String shpFileName) {
        this.shpFileName = shpFileName;
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

    public String getShpColor() {
        return shpColor;
    }

    public void setShpColor(String shpColor) {
        this.shpColor = shpColor;
    }

    public String getShpFilePath() {
        return shpFilePath;
    }

    public void setShpFilePath(String shpFilePath) {
        this.shpFilePath = shpFilePath;
    }

    public SysShpFile() {
    }

    public SysShpFile(Long shpFileId, Long parentId, String ancestors, String shpFileName, String orderNum, String status, String delFlag, String parentName, String shpColor, String shpFilePath) {
        this.shpFileId = shpFileId;
        this.parentId = parentId;
        this.ancestors = ancestors;
        this.shpFileName = shpFileName;
        this.orderNum = orderNum;
        this.status = status;
        this.delFlag = delFlag;
        this.parentName = parentName;
        this.shpColor = shpColor;
        this.shpFilePath = shpFilePath;
    }

    @Override
    public String toString() {
        return "SysShpFile{" +
                "shpFileId=" + shpFileId +
                ", parentId=" + parentId +
                ", ancestors='" + ancestors + '\'' +
                ", shpFileName='" + shpFileName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", parentName='" + parentName + '\'' +
                ", shpColor='" + shpColor + '\'' +
                ", shpFilePath='" + shpFilePath + '\'' +
                '}';
    }
}
