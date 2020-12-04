package com.ruoyi.zaihai.ReserveManagement.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 出库对象 k_outbound
 * 
 * @author ruoyi
 * @date 2020-06-16
 */
public class KOutbound extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 物资名称 */
    @Excel(name = "物资名称")
    private String name;

    /** 出库时间 */
    @Excel(name = "出库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warehousingTime;

    /** 储备库 */
    @Excel(name = "储备库", width = 30, dateFormat = "yyyy-MM-dd")
    private String warehousingMode;

    /** 减少原因 */
    @Excel(name = "减少原因")
    private String reason;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createName;

    /** 修改人 */
    @Excel(name = "修改人", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateName;

    /** 储备库id */
    @Excel(name = "储备库id")
    private Long reserveId;

    public Long getInstockId() {
        return instockId;
    }

    public void setInstockId(Long instockId) {
        this.instockId = instockId;
    }

    /** 出入库id */
    @Excel(name = "出入库id")
    private Long instockId;




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
    public void setWarehousingTime(Date warehousingTime)
    {
        this.warehousingTime = warehousingTime;
    }

    public Date getWarehousingTime()
    {
        return warehousingTime;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSatus()
    {
        return status;
    }
    public void setCreateName(String createName) 
    {
        this.createName = createName;
    }

    public String getCreateName() 
    {
        return createName;
    }
    public void setUpdateName(Date updateName) 
    {
        this.updateName = updateName;
    }

    public Date getUpdateName() 
    {
        return updateName;
    }
    public void setReserveId(Long reserveId) 
    {
        this.reserveId = reserveId;
    }

    public Long getReserveId() 
    {
        return reserveId;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("warehousingTime", getWarehousingTime())
                .append("warehousingMode", getWarehousingMode())

                .append("reason", getReason())
            .append("status", getStatus())
            .append("createName", getCreateName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateName", getUpdateName())
                .append("instockId", getInstockId())
            .toString();
    }

    public String getWarehousingMode() {
        return warehousingMode;
    }

    public void setWarehousingMode(String warehousingMode) {
        this.warehousingMode = warehousingMode;
    }
}
