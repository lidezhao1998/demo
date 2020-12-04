package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 意见对象 t_task_idea
 * 
 * @author ruoyi
 * @date 2020-04-20
 */
public class TTaskIdea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 意见内容 */
    @Excel(name = "意见内容")
    private String ideaContent;

    /** 申请人 */
    @Excel(name = "申请人")
    private String ideaApplicant;

    /** 驳回人 */
    @Excel(name = "驳回人")
    private String ideaCreateUser;

    /** 任务id */
    @Excel(name = "任务id")
    private Integer taskId;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setIdeaContent(String ideaContent) 
    {
        this.ideaContent = ideaContent;
    }

    public String getIdeaContent() 
    {
        return ideaContent;
    }
    public void setIdeaApplicant(String ideaApplicant) 
    {
        this.ideaApplicant = ideaApplicant;
    }

    public String getIdeaApplicant() 
    {
        return ideaApplicant;
    }
    public void setIdeaCreateUser(String ideaCreateUser)
    {
        this.ideaCreateUser = ideaCreateUser;
    }

    public String getIdeaCreateUser()
    {
        return ideaCreateUser;
    }
    public void setTaskId(Integer taskId)
    {
        this.taskId = taskId;
    }

    public Integer getTaskId()
    {
        return taskId;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ideaContent", getIdeaContent())
            .append("ideaApplicant", getIdeaApplicant())
            .append("ideaCreateUser", getIdeaCreateUser())
            .append("taskId", getTaskId())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
