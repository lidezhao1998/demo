package com.ruoyi.system.service.gis;

import com.ruoyi.system.domain.gis.GisTab;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
public interface IGisTabService {

    List<GisTab> getListGisTab(Long userId, GisTab gisTab);

    int updataGisTab(GisTab gisTab);

    int insertGisTab(GisTab gisTab);

    int deleteGisTab(int gisId);


    GisTab getGisTabById(int id);

    long getGisTabMaxId();

    List<HashMap<String,Object>> getGisTabYear();

    List<GisTab> getListById(String shpIds);

    String getArea(@Param("geo") String geo);

    Map getGrassLandArea(String substring, String host) throws IOException, InterruptedException;

    GisTab getTabById(int id);

    List<GisTab> listLimit(int page,int limit,List<GisTab> list);


}
