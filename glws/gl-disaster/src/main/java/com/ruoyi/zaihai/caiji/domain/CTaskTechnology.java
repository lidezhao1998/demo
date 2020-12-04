package com.ruoyi.zaihai.caiji.domain;

import com.ruoyi.zaihai.common.domain.CResource;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 科技成果信息对象 c_task_technology
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
public class CTaskTechnology extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 提交人 */
    @Excel(name = "提交人")
    private String sumbitter;

    /** 成果类型 */
    @Excel(name = "成果类型")
    private String name;

    /** 所属技术领域 */
    @Excel(name = "所属技术领域")
    private String technicalField;

    /** email */
    @Excel(name = "email")
    private String email;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private Long phone;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String fax;

    /** 技术成熟度 */
    @Excel(name = "技术成熟度")
    private String technologyMaturity;

    /** 研发方式 */
    @Excel(name = "研发方式")
    private String rdmethods;

    /** 合作方式 */
    @Excel(name = "合作方式")
    private String cooperationMethod;

    /** 成果应用情况 */
    @Excel(name = "成果应用情况")
    private String resultsApplication;

    /** 实施成果转化需求 */
    @Excel(name = "实施成果转化需求")
    private String demand;

    /** 成果应用行业 */
    @Excel(name = "成果应用行业")
    private String industry;

    /** 获奖类别 */
    @Excel(name = "获奖类别")
    private String awardCategory;

    /** 获奖级别 */
    @Excel(name = "获奖级别")
    private String awardLevel;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 填报时间 */
    @Excel(name = "填报时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputTime;



    /** 上报时间 */
    @Excel(name = "上报时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportTime;


    /** 创建人 */
    @Excel(name = "创建人")
    private String creatBy;


    /** 状态 */
    @Excel(name = "状态")
    private String status;


    /** 文件地址 */
    @Excel(name = "文件地址")
    private String fileUrl;

    /** 文件地址 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 附件表 */
    private CResource cResource;

    public String getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSumbitter(String sumbitter) 
    {
        this.sumbitter = sumbitter;
    }

    public String getSumbitter() 
    {
        return sumbitter;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setTechnicalField(String technicalField) 
    {
        this.technicalField = technicalField;
    }

    public String getTechnicalField() 
    {
        return technicalField;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPhone(Long phone) 
    {
        this.phone = phone;
    }

    public Long getPhone() 
    {
        return phone;
    }
    public void setFax(String fax) 
    {
        this.fax = fax;
    }

    public String getFax() 
    {
        return fax;
    }
    public void setTechnologyMaturity(String technologyMaturity) 
    {
        this.technologyMaturity = technologyMaturity;
    }

    public String getTechnologyMaturity() 
    {
        return technologyMaturity;
    }
    public void setRdmethods(String rdmethods) 
    {
        this.rdmethods = rdmethods;
    }

    public String getRdmethods() 
    {
        return rdmethods;
    }
    public void setCooperationMethod(String cooperationMethod) 
    {
        this.cooperationMethod = cooperationMethod;
    }

    public String getCooperationMethod() 
    {
        return cooperationMethod;
    }
    public void setResultsApplication(String resultsApplication) 
    {
        this.resultsApplication = resultsApplication;
    }

    public String getResultsApplication() 
    {
        return resultsApplication;
    }
    public void setDemand(String demand)
    {
        this.demand = demand;
    }

    public String getDemand() 
    {
        return demand;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setAwardCategory(String awardCategory) 
    {
        this.awardCategory = awardCategory;
    }

    public String getAwardCategory() 
    {
        return awardCategory;
    }
    public void setAwardLevel(String awardLevel) 
    {
        this.awardLevel = awardLevel;
    }

    public String getAwardLevel() 
    {
        return awardLevel;
    }
    public void setCreatTime(Date creatTime)
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime()
    {
        return creatTime;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public CResource getcResource() {
        return cResource;
    }

    public void setcResource(CResource cResource) {
        this.cResource = cResource;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sumbitter", getSumbitter())
            .append("name", getName())
            .append("technicalField", getTechnicalField())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("phone", getPhone())
            .append("fax", getFax())
            .append("technologyMaturity", getTechnologyMaturity())
            .append("rdmethods", getRdmethods())
            .append("cooperationMethod", getCooperationMethod())
            .append("resultsApplication", getResultsApplication())
            .append("demand", getDemand())
            .append("industry", getIndustry())
            .append("awardCategory", getAwardCategory())
            .append("awardLevel", getAwardLevel())
            .append("creatTime", getCreatTime())
            .append("inputTime", getInputTime())
            .append("reportTime", getReportTime())
            .append("createBy", getCreateBy())
            .append("status", getStatus())
            .append("fileUrl", getFileUrl())
            .append("fileName", getFileName())
            .toString();
    }
}
