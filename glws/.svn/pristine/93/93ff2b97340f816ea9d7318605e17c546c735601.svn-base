package com.ruoyi.zaihai.caiji.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.caiji.domain.CDataManagement;
import com.ruoyi.zaihai.caiji.mapper.CDataManagementMapper;
import com.ruoyi.zaihai.caiji.service.ICDataManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * 防治组织信息管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-20
 */
@Service
public class CDataManagementServiceImpl implements ICDataManagementService
{
    @Autowired
    private CDataManagementMapper cDataManagementMapper;

    /**
     * 查询防治组织信息管理
     * 
     * @param id 防治组织信息管理ID
     * @return 防治组织信息管理
     */
    @Override
    public CDataManagement selectCDataManagementById(Long id)
    {
        return cDataManagementMapper.selectCDataManagementById(id);
    }

    /**
     * 查询防治组织信息管理列表
     * 
     * @param cDataManagement 防治组织信息管理
     * @return 防治组织信息管理
     */
    @Override
    public List<CDataManagement> selectCDataManagementList(CDataManagement cDataManagement)
    {
        return cDataManagementMapper.selectCDataManagementList(cDataManagement);
    }

    /**
     * 新增防治组织信息管理
     * 
     * @param cDataManagement 防治组织信息管理
     * @return 结果
     */
    @Override
    public int insertCDataManagement(CDataManagement cDataManagement)
    {
        cDataManagement.setCreateTime(DateUtils.getNowDate());
        return cDataManagementMapper.insertCDataManagement(cDataManagement);
    }

    /**
     * 修改防治组织信息管理
     * 
     * @param cDataManagement 防治组织信息管理
     * @return 结果
     */
    @Override
    public int updateCDataManagement(CDataManagement cDataManagement)
    {
        cDataManagement.setUpdateTime(DateUtils.getNowDate());
        return cDataManagementMapper.updateCDataManagement(cDataManagement);
    }

    /**
     * 删除防治组织信息管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCDataManagementByIds(String ids)
    {
        return cDataManagementMapper.deleteCDataManagementByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除防治组织信息管理信息
     * 
     * @param id 防治组织信息管理ID
     * @return 结果
     */
    @Override
    public int deleteCDataManagementById(Long id)
    {
        return cDataManagementMapper.deleteCDataManagementById(id);
    }
}
