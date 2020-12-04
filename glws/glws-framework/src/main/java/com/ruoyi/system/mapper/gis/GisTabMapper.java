package com.ruoyi.system.mapper.gis;

import com.ruoyi.system.domain.gis.GisTab;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
public interface GisTabMapper {

    List<GisTab> GisTabByCerateBy(GisTab gisTab);

    int updataGisTab(GisTab gisTab);

    int insertGisTab(GisTab gisTab);

    int deleteGisTab(int gisId);

    GisTab getGisTabById(int id);

    long getGisTabMaxId();

    List<HashMap<String,Object>> getGisTabYear();

    List<GisTab> getListById(String[] ids);

    String getArea(@Param("geo") String geo);

}
