package com.sinosoft.extinterface.service;

import com.sinosoft.extinterface.domain.LandSurvey;

import java.util.List;

/**
 * author  lhf
 * date  2020/8/19 17:55
 * version 1.0
 */
public interface LandSurveyService {
    LandSurvey selectLandSurvey(Long parentId);
    /**
     *功能描述 列表
     * @author sunlei
     * @date 2020/9/22
     * @param
     * @return [landSurvey]
     */
    List<LandSurvey> selectLandSurveyList(LandSurvey landSurvey);

    int insertShpFile(String filePath,String color);
    /**
     *功能描述 取消审核
     * @author sunlei
     * @date 2020/9/22
     * @param
     * @return [id]
     */
    int updateunexamine(Long id);
    /**
     *功能描述 审核通过
     * @author sunlei
     * @date 2020/9/22
     * @param
     * @return [id]
     */
    int examine(Long id);
    /**
     *功能描述 逻辑删除
     * @author sunlei
     * @date 2020/9/22
     * @param
     * @return [id]
     */
    int deleteShpFileById(Long id);

    int addFields(long id, String centroid);

    int updateLandSurvey(LandSurvey landSurvey);
}
