package com.ruoyi.zaihai.ReserveManagement.service;


import com.ruoyi.zaihai.ReserveManagement.domain.KOutbound;

import java.util.List;

/**
 * 出库Service接口
 * 
 * @author ruoyi
 * @date 2020-06-16
 */
public interface IKOutboundService 
{
    /**
     * 查询出库
     * 
     * @param id 出库ID
     * @return 出库
     */
        KOutbound selectKOutboundById(Long id);

    /**
     * 查询出库列表
     * 
     * @param kOutbound 出库
     * @return 出库集合
     */
    List<KOutbound> selectKOutboundList(KOutbound kOutbound);

    /**
     * 新增出库
     * 
     * @param kOutbound 出库
     * @return 结果
     */
    int insertKOutbound(KOutbound kOutbound);

    /**
     * 修改出库
     * 
     * @param kOutbound 出库
     * @return 结果
     */
    int updateKOutbound(KOutbound kOutbound);

    /**
     * 批量删除出库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteKOutboundByIds(String ids);

    /**
     * 删除出库信息
     *
     * @param id 出库ID
     * @return 结果
     */
    int deleteKOutboundById(Long id);

    /**
     * 确认出库信息
     *
     * @param outboundId 出库ID
     * @return 结果
     */
    int updateoutbound(long outboundId);

    List<KOutbound> selectKOutboundListTwo(KOutbound kOutbound);
}
