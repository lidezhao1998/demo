package com.ruoyi.zaihai.caiji.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.caiji.domain.CMeteorological;
import com.ruoyi.zaihai.caiji.mapper.CMeteorologicalMapper;
import com.ruoyi.zaihai.caiji.service.ICMeteorologicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 气象管理信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-15
 */
@Service
public class CMeteorologicalServiceImpl implements ICMeteorologicalService
{
    @Autowired
    private CMeteorologicalMapper cMeteorologicalMapper;

    /**
     * 查询气象管理信息
     * 
     * @param id 气象管理信息ID
     * @return 气象管理信息
     */
    @Override
    public CMeteorological selectCMeteorologicalById(Long id)
    {
        return cMeteorologicalMapper.selectCMeteorologicalById(id);
    }

    /**
     * 查询气象管理信息列表
     * 
     * @param cMeteorological 气象管理信息
     * @return 气象管理信息
     */
    @Override
    public List<CMeteorological> selectCMeteorologicalList(CMeteorological cMeteorological)
    {
        return cMeteorologicalMapper.selectCMeteorologicalList(cMeteorological);
    }

    /**
     * 新增气象管理信息
     * 
     * @param cMeteorological 气象管理信息
     * @return 结果
     */
    @Override
    public int insertCMeteorological(CMeteorological cMeteorological)
    {
        cMeteorological.setCreateTime(DateUtils.getNowDate());
        return cMeteorologicalMapper.insertCMeteorological(cMeteorological);
    }

    /**
     * 修改气象管理信息
     * 
     * @param cMeteorological 气象管理信息
     * @return 结果
     */
    @Override
    public int updateCMeteorological(CMeteorological cMeteorological)
    {
        cMeteorological.setUpdateTime(DateUtils.getNowDate());
        return cMeteorologicalMapper.updateCMeteorological(cMeteorological);
    }

    /**
     * 删除气象管理信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCMeteorologicalByIds(String ids)
    {
        return cMeteorologicalMapper.deleteCMeteorologicalByIds(Convert.toStrArray(ids));
    }

    /**
     * 修改状态
     *
     * @param cMeteorological ID
     * @return 结果
     */
    @Override
    public int changeStatus(CMeteorological cMeteorological) {
        return cMeteorologicalMapper.updateCMeteorological(cMeteorological);
    }

    /**
     * 删除气象管理信息信息
     * 
     * @param id 气象管理信息ID
     * @return 结果
     */
    @Override
    public int deleteCMeteorologicalById(Long id)
    {
        return cMeteorologicalMapper.deleteCMeteorologicalById(id);
    }
}
