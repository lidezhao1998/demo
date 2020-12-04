package com.sinosoft.integration.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.vividsolutions.jts.geom.Geometry;
import java.math.BigDecimal;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/28 16:18
 */
public class RemoteSensing extends BaseEntity {
    private int id;
    private String name;
    private String year;
    private String imgUrl;
    private String size;
    private int status;

    private int queryStatus;

    private long itemCode;//目代码
    private String item;//目
    private long sectionCode;//科代码
    private String section;//科
    private long genericCode;//属代码
    private String generic;//属
    private long bundleCode;//丛代码
    private String bundle;//丛
    private String color;//颜色
    private BigDecimal area;//面积平方米
    private String centroid;//中心坐标
    private String geomData;//斑块坐标
    private String provinceName;//分布范围


    public int getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(int queryStatus) {
        this.queryStatus = queryStatus;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getItemCode() {
        return itemCode;
    }

    public void setItemCode(long itemCode) {
        this.itemCode = itemCode;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public long getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(long sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public long getGenericCode() {
        return genericCode;
    }

    public void setGenericCode(long genericCode) {
        this.genericCode = genericCode;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public long getBundleCode() {
        return bundleCode;
    }

    public void setBundleCode(long bundleCode) {
        this.bundleCode = bundleCode;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getCentroid() {
        return centroid;
    }

    public void setCentroid(String centroid) {
        this.centroid = centroid;
    }

    public String getGeomData() {
        return geomData;
    }

    public void setGeomData(String geomData) {
        this.geomData = geomData;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
