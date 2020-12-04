package com.ruoyi.system.service.gis;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.datasource.DataSource;
import com.ruoyi.system.domain.gis.GrasslandEcology;

import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/9/18
 */

public interface PostGisService {
    List<Ztree> getGressTypeList();

    List<GrasslandEcology> getSection();

    List<GrasslandEcology> getGressList(GrasslandEcology grasslandEcology);

    List<GrasslandEcology> getGeneric(String sectionCode);

    List<GrasslandEcology> getBundle(String genericCode);

    List<GrasslandEcology> getProvince();

    GrasslandEcology getGarssById(int id);

    List<Ztree> getSystemGressTypeList();
}
