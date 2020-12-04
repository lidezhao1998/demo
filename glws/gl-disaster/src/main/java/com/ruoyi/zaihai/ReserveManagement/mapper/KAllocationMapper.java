package com.ruoyi.zaihai.ReserveManagement.mapper;


import com.ruoyi.zaihai.ReserveManagement.domain.KAllocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 调拨单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-30
 */
public interface KAllocationMapper 
{
    /**
     * 查询调拨单
     * 
     * @param id 调拨单ID
     * @return 调拨单
     */
        KAllocation selectKAllocationById(Long id);

    /**
     * 查询调拨单列表
     * 
     * @param kAllocation 调拨单
     * @return 调拨单集合
     */
    List<KAllocation> selectKAllocationList(KAllocation kAllocation);

    /**
     * 新增调拨单
     * 
     * @param kAllocation 调拨单
     * @return 结果
     */
    int insertKAllocation(KAllocation kAllocation);

    /**
     * 修改调拨单
     * 
     * @param kAllocation 调拨单
     * @return 结果
     */
    int updateKAllocation(KAllocation kAllocation);

    /**
     * 删除调拨单
     * 
     * @param id 调拨单ID
     * @return 结果
     */
    int deleteKAllocationById(Long id);

    /**
     * 批量删除调拨单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteKAllocationByIds(String[] ids);

    /**
     * 上报调拨单信息
     *
     * @param id 调拨单ID
     * @return 结果
     */
    int updateAllocation(long id);


    /**
     * 驳回调拨单信息
     *
     * @param id 调拨单ID
     * @return 结果
     */
    int updateBhAllocation(long id);

    /**
     * 上报调拨单信息
     *
     * @param kAllocation 调拨单ID
     * @return 结果
     */
    int updateShAllocation(KAllocation kAllocation);

    /**
     * 查询调拨单
     *
     * @param kAllocation 调拨单
     * @return 调拨单
     */
    List<KAllocation> selectKAllocationShList(KAllocation kAllocation);
}
