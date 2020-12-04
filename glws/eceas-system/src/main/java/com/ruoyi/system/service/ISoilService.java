package com.ruoyi.system.service;

import com.ruoyi.system.domain.Soil;
import java.util.List;

/**
 * 保育土壤Service接口
 * 
 * @author hdp
 * @date 2020-06-22
 */
public interface ISoilService 
{
    /**
     * 查询保育土壤
     * 
     * @param soilId 保育土壤ID
     * @return 保育土壤
     */
        Soil selectSoilById(Long soilId);

    /**
     * 查询保育土壤列表
     * 
     * @param soil 保育土壤
     * @return 保育土壤集合
     */
    List<Soil> selectSoilList(Soil soil);

    /**
     * 新增保育土壤
     * 
     * @param soil 保育土壤
     * @return 结果
     */
    int insertSoil(Soil soil);

    /**
     * 修改保育土壤
     * 
     * @param soil 保育土壤
     * @return 结果
     */
    int updateSoil(Soil soil);

    /**
     * 批量删除保育土壤
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSoilByIds(String ids);

    /**
     * 删除保育土壤信息
     * 
     * @param soilId 保育土壤ID
     * @return 结果
     */
    int deleteSoilById(Long soilId);
}
