package com.ruoyi.zaihai.ReserveManagement.mapper;


import com.ruoyi.zaihai.ReserveManagement.domain.KInstock;
import com.ruoyi.zaihai.ReserveManagement.domain.KManufactor;
import com.ruoyi.zaihai.ReserveManagement.domain.KMaterialType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 入库Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-16
 */
public interface KInstockMapper 
{
    /**
     * 查询入库
     * 
     * @param id 入库ID
     * @return 入库
     */
        KInstock selectKInstockById(Long id);

    /**
     * 查询入库列表
     * 
     * @param kInstock 入库
     * @return 入库集合
     */
    List<KInstock> selectKInstockList(KInstock kInstock);

    /**
     * 新增入库
     * 
     * @param kInstock 入库
     * @return 结果
     */
    int insertKInstock(KInstock kInstock);

    /**
     * 修改入库
     * 
     * @param kInstock 入库
     * @return 结果
     */
    int updateKInstock(KInstock kInstock);

    /**
     * 删除入库
     * 
     * @param id 入库ID
     * @return 结果
     */
    int deleteKInstockById(Long id);

    /**
     * 批量删除入库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteKInstockByIds(String[] ids);

    /**
     * 确认入库信息
     *
     * @param instockId  入库id
     * @return 结果
     */
    int updateInstock(long instockId);

    /**
     * 查询出库列表
     *
     * @param kInstock 出库
     * @return 出库集合
     */
    List<KInstock> selectKInstockListTwo(KInstock kInstock);

    /**
     * 确认出库信息
     *
     * @param instockId  入库id
     * @return 结果
     */
    int updateOutbound(long instockId);

    /**
     * 查出所有物资入库数量合计
     *
     * @param kInstock1  入库id
     * @return 结果
     */
    List<KInstock> selectKInstockListCount(KInstock kInstock1);

    /**
     * 查询物资类型联动
     *
     * @param
     * @return 查询物资
     */
    @Select("select MATERIAL_NAME from  k_material_type")
    List<KMaterialType> selectDictDataByType();

    /**
     * 查询物资名称
     *
     * @param
     * @return 查询物资
     */
    @Select("select MATERIAL_NAME from  k_manufactor where MATERIAL_TYPE=#{provinceCode}")
    List<KManufactor> getGoods(@Param("provinceCode") String provinceCode);

    /**
     * 查询调拨单列表
     *
     * @param
     * @return 查询调拨单列表
     */
    List<KInstock> selectKInstockAllocationList(KInstock kInstock);
}
