package com.ruoyi.zaihai.ReserveManagement.service;


import com.ruoyi.zaihai.ReserveManagement.domain.Reserve;

import java.util.List;

/**
 * 储备库Service接口
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
public interface IReserveService 
{
    /**
     * 查询储备库
     * 
     * @param id 储备库ID
     * @return 储备库
     */
        Reserve selectReserveById(Long id);

    /**
     * 查询储备库列表
     * 
     * @param reserve 储备库
     * @return 储备库集合
     */
    List<Reserve> selectReserveList(Reserve reserve);

    /**
     * 新增储备库
     * 
     * @param reserve 储备库
     * @return 结果
     */
    int insertReserve(Reserve reserve);

    /**
     * 修改储备库
     * 
     * @param reserve 储备库
     * @return 结果
     */
    int updateReserve(Reserve reserve);

    /**
     * 批量删除储备库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteReserveByIds(String ids);

    /**
     * 删除储备库信息
     * 
     * @param id 储备库ID
     * @return 结果
     */
    int deleteReserveById(Long id);


    int changeStatus(Reserve reserve);

    Reserve selectReserveByLatitude(String splitCoord);

}
