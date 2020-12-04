package com.ruoyi.system.mapper.gis;

import com.ruoyi.common.datasource.DataSource;
import com.ruoyi.system.domain.gis.GrasslandEcology;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/9/18
 */
@DataSource("gis")
public interface GresslandEcologyMapper {
    List<GrasslandEcology> getGressList(GrasslandEcology grasslandEcology);
    List<GrasslandEcology> getSection();
    List<GrasslandEcology> getGeneric(@Param("section") String section);
    List<GrasslandEcology> getBundle(@Param("generic") String generic);
    List<GrasslandEcology> getProvince();

    GrasslandEcology getGarssById(int id);
}
