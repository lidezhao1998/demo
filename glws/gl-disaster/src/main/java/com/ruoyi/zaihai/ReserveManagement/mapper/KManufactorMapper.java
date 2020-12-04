package com.ruoyi.zaihai.ReserveManagement.mapper;


import com.ruoyi.zaihai.ReserveManagement.domain.KManufactor;

import java.util.List;

/**
 * 物资Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
public interface KManufactorMapper 
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
     * 删除物资
     * 
     * @param materialId 物资ID
     * @return 结果
     */
    int deleteKManufactorById(Long materialId);

    /**
     * 批量删除物资
     * 
     * @param materialIds 需要删除的数据ID
     * @return 结果
     */
    int deleteKManufactorByIds(String[] materialIds);

    /**
     * 查询物资名称
     *
     * @param
     * @return 查询物资
     */
    List<KManufactor> getGoods(String provinceCode);
}
