package com.ruoyi.zaihai.ReserveManagement.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.ReserveManagement.domain.KAllocation;
import com.ruoyi.zaihai.ReserveManagement.mapper.KAllocationMapper;
import com.ruoyi.zaihai.ReserveManagement.service.IKAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调拨单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-30
 */
@Service
public class KAllocationServiceImpl implements IKAllocationService
{
    @Autowired
    private KAllocationMapper kAllocationMapper;

    /**
     * 查询调拨单
     * 
     * @param id 调拨单ID
     * @return 调拨单
     */
    @Override
    public KAllocation selectKAllocationById(Long id)
    {
        return kAllocationMapper.selectKAllocationById(id);
    }

    /**
     * 查询调拨单列表
     * 
     * @param kAllocation 调拨单
     * @return 调拨单
     */
    @Override
    public List<KAllocation> selectKAllocationList(KAllocation kAllocation)
    {
        return kAllocationMapper.selectKAllocationList(kAllocation);
    }

    /**
     * 新增调拨单
     * 
     * @param kAllocation 调拨单
     * @return 结果
     */
    @Override
    public int insertKAllocation(KAllocation kAllocation)
    {
        kAllocation.setCreateTime(DateUtils.getNowDate());
        return kAllocationMapper.insertKAllocation(kAllocation);
    }

    /**
     * 修改调拨单
     * 
     * @param kAllocation 调拨单
     * @return 结果
     */
    @Override
    public int updateKAllocation(KAllocation kAllocation)
    {
        kAllocation.setUpdateTime(DateUtils.getNowDate());
        return kAllocationMapper.updateKAllocation(kAllocation);
    }

    /**
     * 删除调拨单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKAllocationByIds(String ids)
    {
        return kAllocationMapper.deleteKAllocationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除调拨单信息
     * 
     * @param id 调拨单ID
     * @return 结果
     */
    @Override
    public int deleteKAllocationById(Long id)
    {
        return kAllocationMapper.deleteKAllocationById(id);
    }

    /**
     * 上报调拨单信息
     *
     * @param id 调拨单ID
     * @return 结果
     */
    @Override
    public int updateAllocation(long id) {
        return kAllocationMapper.updateAllocation(id);
    }

    /**
     * 查询调拨单
     *
     * @param kAllocation 调拨单
     * @return 调拨单
     */
    @Override
    public List<KAllocation> selectKAllocationShList(KAllocation kAllocation) {
        return kAllocationMapper.selectKAllocationShList(kAllocation);
    }
}
