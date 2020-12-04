package com.ruoyi.zaihai.ReserveManagement.mapper;


import com.ruoyi.zaihai.ReserveManagement.domain.KMaterialType;

import java.util.List;

/**
 * 物资类型Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
public interface KMaterialTypeMapper 
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
     * 删除物资类型
     * 
     * @param id 物资类型ID
     * @return 结果
     */
    int deleteKMaterialTypeById(Long id);

    /**
     * 批量删除物资类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteKMaterialTypeByIds(String[] ids);

    Object selectDictTypeById(Long dictId);

    List<KMaterialType> selectDictTypeAll();

    List<String> checkCode(String code);

    KMaterialType selectKMaterialTypeByCode(String manufactorType);

    KMaterialType selectKMaterialTypeByType(String type);
}
