package com.ruoyi.zaihai.ReserveManagement.service;


import com.ruoyi.zaihai.ReserveManagement.domain.KInstock;
import com.ruoyi.zaihai.ReserveManagement.domain.KManufactor;
import com.ruoyi.zaihai.ReserveManagement.domain.KMaterialType;

import java.util.List;

/**
 * 入库Service接口
 * 
 * @author ruoyi
 * @date 2020-06-16
 */
public interface IKInstockService 
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
     * 批量删除入库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteKInstockByIds(String ids);

    /**
     * 删除入库信息
     * 
     * @param id 入库ID
     * @return 结果
     */
    int deleteKInstockById(Long id);


    /**
     * 确认入库信息
     *
     * @param id 入库ID
     * @return 结果
     */
    int updateInstock(long instockId);

    /**
     * 查询出库
     *
     * @param
     * @return 出库
     */
    List<KInstock> selectKInstockListTwo(KInstock kInstock);

    /**
     * 查询物资类型联动
     *
     * @param
     * @return 查询物资
     */
    List<KMaterialType> selectDictDataByType();

    /**
     * 查询物资名称
     *
     * @param
     * @return 查询物资名称
     */
    List<KManufactor> getGoods(String provinceCode);

    /**
     * 查询调拨单列表
     *
     * @param
     * @return 查询调拨单列表
     */
    List<KInstock> selectKInstockAllocationList(KInstock kInstock);
}
