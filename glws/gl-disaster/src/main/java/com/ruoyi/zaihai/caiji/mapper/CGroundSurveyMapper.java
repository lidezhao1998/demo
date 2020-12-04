package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CGroundSurvey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 地面调查数据Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public interface CGroundSurveyMapper 
{
    /**
     * 查询地面调查数据
     * 
     * @param groundId 地面调查数据ID
     * @return 地面调查数据
     */
        CGroundSurvey selectCGroundSurveyById(Long groundId);

    /**
     * 查询地面调查数据列表
     * 
     * @param cGroundSurvey 地面调查数据
     * @return 地面调查数据集合
     */
    List<CGroundSurvey> selectCGroundSurveyList(CGroundSurvey cGroundSurvey);


    List<CGroundSurvey> selectCGroundSurveyList1(CGroundSurvey cGroundSurvey);
    /**
     * 新增地面调查数据
     * 
     * @param cGroundSurvey 地面调查数据
     * @return 结果
     */
    int insertCGroundSurvey(CGroundSurvey cGroundSurvey);

    /**
     * 修改地面调查数据
     * 
     * @param cGroundSurvey 地面调查数据
     * @return 结果
     */
    int updateCGroundSurvey(CGroundSurvey cGroundSurvey);

    /**
     * 修改地面调查数据
     *
     * @param cGroundSurvey 地面调查数据
     * @return 结果
     */
    int updateCGroundSurveyStaute(CGroundSurvey cGroundSurvey);

    /**
     * 删除地面调查数据
     * 
     * @param groundId 地面调查数据ID
     * @return 结果
     */
    int deleteCGroundSurveyById(Long groundId);

    /**
     * 批量删除地面调查数据
     * 
     * @param groundIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCGroundSurveyByIds(String[] groundIds);


    int countCGroundSurvey(@Param("code") String code , @Param("data")Date data);
}
