package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CDataManagement;
import java.util.List;

/**
 * 防治组织信息管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-29
 */
public interface CDataManagementMapper 
{
    /**
     * 查询防治组织信息管理
     * 
     * @param id 防治组织信息管理ID
     * @return 防治组织信息管理
     */
        CDataManagement selectCDataManagementById(Long id);

    /**
     * 查询防治组织信息管理列表
     * 
     * @param cDataManagement 防治组织信息管理
     * @return 防治组织信息管理集合
     */
    List<CDataManagement> selectCDataManagementList(CDataManagement cDataManagement);

    /**
     * 新增防治组织信息管理
     * 
     * @param cDataManagement 防治组织信息管理
     * @return 结果
     */
    int insertCDataManagement(CDataManagement cDataManagement);

    /**
     * 修改防治组织信息管理
     * 
     * @param cDataManagement 防治组织信息管理
     * @return 结果
     */
    int updateCDataManagement(CDataManagement cDataManagement);

    /**
     * 删除防治组织信息管理
     * 
     * @param id 防治组织信息管理ID
     * @return 结果
     */
    int deleteCDataManagementById(Long id);

    /**
     * 批量删除防治组织信息管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCDataManagementByIds(String[] ids);
}
