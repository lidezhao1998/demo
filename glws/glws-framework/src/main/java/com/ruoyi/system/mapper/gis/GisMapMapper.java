package com.ruoyi.system.mapper.gis;

import com.ruoyi.system.domain.gis.GisMap;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
public interface GisMapMapper {

    List<GisMap> GisMapByCerateBy(@Param("createBy") String createBy, @Param("gisYear") String year);

    int updataGisMap(GisMap gisMap);

    int insertGisMap(GisMap gisMap);

    int deleteGisMap(int  gisId);

    GisMap getGisMapById(int id);

    long getGisMapMaxId();

    List<HashMap<String,Object>> getGisMapYear();

    List<GisMap> getListById(String[] ids);

    String getArea(@Param("geo") String geo);
}
