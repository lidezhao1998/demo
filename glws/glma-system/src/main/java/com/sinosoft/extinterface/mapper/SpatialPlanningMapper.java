package com.sinosoft.extinterface.mapper;

import com.ruoyi.common.datasource.DataSource;
import com.sinosoft.extinterface.domain.SpatialPlanning;
import com.sinosoft.integration.domain.RemoteSensing;
import com.sinosoft.integration.domain.SysShpFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sunlei
 * @description mapper类
 * @date 2020/09/02/11:26
 */
@DataSource("gis")
public interface SpatialPlanningMapper {
    /**
     *功能描述  查看列表
     * @author sunlei
     * @date 2020/9/3
     * @param
     * @return [spatialPlanning]
     */
    List<SpatialPlanning> spatialPlanningMapper(SpatialPlanning spatialPlanning);
    /**
     *功能描述 查看
     * @author sunlei
     * @date 2020/9/3
     * @param
     * @return [id]
     */
    SpatialPlanning selectShpFileById(@Param("id") long id);
    /**
     *功能描述 新增shp
     * @author sunlei
     * @date 2020/9/3
     * @param
     * @return [shpFile]
     */
    int insertShpFile(@Param("list") List<SpatialPlanning> list);
    /**
     * 校验shpFile名称是否唯一
     *
     * @param shpFileName shpFile名称
     * @param parentId 父shpFileID
     * @return 结果
     */
    SpatialPlanning checkShpFileNameUnique(@Param("name") String name,@Param("parentId") Long parentId);

    int deleteShpFileById(Long shpFileId);

    int selectShpFileCount(SpatialPlanning shpFile);

    int updateShpFile(SpatialPlanning shpFile);

    List<SpatialPlanning> selectChildrenShpFileById(Long shpFileId);

    void updateShpFileChildren(@Param("shpFiles") List<SpatialPlanning> shpFiles);

    void updateShpFileStatus(SpatialPlanning shpFile);

    Integer updateunexamine(@Param("id") Long id);

    Integer examine(Long id);


    /**
     *功能描述 审核通过 stage 改为1
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    int examineSpatialPlanning(@Param("id") Long id);
    /**
     *功能描述 未审核
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    int unexamineSpatialPlanning(@Param("id")Long id);
    /**
     *功能描述  逻辑删除
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    int defFalgSpatialPlanning(@Param("id")Long id);
    /**
     * @Author sunlei
     * @Description: 保存修改
     * @Date 2020/9/20 18:47
     *   * @param remoteSensing
     * @return int
     **/

    int updateEditSpatialPlanning(SpatialPlanning spatialPlanning);

    int updateaddFields(SpatialPlanning spatialPlanning);
}
