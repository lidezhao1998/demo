package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GradeSettingMapper;
import com.ruoyi.system.domain.GradeSetting;
import com.ruoyi.system.service.IGradeSettingService;
import com.ruoyi.common.core.text.Convert;

/**
 * 等级Service业务层处理
 * 
 * @author hdp
 * @date 2020-10-12
 */
@Service
public class GradeSettingServiceImpl implements IGradeSettingService 
{
    @Autowired
    private GradeSettingMapper gradeSettingMapper;

    /**
     * 查询等级
     * 
     * @param id 等级ID
     * @return 等级
     */
    @Override
    public GradeSetting selectGradeSettingById(Integer id)
    {
        return gradeSettingMapper.selectGradeSettingById(id);
    }

    /**
     * 查询等级列表
     * 
     * @param gradeSetting 等级
     * @return 等级
     */
    @Override
    public List<GradeSetting> selectGradeSettingList(GradeSetting gradeSetting)
    {
        return gradeSettingMapper.selectGradeSettingList(gradeSetting);
    }

    /**
     * 新增等级
     * 
     * @param gradeSetting 等级
     * @return 结果
     */
    @Override
    public int insertGradeSetting(GradeSetting gradeSetting)
    {
        return gradeSettingMapper.insertGradeSetting(gradeSetting);
    }

    /**
     * 修改等级
     * 
     * @param gradeSetting 等级
     * @return 结果
     */
    @Override
    public int updateGradeSetting(GradeSetting gradeSetting)
    {
        return gradeSettingMapper.updateGradeSetting(gradeSetting);
    }

    /**
     * 删除等级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGradeSettingByIds(String ids)
    {
        return gradeSettingMapper.deleteGradeSettingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除等级信息
     * 
     * @param id 等级ID
     * @return 结果
     */
    @Override
    public int deleteGradeSettingById(Integer id)
    {
        return gradeSettingMapper.deleteGradeSettingById(id);
    }
}
