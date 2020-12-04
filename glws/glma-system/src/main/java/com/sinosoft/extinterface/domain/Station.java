package com.sinosoft.extinterface.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.vividsolutions.jts.geom.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author  lhf
 * date  2020/8/27 15:42
 * version 1.0
 * 草原检测站
 */
public class Station  extends BaseEntity {

    private int id;
    private String name;
    private Point point;
    private String status;
    //地址标记颜色
    private String markColor;
    // 经度
    private double lon;
    // 纬度
    private double lat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMarkColor() {
        return markColor;
    }

    public void setMarkColor(String markColor) {
        this.markColor = markColor;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
