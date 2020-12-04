package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 等级对象 grade_setting
 * 
 * @author hdp
 * @date 2020-10-12
 */
public class GradeSetting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 等级名 */
    @Excel(name = "等级名")
    private String gradeName;

    /** 等级值 */
    @Excel(name = "等级值")
    private Double gradeValue;

    /** 描述 */
    @Excel(name = "描述")
    private String gradeDesc;

    /** 类型 */
    @Excel(name = "类型")
    private String gradeType;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setGradeName(String gradeName) 
    {
        this.gradeName = gradeName;
    }

    public String getGradeName() 
    {
        return gradeName;
    }
    public void setGradeValue(Double gradeValue) 
    {
        this.gradeValue = gradeValue;
    }

    public Double getGradeValue() 
    {
        return gradeValue;
    }
    public void setGradeDesc(String gradeDesc) 
    {
        this.gradeDesc = gradeDesc;
    }

    public String getGradeDesc() 
    {
        return gradeDesc;
    }
    public void setGradeType(String gradeType) 
    {
        this.gradeType = gradeType;
    }

    public String getGradeType() 
    {
        return gradeType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gradeName", getGradeName())
            .append("gradeValue", getGradeValue())
            .append("gradeDesc", getGradeDesc())
            .append("gradeType", getGradeType())
            .toString();
    }
}
