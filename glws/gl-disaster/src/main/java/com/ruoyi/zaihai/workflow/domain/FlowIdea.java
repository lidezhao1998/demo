package com.ruoyi.zaihai.workflow.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 流程意见对象 flow_idea
 * 
 * @author sudogndong
 * @date 2020-07-10
 */
public class FlowIdea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** null */
    @Excel(name = "null")
    private String filetypeid;

    /** null */
    @Excel(name = "null")
    private String recordid;

    /** null */
    @Excel(name = "null")
    private String ideafieldname;

    /** null */
    @Excel(name = "null")
    private String idea;

    /** null */
    @Excel(name = "null")
    private String tablename;

    /** null */
    @Excel(name = "null")
    private String tableidname;

    /** null */
    @Excel(name = "null")
    private String organiseid;

    /** null */
    @Excel(name = "null")
    private String userid;

    /** null */
    @Excel(name = "null")
    private String deptid;

    /** null */
    @Excel(name = "null", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ideatime;

    /** null */
    @Excel(name = "null")
    private String sequence;

    /** null */
    @Excel(name = "null")
    private String deptlevel;

    /** null */
    @Excel(name = "null")
    private String roleid;

    /** null */
    @Excel(name = "null")
    private String username;

    /** null */
    @Excel(name = "null")
    private String deptname;

    /** null */
    @Excel(name = "null")
    private String uniqueid;

    /** null */
    @Excel(name = "null")
    private String tempidea;

    /** null */
    @Excel(name = "null")
    private String isvisible;

    /** null */
    @Excel(name = "null")
    private String visiblescope;

    /** null */
    @Excel(name = "null")
    private String deptorder;

    /** null */
    @Excel(name = "null")
    private String wfisvisible;

    /** null */
    @Excel(name = "null")
    private String ishand;

    /** null */
    @Excel(name = "null")
    private String stattag;

    /** null */
    @Excel(name = "null")
    private String typistdeptid;

    /** null */
    @Excel(name = "null")
    private String typistuserid;

    /** null */
    @Excel(name = "null")
    private String sortname;

    /** null */
    @Excel(name = "null")
    private String subisvisible;

    /** null */
    @Excel(name = "null")
    private String iscollect;

    /** null */
    @Excel(name = "null")
    private String hqendisvisible;

    /** null */
    @Excel(name = "null")
    private String subhqisshow;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFiletypeid(String filetypeid) 
    {
        this.filetypeid = filetypeid;
    }

    public String getFiletypeid() 
    {
        return filetypeid;
    }
    public void setRecordid(String recordid) 
    {
        this.recordid = recordid;
    }

    public String getRecordid() 
    {
        return recordid;
    }
    public void setIdeafieldname(String ideafieldname) 
    {
        this.ideafieldname = ideafieldname;
    }

    public String getIdeafieldname() 
    {
        return ideafieldname;
    }
    public void setIdea(String idea) 
    {
        this.idea = idea;
    }

    public String getIdea() 
    {
        return idea;
    }
    public void setTablename(String tablename) 
    {
        this.tablename = tablename;
    }

    public String getTablename() 
    {
        return tablename;
    }
    public void setTableidname(String tableidname) 
    {
        this.tableidname = tableidname;
    }

    public String getTableidname() 
    {
        return tableidname;
    }
    public void setOrganiseid(String organiseid) 
    {
        this.organiseid = organiseid;
    }

    public String getOrganiseid() 
    {
        return organiseid;
    }
    public void setUserid(String userid) 
    {
        this.userid = userid;
    }

    public String getUserid() 
    {
        return userid;
    }
    public void setDeptid(String deptid) 
    {
        this.deptid = deptid;
    }

    public String getDeptid() 
    {
        return deptid;
    }
    public void setIdeatime(Date ideatime) 
    {
        this.ideatime = ideatime;
    }

    public Date getIdeatime() 
    {
        return ideatime;
    }
    public void setSequence(String sequence) 
    {
        this.sequence = sequence;
    }

    public String getSequence() 
    {
        return sequence;
    }
    public void setDeptlevel(String deptlevel) 
    {
        this.deptlevel = deptlevel;
    }

    public String getDeptlevel() 
    {
        return deptlevel;
    }
    public void setRoleid(String roleid) 
    {
        this.roleid = roleid;
    }

    public String getRoleid() 
    {
        return roleid;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setDeptname(String deptname) 
    {
        this.deptname = deptname;
    }

    public String getDeptname() 
    {
        return deptname;
    }
    public void setUniqueid(String uniqueid) 
    {
        this.uniqueid = uniqueid;
    }

    public String getUniqueid() 
    {
        return uniqueid;
    }
    public void setTempidea(String tempidea) 
    {
        this.tempidea = tempidea;
    }

    public String getTempidea() 
    {
        return tempidea;
    }
    public void setIsvisible(String isvisible) 
    {
        this.isvisible = isvisible;
    }

    public String getIsvisible() 
    {
        return isvisible;
    }
    public void setVisiblescope(String visiblescope) 
    {
        this.visiblescope = visiblescope;
    }

    public String getVisiblescope() 
    {
        return visiblescope;
    }
    public void setDeptorder(String deptorder) 
    {
        this.deptorder = deptorder;
    }

    public String getDeptorder() 
    {
        return deptorder;
    }
    public void setWfisvisible(String wfisvisible) 
    {
        this.wfisvisible = wfisvisible;
    }

    public String getWfisvisible() 
    {
        return wfisvisible;
    }
    public void setIshand(String ishand) 
    {
        this.ishand = ishand;
    }

    public String getIshand() 
    {
        return ishand;
    }
    public void setStattag(String stattag) 
    {
        this.stattag = stattag;
    }

    public String getStattag() 
    {
        return stattag;
    }
    public void setTypistdeptid(String typistdeptid) 
    {
        this.typistdeptid = typistdeptid;
    }

    public String getTypistdeptid() 
    {
        return typistdeptid;
    }
    public void setTypistuserid(String typistuserid) 
    {
        this.typistuserid = typistuserid;
    }

    public String getTypistuserid() 
    {
        return typistuserid;
    }
    public void setSortname(String sortname) 
    {
        this.sortname = sortname;
    }

    public String getSortname() 
    {
        return sortname;
    }
    public void setSubisvisible(String subisvisible) 
    {
        this.subisvisible = subisvisible;
    }

    public String getSubisvisible() 
    {
        return subisvisible;
    }
    public void setIscollect(String iscollect) 
    {
        this.iscollect = iscollect;
    }

    public String getIscollect() 
    {
        return iscollect;
    }
    public void setHqendisvisible(String hqendisvisible) 
    {
        this.hqendisvisible = hqendisvisible;
    }

    public String getHqendisvisible() 
    {
        return hqendisvisible;
    }
    public void setSubhqisshow(String subhqisshow) 
    {
        this.subhqisshow = subhqisshow;
    }

    public String getSubhqisshow() 
    {
        return subhqisshow;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("filetypeid", getFiletypeid())
            .append("recordid", getRecordid())
            .append("ideafieldname", getIdeafieldname())
            .append("idea", getIdea())
            .append("tablename", getTablename())
            .append("tableidname", getTableidname())
            .append("organiseid", getOrganiseid())
            .append("userid", getUserid())
            .append("deptid", getDeptid())
            .append("ideatime", getIdeatime())
            .append("sequence", getSequence())
            .append("deptlevel", getDeptlevel())
            .append("roleid", getRoleid())
            .append("username", getUsername())
            .append("deptname", getDeptname())
            .append("uniqueid", getUniqueid())
            .append("tempidea", getTempidea())
            .append("isvisible", getIsvisible())
            .append("visiblescope", getVisiblescope())
            .append("deptorder", getDeptorder())
            .append("wfisvisible", getWfisvisible())
            .append("ishand", getIshand())
            .append("stattag", getStattag())
            .append("typistdeptid", getTypistdeptid())
            .append("typistuserid", getTypistuserid())
            .append("sortname", getSortname())
            .append("subisvisible", getSubisvisible())
            .append("iscollect", getIscollect())
            .append("hqendisvisible", getHqendisvisible())
            .append("subhqisshow", getSubhqisshow())
            .toString();
    }
}
