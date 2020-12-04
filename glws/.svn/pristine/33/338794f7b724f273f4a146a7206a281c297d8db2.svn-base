package com.sinosoft.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.sinosoft.system.domain.CResource;
import com.sinosoft.system.mapper.CResourceMapper;
import com.sinosoft.system.service.ICResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 附件Service业务层处理
 * 
 * @author sudongdong
 * @date 2020-05-28
 */
@Service
public class CResourceServiceImpl implements ICResourceService
{
    @Autowired
    private CResourceMapper cResourceMapper;

    /**
     * 查询附件
     * 
     * @param id 附件ID
     * @return 附件
     */
    @Override
    public CResource selectCResourceById(Long id)
    {
        return cResourceMapper.selectCResourceById(id);
    }

    /**
     * 查询附件列表
     * 
     * @param cResource 附件
     * @return 附件
     */
    @Override
    public List<CResource> selectCResourceList(CResource cResource)
    {
        return cResourceMapper.selectCResourceList(cResource);
    }

    /**
     * 新增附件
     * 
     * @param cResource 附件
     * @return 结果
     */
    @Override
    public int insertCResource(CResource cResource)
    {
        cResource.setCreateTime(DateUtils.getNowDate());
        return cResourceMapper.insertCResource(cResource);
    }

    /**
     * 修改附件
     * 
     * @param cResource 附件
     * @return 结果
     */
    @Override
    public int updateCResource(CResource cResource)
    {
        return cResourceMapper.updateCResource(cResource);
    }

    /**
     * 删除附件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCResourceByIds(String ids)
    {
        return cResourceMapper.deleteCResourceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除附件信息
     * 
     * @param id 附件ID
     * @return 结果
     */
    @Override
    public int deleteCResourceById(Long id)
    {
        return cResourceMapper.deleteCResourceById(id);
    }
}
