package com.ruoyi.zaihai.ReserveManagement.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.ReserveManagement.domain.KInstock;
import com.ruoyi.zaihai.ReserveManagement.domain.KStock;
import com.ruoyi.zaihai.ReserveManagement.mapper.KStockMapper;
import com.ruoyi.zaihai.ReserveManagement.service.IKStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-23
 */
@Service
public class KStockServiceImpl implements IKStockService
{
    @Autowired
    private KStockMapper kStockMapper;

    /**
     * 查询库存
     * 
     * @param id 库存ID
     * @return 库存
     */
    @Override
    public KStock selectKStockById(Long id)
    {
        return kStockMapper.selectKStockById(id);
    }

    /**
     * 查询库存列表
     * 
     * @param kStock 库存
     * @return 库存
     */
    @Override
    public List<KStock> selectKStockList(KStock kStock)
    {
        return kStockMapper.selectKStockList(kStock);
    }

    /**
     * 新增库存
     * 
     * @param kStock 库存
     * @return 结果
     */
    @Override
    public int insertKStock(KStock kStock)
    {
        kStock.setCreateTime(DateUtils.getNowDate());
        return kStockMapper.insertKStock(kStock);
    }

    /**
     * 修改库存
     * 
     * @param kStock 库存
     * @return 结果
     */
    @Override
    public int updateKStock(KStock kStock)
    {
        kStock.setUpdateTime(DateUtils.getNowDate());
        return kStockMapper.updateKStock(kStock);
    }

    /**
     * 删除库存对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKStockByIds(String ids)
    {
        return kStockMapper.deleteKStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存信息
     * 
     * @param id 库存ID
     * @return 结果
     */
    @Override
    public int deleteKStockById(Long id)
    {
        return kStockMapper.deleteKStockById(id);
    }


    /**
     * 入库信息添加进库存信息
     *
     * @param kInstock 入库信息
     * @return 结果
     */
    @Override
    public void insertKInstock(KInstock kInstock) {
        kStockMapper.insertKInstock(kInstock);
    }

    /**
     * 修改入库信息库存数量
     *
     * @param stock 入库信息
     * @return 结果
     */
    @Override
    public void updateKInstock(KStock stock) {
        kStockMapper.updateKInstock(stock);
    }

    /**
     * 回显入库信息库存数量
     *
     * @param  入库信息
     * @return 结果
     */
    @Override
    public KStock getNumber(String provinceCode) {
        return kStockMapper.getNumber(provinceCode);
    }

    /**
     * @description 获取 储备库统计折线图
     * @author feiyanxu
     * @date
     */
    @Override
    public List<KStock> getOption(KStock stock) {
        return kStockMapper.getOption(stock);
    }

    /**
     * @description 获取 储备库统计柱状图
     * @author feiyanxu
     * @date
     */
    @Override
    public List<KStock> getOptionHistogram(KStock stock) {
        return kStockMapper.getOptionHistogram(stock);
    }
}
