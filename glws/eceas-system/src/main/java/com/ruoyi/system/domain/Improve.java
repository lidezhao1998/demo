package com.ruoyi.system.domain;

import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wangduo
 * @create 2020-01-13
 * @description 功能描述： 增加用来存储退牧还草工程任务领取分解表中数据：
 */
public class Improve extends TableDataInfo {

    //存储年份
    private List<Integer> yearCols;
    //存储省份
    private Set<String> provinceRows;
    private List<Map<String,String>> datas;

    public List<Integer> getYearCols() {
        return yearCols;
    }

    public void setYearCols(List<Integer> yearCols) {
        this.yearCols = yearCols;
    }

    public Set<String> getProvinceRows() {
        return provinceRows;
    }

    public void setProvinceRows(Set<String> provinceRows) {
        this.provinceRows = provinceRows;
    }

    public List<Map<String, String>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, String>> datas) {
        this.datas = datas;
    }
}
