package com.sinosoft.analyze.service;

import com.sinosoft.analyze.domain.SysGisMap;

import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
public interface ISysGisMapService {

    List<SysGisMap> getListGisMap(Long userId);

    int updataGisMap(SysGisMap gisMap);

    int insertGisMap(SysGisMap gisMap);

    int deleteGisMap(int gisId);


    SysGisMap getGisMapById(int id);

    long getGisMapMaxId();
}
