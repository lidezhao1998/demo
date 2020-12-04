package com.ruoyi.system.mapper.gis;

import com.ruoyi.system.domain.gis.GisFeatures;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GisFeaturesMapper {

    int selectShpFileCount(GisFeatures gisFeatures);

    List<GisFeatures> selectShpFileList(GisFeatures gisFeatures);

    int deleteShpFileById(Long id);


    int insertShpFile(GisFeatures gisFeatures);

    int updateShpFile(GisFeatures gisFeatures);

    int updateShpFileChildren(@Param("shpFiles") List<GisFeatures> shpFiles);

    GisFeatures selectShpFileById(Long id);

    GisFeatures checkShpFileNameUnique(@Param("name") String name, @Param("parentId") Long parentId);

    List<String> selectRoleShpFileTree(Long roleId);

    void updateShpFileStatus(GisFeatures gisFeatures);

    List<GisFeatures> selectChildrenShpFileById(Long shpFileId);

    List<GisFeatures> getShpByInShpId(@Param("shpIds") String shpIds);

    void updateStstus(@Param("id") Long id, @Param("status") int status);

    List<GisFeatures> getListByType();
}
