package com.ruoyi.system.service.gis;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.gis.GisFeatures;

import java.util.List;

public interface IGisFeaturesService
{

    List<GisFeatures> selectGisFeaturesList(GisFeatures gisFeatures);


    List<Ztree> selectGisFeaturesTree(GisFeatures gisFeatures);

    List<Ztree> roleGisFeaturesTreeData(SysRole role);


    int selectGisFeaturesCount(Long parentId);


    int deleteGisFeaturesById(Long id);

    int insertGisFeatures(GisFeatures gisFeatures);

    int updateGisFeatures(GisFeatures gisFeatures);

    GisFeatures selectGisFeaturesById(Long id);

    String checkGisFeaturesNameUnique(GisFeatures gisFeatures);

    void updateStstus(Long id, int status);

    List<Ztree> getListByType();
}
