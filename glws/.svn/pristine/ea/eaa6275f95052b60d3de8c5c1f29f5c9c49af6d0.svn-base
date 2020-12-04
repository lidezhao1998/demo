package com.sinosoft.extinterface.service.impl;

import java.util.List;

import com.sinosoft.extinterface.domain.APointInterface;
import com.sinosoft.extinterface.mapper.APointInterfaceMapper;
import com.sinosoft.extinterface.service.IAPointInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-03
 */
@Service
public class APointInterfaceServiceImpl implements IAPointInterfaceService
{
    @Autowired
    private APointInterfaceMapper aPointInterfaceMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public APointInterface selectAPointInterfaceById(Long id)
    {
        return aPointInterfaceMapper.selectAPointInterfaceById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param aPointInterface 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<APointInterface> selectAPointInterfaceList(APointInterface aPointInterface)
    {
        return aPointInterfaceMapper.selectAPointInterfaceList(aPointInterface);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param aPointInterface 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAPointInterface(APointInterface aPointInterface)
    {
        return aPointInterfaceMapper.insertAPointInterface(aPointInterface);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param aPointInterface 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAPointInterface(APointInterface aPointInterface)
    {
        int i = aPointInterfaceMapper.updateAPointInterface(aPointInterface);
        return i;
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAPointInterfaceByIds(String ids)
    {
        return aPointInterfaceMapper.deleteAPointInterfaceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteAPointInterfaceById(Long id)
    {
        return aPointInterfaceMapper.deleteAPointInterfaceById(id);
    }
}
