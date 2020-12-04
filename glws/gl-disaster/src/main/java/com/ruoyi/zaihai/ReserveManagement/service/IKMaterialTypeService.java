package com.ruoyi.zaihai.ReserveManagement.service;


import com.ruoyi.zaihai.ReserveManagement.domain.KMaterialType;

import java.util.List;

/**
 * 物资类型Service接口
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
public interface IKMaterialTypeService 
{
    /**
     * 查询物资类型
     * 
     * @param id 物资类型ID
     * @return 物资类型
     */
        KMaterialType selectKMaterialTypeById(Long id);

    /**
     * 查询物资类型列表
     * 
     * @param kMaterialType 物资类型
     * @return 物资类型集合
     */
    List<KMaterialType> selectKMaterialTypeList(KMaterialType kMaterialType);

    /**
     * 新增物资类型
     * 
     * @param kMaterialType 物资类型
     * @return 结果
     */
    int insertKMaterialType(KMaterialType kMaterialType);

    /**
     * 修改物资类型
     * 
     * @param kMaterialType 物资类型
     * @return 结果
     */
    int updateKMaterialType(KMaterialType kMaterialType);

    /**
     * 批量删除物资类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteKMaterialTypeByIds(String ids);

    /**
     * 删除物资类型信息
     * 
     * @param id 物资类型ID
     * @return 结果
     */
    int deleteKMaterialTypeById(Long id);

    Object selectDictTypeById(Long dictId);

    List<KMaterialType> selectDictTypeAll();

    boolean checkCode(String code);
    /**
     * 根据物资类型信息查询
     *
     * @param type 物资类型type
     * @return 结果
     */
    KMaterialType selectKMaterialTypeByType(String type);
}
