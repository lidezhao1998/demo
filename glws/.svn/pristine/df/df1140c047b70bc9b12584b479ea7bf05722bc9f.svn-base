package com.sinosoft.analyze.mapper;

import com.sinosoft.analyze.domain.SysGisMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
public interface SysGisMapMapper {

    List<SysGisMap> GisMapByCerateBy(String createBy);

    int updataGisMap(SysGisMap gisMap);

    int insertGisMap(SysGisMap gisMap);

    int deleteGisMap(int gisId);

    SysGisMap getGisMapById(int id);

    /*获取最新天地图信息id*/
    @Select("select MAX(ID) from sys_gis_map")
    long getGisMapMaxId();
}
