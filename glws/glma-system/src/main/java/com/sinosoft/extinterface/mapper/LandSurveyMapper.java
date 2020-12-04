package com.sinosoft.extinterface.mapper;

import com.ruoyi.common.datasource.DataSource;
import com.sinosoft.extinterface.domain.LandSurvey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * author  lhf
 * date  2020/8/19 17:50
 * version 1.0
 */
@DataSource("gis")
public interface LandSurveyMapper {
    LandSurvey selectLandSurvey(@Param("id") Long parentId);
    /**
     *功能描述 列表
     * @author sunlei
     * @date 2020/9/22
     * @param
     * @return [landSurvey]
     */
    List<LandSurvey> selectLandSurveyList(LandSurvey landSurvey);
    /**
     *功能描述 编辑
     * @author sunlei
     * @date 2020/9/22
     * @param
     * @return [id]
     */
    LandSurvey selectLandSurveyById(long id);

    int insertlandSurvey(@Param("list")List<LandSurvey> list);
    /**
     *功能描述 取消审核
     * @author sunlei
     * @date 2020/9/22
     * @param
     * @return [id]
     */
    int unExamineSpatialPlanning(@Param("id")Long id);
    /**
     *功能描述 审核通过
     * @author sunlei
     * @date 2020/9/22
     * @param
     * @return [id]
     */
    int examineSpatialPlanning(@Param("id")Long id);
    /**
     *功能描述 逻辑删除
     * @author sunlei
     * @date 2020/9/22
     * @param
     * @return [id]
     */
    int defFalgSpatialPlanning(@Param("id")Long id);

    int updateaddFields(LandSurvey landSurvey);

    int updateLandSurvey(LandSurvey landSurvey);

    String getProvincesCode(String provinces);

    String getCityCode(@Param("provincesCode") String provincesCode,@Param("cityCode")String cityCode);

}
