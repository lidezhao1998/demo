package com.ruoyi.zaihai.common.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 附件对象 c_resource
 * 
 * @author sudongdong
 * @date 2020-05-28
 */
public class CResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 真实名称 */
    @Excel(name = "真实名称")
    private String filename;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long size;

    /** 路径 */
    @Excel(name = "路径")
    private String url;

    /** 物理路径 */
    @Excel(name = "物理路径")
    private String fileurl;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setFileurl(String fileurl) 
    {
        this.fileurl = fileurl;
    }

    public String getFileurl() 
    {
        return fileurl;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("filename", getFilename())
                .append("size", getSize())
            .append("url", getUrl())
            .append("fileurl", getFileurl())
            .append("type", getType())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .append("updateDate", getUpdateDate())
            .append("updateBy", getUpdateBy())
            .toString();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
