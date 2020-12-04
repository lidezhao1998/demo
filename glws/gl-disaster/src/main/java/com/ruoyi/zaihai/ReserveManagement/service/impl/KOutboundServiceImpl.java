package com.ruoyi.zaihai.ReserveManagement.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.ReserveManagement.domain.KOutbound;
import com.ruoyi.zaihai.ReserveManagement.mapper.KOutboundMapper;
import com.ruoyi.zaihai.ReserveManagement.service.IKOutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 出库Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-16
 */
@Service
public class KOutboundServiceImpl implements IKOutboundService
{
    @Autowired
    private KOutboundMapper kOutboundMapper;

    /**
     * 查询出库
     * 
     * @param id 出库ID
     * @return 出库
     */
    @Override
    public KOutbound selectKOutboundById(Long id)
    {
        return kOutboundMapper.selectKOutboundById(id);
    }

    /**
     * 查询出库列表
     * 
     * @param kOutbound 出库
     * @return 出库
     */
    @Override
    public List<KOutbound> selectKOutboundList(KOutbound kOutbound)
    {
        return kOutboundMapper.selectKOutboundList(kOutbound);
    }

    /**
     * 新增出库
     * 
     * @param kOutbound 出库
     * @return 结果
     */
    @Override
    public int insertKOutbound(KOutbound kOutbound)
    {
        kOutbound.setCreateTime(DateUtils.getNowDate());
        return kOutboundMapper.insertKOutbound(kOutbound);
    }

    /**
     * 修改出库
     * 
     * @param kOutbound 出库
     * @return 结果
     */
    @Override
    public int updateKOutbound(KOutbound kOutbound)
    {
        kOutbound.setUpdateTime(DateUtils.getNowDate());
        return kOutboundMapper.updateKOutbound(kOutbound);
    }

    /**
     * 删除出库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKOutboundByIds(String ids)
    {
        return kOutboundMapper.deleteKOutboundByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除出库信息
     * 
     * @param id 出库ID
     * @return 结果
     */
    @Override
    public int deleteKOutboundById(Long id)
    {
        return kOutboundMapper.deleteKOutboundById(id);
    }

    /**
     * 确认出库信息
     *
     * @param outboundId 出库ID
     * @return 结果
     */
    @Override
    public int updateoutbound(long outboundId) {
        return kOutboundMapper.updateoutbound(outboundId);
    }

    @Override
    public List<KOutbound> selectKOutboundListTwo(KOutbound kOutbound) {
        return kOutboundMapper.selectKOutboundListTwo(kOutbound);
    }
}
