package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件资源对象 file_resources
 * @author hdp
 * @date 2020-08-21
 */
public class FileResources extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件主键 */
    private Long fileId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件描述 */
    @Excel(name = "文件描述")
    private String fileDesc;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String fileUrl;

    /** 访问路径 */
    @Excel(name = "访问路径")
    private String playUrl;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 审核状态 */
    @Excel(name= "审核状态")
    private String auditStatus;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileDesc(String fileDesc) 
    {
        this.fileDesc = fileDesc;
    }

    public String getFileDesc() 
    {
        return fileDesc;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }
    public void setPlayUrl(String playUrl) 
    {
        this.playUrl = playUrl;
    }

    public String getPlayUrl() 
    {
        return playUrl;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }

    public void setAuditStatus(String auditStatus){
        this.auditStatus = auditStatus;
    }
    public String getAuditStatus() {
        return auditStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("fileName", getFileName())
            .append("fileDesc", getFileDesc())
            .append("fileUrl", getFileUrl())
            .append("playUrl", getPlayUrl())
            .append("fileType", getFileType())
            .append("auditStatus", getAuditStatus())
            .toString();
    }
}
