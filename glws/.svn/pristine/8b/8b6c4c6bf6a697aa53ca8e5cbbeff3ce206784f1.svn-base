package com.ruoyi.zaihai.caiji.service;

import com.ruoyi.zaihai.caiji.domain.CMeteorological;

import java.util.List;

/**
 * 气象管理信息Service接口
 * 
 * @author ruoyi
 * @date 2020-07-15
 */
public interface ICMeteorologicalService 
{
    /**
     * 查询气象管理信息
     * 
     * @param id 气象管理信息ID
     * @return 气象管理信息
     */
        CMeteorological selectCMeteorologicalById(Long id);

    /**
     * 查询气象管理信息列表
     * 
     * @param cMeteorological 气象管理信息
     * @return 气象管理信息集合
     */
    List<CMeteorological> selectCMeteorologicalList(CMeteorological cMeteorological);

    /**
     * 新增气象管理信息
     * 
     * @param cMeteorological 气象管理信息
     * @return 结果
     */
    int insertCMeteorological(CMeteorological cMeteorological);

    /**
     * 修改气象管理信息
     * 
     * @param cMeteorological 气象管理信息
     * @return 结果
     */
    int updateCMeteorological(CMeteorological cMeteorological);

    /**
     * 批量删除气象管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCMeteorologicalByIds(String ids);

    /**
     * 删除气象管理信息信息
     * 
     * @param id 气象管理信息ID
     * @return 结果
     */
    int deleteCMeteorologicalById(Long id);

    int changeStatus(CMeteorological cMeteorological);
}
