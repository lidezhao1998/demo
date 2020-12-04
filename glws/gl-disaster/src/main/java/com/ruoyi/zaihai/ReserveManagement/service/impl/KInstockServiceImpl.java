package com.ruoyi.zaihai.ReserveManagement.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.ReserveManagement.domain.KInstock;
import com.ruoyi.zaihai.ReserveManagement.domain.KManufactor;
import com.ruoyi.zaihai.ReserveManagement.domain.KMaterialType;
import com.ruoyi.zaihai.ReserveManagement.mapper.KInstockMapper;
import com.ruoyi.zaihai.ReserveManagement.service.IKInstockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入库Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-16
 */
@Service
public class KInstockServiceImpl implements IKInstockService
{
    @Autowired
    private KInstockMapper kInstockMapper;

    /**
     * 查询入库
     * 
     * @param id 入库ID
     * @return 入库
     */
    @Override
    public KInstock selectKInstockById(Long id)
    {
        return kInstockMapper.selectKInstockById(id);
    }

    /**
     * 查询入库列表
     * 
     * @param kInstock 入库
     * @return 入库
     */
    @Override
    public List<KInstock> selectKInstockList(KInstock kInstock)
    {
        return kInstockMapper.selectKInstockList(kInstock);
    }

    /**
     * 新增入库
     * 
     * @param kInstock 入库
     * @return 结果
     */
    @Override
    public int insertKInstock(KInstock kInstock)
    {
        kInstock.setCreateTime(DateUtils.getNowDate());
        return kInstockMapper.insertKInstock(kInstock);
    }

    /**
     * 修改入库
     * 
     * @param kInstock 入库
     * @return 结果
     */
    @Override
    public int updateKInstock(KInstock kInstock)
    {
        kInstock.setUpdateTime(DateUtils.getNowDate());
        return kInstockMapper.updateKInstock(kInstock);
    }

    /**
     * 删除入库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKInstockByIds(String ids)
    {
        return kInstockMapper.deleteKInstockByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除入库信息
     * 
     * @param id 入库ID
     * @return 结果
     */
    @Override
    public int deleteKInstockById(Long id)
    {
        return kInstockMapper.deleteKInstockById(id);
    }


    /**
     * 确认入库信息
     *
     * @param instockId 入库ID
     * @return 结果
     */
    @Override
    public int updateInstock(long instockId) {
        return kInstockMapper.updateInstock(instockId);
    }

    /**
     * 查询出库
     *
     * @param
     * @return 出库
     */
    @Override
    public List<KInstock> selectKInstockListTwo(KInstock kInstock) {
        return kInstockMapper.selectKInstockListTwo(kInstock);
    }

    /**
     * 查询物资类型联动
     *
     * @param
     * @return 查询物资
     */
    @Override
    public List<KMaterialType> selectDictDataByType() {
        return kInstockMapper.selectDictDataByType();
    }


    /**
     * 查询物资名称
     *
     * @param
     * @return 查询物资
     */
    @Override
    public List<KManufactor> getGoods(String provinceCode) {
        return kInstockMapper.getGoods(provinceCode);
    }

    /**
     * 查询调拨单列表
     *
     * @param
     * @return 查询调拨单列表
     */
    @Override
    public List<KInstock> selectKInstockAllocationList(KInstock kInstock) {
        return kInstockMapper.selectKInstockAllocationList(kInstock);
    }
}