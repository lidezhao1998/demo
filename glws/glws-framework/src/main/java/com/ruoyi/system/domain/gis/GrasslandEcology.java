package com.ruoyi.system.domain.gis;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/9/18
 */
public class GrasslandEcology extends BaseEntity {
    private int gid;
    private String item_code;
    private String item;
    private String section_code;
    private String section;
    private String generic_code;
    private String generic;
    private String bundle_code;
    private String bundle;
    private String status;
    private String color;
    private String province_name;
    private String area;
    private int id;
    private String centroid;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSection_code() {
        return section_code;
    }

    public void setSection_code(String section_code) {
        this.section_code = section_code;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGeneric_code() {
        return generic_code;
    }

    public void setGeneric_code(String generic_code) {
        this.generic_code = generic_code;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getBundle_code() {
        return bundle_code;
    }

    public void setBundle_code(String bundle_code) {
        this.bundle_code = bundle_code;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCentroid() {
        return centroid;
    }

    public void setCentroid(String centroid) {
        this.centroid = centroid;
    }

    public GrasslandEcology() {
    }

    public GrasslandEcology(int gid, String item_code, String item, String section_code, String section, String generic_code, String generic, String bundle_code, String bundle, String status, String color, String province_name, String area, int id, String centroid) {
        this.gid = gid;
        this.item_code = item_code;
        this.item = item;
        this.section_code = section_code;
        this.section = section;
        this.generic_code = generic_code;
        this.generic = generic;
        this.bundle_code = bundle_code;
        this.bundle = bundle;
        this.status = status;
        this.color = color;
        this.province_name = province_name;
        this.area = area;
        this.id = id;
        this.centroid = centroid;
    }
}
