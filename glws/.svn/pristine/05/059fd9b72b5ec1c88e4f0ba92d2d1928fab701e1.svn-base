package com.ruoyi.system.service.gis;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.gis.GisMap;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
public interface IGisMapService {

    List<Ztree> getListGisMap(Long userId, String year);

    int updataGisMap(GisMap gisMap);

    int insertGisMap(GisMap gisMap);

    int deleteGisMap(int gisId);


    GisMap getGisMapById(int id);

    long getGisMapMaxId();

    List<HashMap<String, Object>> getGisMapYear();

    List<GisMap> getListById(String shpIds);

    String getArea(@Param("geo") String geo);

    GisMap getTabById(int id);
}
