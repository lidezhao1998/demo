package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SoilMapper;
import com.ruoyi.system.domain.Soil;
import com.ruoyi.system.service.ISoilService;
import com.ruoyi.common.core.text.Convert;

/**
 * 保育土壤Service业务层处理
 * 
 * @author hdp
 * @date 2020-06-22
 */
@Service
public class SoilServiceImpl implements ISoilService 
{
    @Autowired
    private SoilMapper soilMapper;

    /**
     * 查询保育土壤
     * 
     * @param soilId 保育土壤ID
     * @return 保育土壤
     */
    @Override
    public Soil selectSoilById(Long soilId)
    {
        return soilMapper.selectSoilById(soilId);
    }

    /**
     * 查询保育土壤列表
     * 
     * @param soil 保育土壤
     * @return 保育土壤
     */
    @Override
    public List<Soil> selectSoilList(Soil soil)
    {
        return soilMapper.selectSoilList(soil);
    }

    /**
     * 新增保育土壤
     * 
     * @param soil 保育土壤
     * @return 结果
     */
    @Override
    public int insertSoil(Soil soil)
    {
        return soilMapper.insertSoil(soil);
    }

    /**
     * 修改保育土壤
     * 
     * @param soil 保育土壤
     * @return 结果
     */
    @Override
    public int updateSoil(Soil soil)
    {
        return soilMapper.updateSoil(soil);
    }

    /**
     * 删除保育土壤对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSoilByIds(String ids)
    {
        return soilMapper.deleteSoilByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除保育土壤信息
     * 
     * @param soilId 保育土壤ID
     * @return 结果
     */
    @Override
    public int deleteSoilById(Long soilId)
    {
        return soilMapper.deleteSoilById(soilId);
    }
}
