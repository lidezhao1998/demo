package com.ruoyi.system.domain.gis;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/11/5
 */
public class Region extends BaseEntity {
    private int id;
    private int gid;
    private String name;
    private String pac;
    private String userid;
    private String city;
    private String city_code;
    private String province;
    private String province_code;
    private String type;
    private String lon_lat;

    public Region() {
    }

    public Region(int id, int gid, String name, String pac, String userid, String city, String city_code, String province, String province_code, String type, String lon_lat) {
        this.id = id;
        this.gid = gid;
        this.name = name;
        this.pac = pac;
        this.userid = userid;
        this.city = city;
        this.city_code = city_code;
        this.province = province;
        this.province_code = province_code;
        this.type = type;
        this.lon_lat = lon_lat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLon_lat() {
        return lon_lat;
    }

    public void setLon_lat(String lon_lat) {
        this.lon_lat = lon_lat;
    }
}

