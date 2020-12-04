package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 退牧还草工程进度上报对象 t_task_grass
 *
 * @author LiuHongfei
 * @date 2019-12-19
 */
public class TTaskGrass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 上报id */
    private Long reportId;

    /** 分解id */
    @Excel(name = "分解id")
    private Long resolveId;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 时间刻度 */
    @Excel(name = "时间刻度")
    private String timeScale;

    /** 2019年 */
    @Excel(name = "2019年")
    private String y2019;

    /** 2018年 */
    @Excel(name = "2018年")
    private String y2018;

    /** 2017年 */
    @Excel(name = "2017年")
    private String y2017;

    /** 2016年 */
    @Excel(name = "2016年")
    private String y2016;

    /** 2015年 */
    @Excel(name = "2015年")
    private String y2015;

    /** 2014年 */
    @Excel(name = "2014年")
    private String y2014;

    /** 2013年 */
    @Excel(name = "2013年")
    private String y2013;

    /** 2012年 */
    @Excel(name = "2012年")
    private String y2012;

    /** 2011年 */
    @Excel(name = "2011年")
    private String y2011;

    /** 2010年 */
    @Excel(name = "2010年")
    private String y2010;

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getReportId()
    {
        return reportId;
    }
    public void setResolveId(Long resolveId)
    {
        this.resolveId = resolveId;
    }

    public Long getResolveId()
    {
        return resolveId;
    }
    public void setYear(String year)
    {
        this.year = year;
    }

    public String getYear()
    {
        return year;
    }
    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getProvince()
    {
        return province;
    }
    public void setTimeScale(String timeScale)
    {
        this.timeScale = timeScale;
    }

    public String getTimeScale()
    {
        return timeScale;
    }
    public void setY2019(String y2019)
    {
        this.y2019 = y2019;
    }

    public String getY2019()
    {
        return y2019;
    }
    public void setY2018(String y2018)
    {
        this.y2018 = y2018;
    }

    public String getY2018()
    {
        return y2018;
    }
    public void setY2017(String y2017)
    {
        this.y2017 = y2017;
    }

    public String getY2017()
    {
        return y2017;
    }
    public void setY2016(String y2016)
    {
        this.y2016 = y2016;
    }

    public String getY2016()
    {
        return y2016;
    }
    public void setY2015(String y2015)
    {
        this.y2015 = y2015;
    }

    public String getY2015()
    {
        return y2015;
    }
    public void setY2014(String y2014)
    {
        this.y2014 = y2014;
    }

    public String getY2014()
    {
        return y2014;
    }
    public void setY2013(String y2013)
    {
        this.y2013 = y2013;
    }

    public String getY2013()
    {
        return y2013;
    }
    public void setY2012(String y2012)
    {
        this.y2012 = y2012;
    }

    public String getY2012()
    {
        return y2012;
    }
    public void setY2011(String y2011)
    {
        this.y2011 = y2011;
    }

    public String getY2011()
    {
        return y2011;
    }
    public void setY2010(String y2010)
    {
        this.y2010 = y2010;
    }

    public String getY2010()
    {
        return y2010;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("reportId", getReportId())
                .append("resolveId", getResolveId())
                .append("year", getYear())
                .append("province", getProvince())
                .append("timeScale", getTimeScale())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("y2019", getY2019())
                .append("y2018", getY2018())
                .append("y2017", getY2017())
                .append("y2016", getY2016())
                .append("y2015", getY2015())
                .append("y2014", getY2014())
                .append("y2013", getY2013())
                .append("y2012", getY2012())
                .append("y2011", getY2011())
                .append("y2010", getY2010())
                .toString();
    }
}
