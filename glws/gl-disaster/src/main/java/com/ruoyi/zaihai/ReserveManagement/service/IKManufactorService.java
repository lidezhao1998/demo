package com.ruoyi.zaihai.ReserveManagement.service;


import com.ruoyi.zaihai.ReserveManagement.domain.KManufactor;

import java.util.List;

/**
 * 物资Service接口
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
public interface IKManufactorService 
{
    /**
     * 查询物资
     * 
     * @param materialId 物资ID
     * @return 物资
     */
        KManufactor selectKManufactorById(Long materialId);

    /**
     * 查询物资列表
     * 
     * @param kManufactor 物资
     * @return 物资集合
     */
    List<KManufactor> selectKManufactorList(KManufactor kManufactor);

    /**
     * 新增物资
     * 
     * @param kManufactor 物资
     * @return 结果
     */
    int insertKManufactor(KManufactor kManufactor);

    /**
     * 修改物资
     * 
     * @param kManufactor 物资
     * @return 结果
     */
    int updateKManufactor(KManufactor kManufactor);

    /**
     * 批量删除物资
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteKManufactorByIds(String ids);

    /**
     * 删除物资信息
     * 
     * @param materialId 物资ID
     * @return 结果
     */
    int deleteKManufactorById(Long materialId);
}
