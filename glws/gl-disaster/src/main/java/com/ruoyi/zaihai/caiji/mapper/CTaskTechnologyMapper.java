package com.ruoyi.zaihai.caiji.mapper;


import com.ruoyi.zaihai.caiji.domain.CTaskTechnology;

import java.util.List;

/**
 * 科技成果信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-17
 */
public interface CTaskTechnologyMapper 
{
    /**
     * 查询科技成果信息
     * 
     * @param id 科技成果信息ID
     * @return 科技成果信息
     */
        CTaskTechnology selectCTaskTechnologyById(Long id);

    /**
     * 查询科技成果信息列表
     * 
     * @param cTaskTechnology 科技成果信息
     * @return 科技成果信息集合
     */
    List<CTaskTechnology> selectCTaskTechnologyList(CTaskTechnology cTaskTechnology);

    /**
     * 新增科技成果信息
     * 
     * @param cTaskTechnology 科技成果信息
     * @return 结果
     */
    int insertCTaskTechnology(CTaskTechnology cTaskTechnology);

    /**
     * 修改科技成果信息
     * 
     * @param cTaskTechnology 科技成果信息
     * @return 结果
     */
    int updateCTaskTechnology(CTaskTechnology cTaskTechnology);

    /**
     * 删除科技成果信息
     * 
     * @param id 科技成果信息ID
     * @return 结果
     */
    int deleteCTaskTechnologyById(Long id);

    /**
     * 批量删除科技成果信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCTaskTechnologyByIds(String[] ids);


    int updateCTaskTechnologychehui(CTaskTechnology cTaskTechnology);

    List<CTaskTechnology> selectCTaskTechnologyList1(CTaskTechnology cTaskTechnology);
}
