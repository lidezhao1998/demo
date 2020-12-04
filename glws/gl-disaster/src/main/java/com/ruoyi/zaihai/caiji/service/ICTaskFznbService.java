package com.ruoyi.zaihai.caiji.service;

import com.ruoyi.zaihai.caiji.domain.CTaskFznb;
import java.util.List;

/**
 * 发生防治年报Service接口
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
public interface ICTaskFznbService 
{
    /**
     * 查询发生防治年报
     * 
     * @param id 发生防治年报ID
     * @return 发生防治年报
     */
        CTaskFznb selectCTaskFznbById(Long id);

    /**
     * 查询草原鼠虫害防治农药使用况父列表
     *
     * @param cTaskFznb 草原鼠虫害防治农药使用况
     * @return 草原鼠虫害防治农药使用况集合
     */
    List<CTaskFznb> selectCTaskFznbParentList(CTaskFznb cTaskFznb);

    /**
     * 查询发生防治年报列表
     * 
     * @param cTaskFznb 发生防治年报
     * @return 发生防治年报集合
     */
    List<CTaskFznb> selectCTaskFznbList(CTaskFznb cTaskFznb);

    /**
     * 新增发生防治年报
     * 
     * @param cTaskFznb 发生防治年报
     * @return 结果
     */
    int insertCTaskFznb(CTaskFznb cTaskFznb);

    /**
     * 修改发生防治年报
     * 
     * @param cTaskFznb 发生防治年报
     * @return 结果
     */
    int updateCTaskFznb(CTaskFznb cTaskFznb);

    /**
     * 批量删除发生防治年报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCTaskFznbByIds(String ids);

    /**
     * 删除发生防治年报信息
     * 
     * @param id 发生防治年报ID
     * @return 结果
     */
    int deleteCTaskFznbById(Long id);

    List<CTaskFznb> selectCTaskFznbCityList(CTaskFznb cTaskFznb);
}
