package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GradeSetting;
import java.util.List;

/**
 * 等级Mapper接口
 * 
 * @author hdp
 * @date 2020-10-12
 */
public interface GradeSettingMapper 
{
    /**
     * 查询等级
     * 
     * @param id 等级ID
     * @return 等级
     */
        GradeSetting selectGradeSettingById(Integer id);

    /**
     * 查询等级列表
     * 
     * @param gradeSetting 等级
     * @return 等级集合
     */
    List<GradeSetting> selectGradeSettingList(GradeSetting gradeSetting);

    /**
     * 新增等级
     * 
     * @param gradeSetting 等级
     * @return 结果
     */
    int insertGradeSetting(GradeSetting gradeSetting);

    /**
     * 修改等级
     * 
     * @param gradeSetting 等级
     * @return 结果
     */
    int updateGradeSetting(GradeSetting gradeSetting);

    /**
     * 删除等级
     * 
     * @param id 等级ID
     * @return 结果
     */
    int deleteGradeSettingById(Integer id);

    /**
     * 批量删除等级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteGradeSettingByIds(String[] ids);
}
