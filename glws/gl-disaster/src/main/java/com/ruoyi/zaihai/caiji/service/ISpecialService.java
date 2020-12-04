package com.ruoyi.zaihai.caiji.service;

import com.ruoyi.zaihai.caiji.domain.Special;

import java.util.List;

/**
 * 专题Service接口
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
public interface ISpecialService 
{
    /**
     * 查询专题
     * 
     * @param id 专题ID
     * @return 专题
     */
        Special selectSpecialById(Long id);

    /**
     * 查询专题列表
     * 
     * @param special 专题
     * @return 专题集合
     */
    List<Special> selectSpecialList(Special special);

    /**
     * 新增专题
     * 
     * @param special 专题
     * @return 结果
     */
    int insertSpecial(Special special);

    /**
     * 修改专题
     * 
     * @param special 专题
     * @return 结果
     */
    int updateSpecial(Special special);

    /**
     * 批量删除专题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSpecialByIds(String ids);

    /**
     * 删除专题信息
     * 
     * @param id 专题ID
     * @return 结果
     */
    int deleteSpecialById(Long id);
}
