package com.ruoyi.zaihai.ReserveManagement.mapper;


import com.ruoyi.zaihai.ReserveManagement.domain.KInstock;
import com.ruoyi.zaihai.ReserveManagement.domain.KStock;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 库存Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-23
 */
public interface KStockMapper 
{
    /**
     * 查询库存
     * 
     * @param id 库存ID
     * @return 库存
     */
        KStock selectKStockById(Long id);

    /**
     * 查询库存列表
     * 
     * @param kStock 库存
     * @return 库存集合
     */
    List<KStock> selectKStockList(KStock kStock);

    /**
     * 新增库存
     * 
     * @param kStock 库存
     * @return 结果
     */
    int insertKStock(KStock kStock);

    /**
     * 修改库存
     * 
     * @param kStock 库存
     * @return 结果
     */
    int updateKStock(KStock kStock);

    /**
     * 删除库存
     * 
     * @param id 库存ID
     * @return 结果
     */
    int deleteKStockById(Long id);

    /**
     * 批量删除库存
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteKStockByIds(String[] ids);

    /**
     * 入库信息添加进库存信息
     *
     * @param kInstock 入库信息
     * @return 结果
     */
    void insertKInstock(KInstock kInstock);

    /**
     * 修改入库信息库存数量
     *
     * @param stock 入库信息
     * @return 结果
     */
    void updateKInstock(KStock stock);


    /**
     * 回显入库信息库存数量
     *
     * @param  入库信息
     * @return 结果
     */
    KStock getNumber(String provinceCode);

    /**
     * @description 获取 储备库统计折线图
     * @author feiyanxu
     * @date
     */
    @Select("select INVENTORY,ADDRESS,NAME from k_stock group by name")
    List<KStock> getOption(KStock stock);

    /**
     * @description 获取 储备库统计柱状图
     * @author feiyanxu
     * @date
     */
    @Select("select INVENTORY,ADDRESS,NAME from k_stock")
    List<KStock> getOptionHistogram(KStock stock);

    @Select("select count(1) from k_stock where RESERVE_ID=#{dictId}")
    int countDictDataByType(Long dictId);
}
