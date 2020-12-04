package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.*;

import java.util.List;

/**
 * 草原鼠害发生与防治情况Mapper接口
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
public interface CTaskSelectAllMapper {

    /**
     * 查询草原鼠害发生与防治情况
     *
     * @return 草原鼠害发生与防治情况
     */
    CTaskSelectAll selectCTaskSelectAllById(Long id);



    /**
     * 查询草原鼠害发生与防治情况列表
     *
     * @return 草原鼠害发生与防治情况集合
     */
    List<CTaskSelectAll> selectCTaskSelectAllList(CTaskSelectAll cTaskSelectAll);

    List<CTaskSelectAll> selectCTaskSelectAllList1(CTaskSelectAll cTaskSelectAll);

    /**
     * 新增草原鼠害发生与防治情况
     *
     * @param cTaskSelectAll
     * @return 结果
     */
    int insertCTaskSelectAll(CTaskSelectAll cTaskSelectAll);

    /**
     * 修改草原鼠害发生与防治情况
     *
     * @param cTaskSelectAll 草原鼠害发生与防治情况
     * @return 结果
     */
    int updateCTaskSelectAll(CTaskSelectAll cTaskSelectAll);

    /**
     * 修改草原虫害发生与防治情况
     *
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 结果
     */
    int updateCTaskChfz(CTaskChfz cTaskChfz);

    /**
     * 删除草原虫害发生与防治情况
     *
     * @param id 草原虫害发生与防治情况ID
     * @return 结果
     */
    int deleteCTaskChfzById(Long id);

    /**
     * 修改发生防治年报
     *
     * @param cTaskFznb 发生防治年报
     * @return 结果
     */
    int updateCTaskFznb(CTaskFznb cTaskFznb);

    /**
     * 删除发生防治年报
     *
     * @param id 发生防治年报ID
     * @return 结果
     */
    int deleteCTaskFznbById(Long id);

    /**
     * 修改草原鼠虫害防治农药使用况
     *
     * @param cTaskScny 草原鼠虫害防治农药使用况
     * @return 结果
     */
    int updateCTaskScny(CTaskScny cTaskScny);

    /**
     * 删除草原鼠虫害防治农药使用况
     *
     * @param id 草原鼠虫害防治农药使用况ID
     * @return 结果
     */
    int deleteCTaskScnyById(Long id);

    /**
     * 修改草原鼠害发生与防治情况
     *
     * @param cTaskSczj 草原鼠害发生与防治情况
     * @return 结果
     */
    int updateCTaskSczj(CTaskSczj cTaskSczj);

    /**
     * 删除草原鼠害发生与防治情况
     *
     * @param id 草原鼠害发生与防治情况ID
     * @return 结果
     */
    int deleteCTaskSczjById(Long id);

    /**
     * 删除草原鼠害发生与防治情况
     *
     * @return 结果
     */
    int deleteCTaskSelectAllById(Long id);

    /**
     * 批量删除草原鼠害发生与防治情况
     *
     * @return 结果
     */
    int deleteCTaskSelectAllByIds(String[] ids);
}


