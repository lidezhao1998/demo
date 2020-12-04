package com.ruoyi.zaihai.caiji.service;

import com.ruoyi.zaihai.caiji.domain.CTaskScny;
import java.util.List;

/**
 * 草原鼠虫害防治农药使用况Service接口
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
public interface ICTaskScnyService 
{
    /**
     * 查询草原鼠虫害防治农药使用况
     * 
     * @param id 草原鼠虫害防治农药使用况ID
     * @return 草原鼠虫害防治农药使用况
     */
        CTaskScny selectCTaskScnyById(Long id);

    /**
     * 查询草原鼠虫害防治农药使用况父列表
     *
     * @param cTaskScny 草原鼠虫害防治农药使用况
     * @return 草原鼠虫害防治农药使用况集合
     */
    List<CTaskScny> selectCTaskScnyParentList(CTaskScny cTaskScny);

    List<CTaskScny> selectCTaskScnyCityList(CTaskScny cTaskScny);

    List<CTaskScny> selectCTaskScnyAreaList(CTaskScny cTaskScny);
    /**
     * 查询草原鼠虫害防治农药使用况列表
     * 
     * @param cTaskScny 草原鼠虫害防治农药使用况
     * @return 草原鼠虫害防治农药使用况集合
     */
    List<CTaskScny> selectCTaskScnyList(CTaskScny cTaskScny);

    /**
     * 新增草原鼠虫害防治农药使用况
     * 
     * @param cTaskScny 草原鼠虫害防治农药使用况
     * @return 结果
     */
    int insertCTaskScny(CTaskScny cTaskScny);

    /**
     * 修改草原鼠虫害防治农药使用况
     * 
     * @param cTaskScny 草原鼠虫害防治农药使用况
     * @return 结果
     */
    int updateCTaskScny(CTaskScny cTaskScny);

    /**
     * 批量删除草原鼠虫害防治农药使用况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCTaskScnyByIds(String ids);

    /**
     * 删除草原鼠虫害防治农药使用况信息
     * 
     * @param id 草原鼠虫害防治农药使用况ID
     * @return 结果
     */
    int deleteCTaskScnyById(Long id);

    /**
     * 获取登录角色判断权限
     *
     * @param
     * @return 修改角色信息
     */
    void updateCTaskSczjRoleName(CTaskScny taskScny);


}
