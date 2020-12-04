package com.ruoyi.zaihai.ReserveManagement.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.ReserveManagement.domain.KManufactor;
import com.ruoyi.zaihai.ReserveManagement.mapper.KManufactorMapper;
import com.ruoyi.zaihai.ReserveManagement.service.IKManufactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
@Service
public class KManufactorServiceImpl implements IKManufactorService
{
    @Autowired
    private KManufactorMapper kManufactorMapper;

    /**
     * 查询物资
     * 
     * @param materialId 物资ID
     * @return 物资
     */
    @Override
    public KManufactor selectKManufactorById(Long materialId)
    {
        return kManufactorMapper.selectKManufactorById(materialId);
    }

    /**
     * 查询物资列表
     * 
     * @param kManufactor 物资
     * @return 物资
     */
    @Override
    public List<KManufactor> selectKManufactorList(KManufactor kManufactor)
    {
        return kManufactorMapper.selectKManufactorList(kManufactor);
    }

    /**
     * 新增物资
     * 
     * @param kManufactor 物资
     * @return 结果
     */
    @Override
    public int insertKManufactor(KManufactor kManufactor)
    {
        kManufactor.setCreateTime(DateUtils.getNowDate());
        return kManufactorMapper.insertKManufactor(kManufactor);
    }

    /**
     * 修改物资
     * 
     * @param kManufactor 物资
     * @return 结果
     */
    @Override
    public int updateKManufactor(KManufactor kManufactor)
    {
        kManufactor.setUpdateTime(DateUtils.getNowDate());
        return kManufactorMapper.updateKManufactor(kManufactor);
    }

    /**
     * 删除物资对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKManufactorByIds(String ids)
    {
        return kManufactorMapper.deleteKManufactorByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物资信息
     * 
     * @param materialId 物资ID
     * @return 结果
     */
    @Override
    public int deleteKManufactorById(Long materialId)
    {
        return kManufactorMapper.deleteKManufactorById(materialId);
    }
}
