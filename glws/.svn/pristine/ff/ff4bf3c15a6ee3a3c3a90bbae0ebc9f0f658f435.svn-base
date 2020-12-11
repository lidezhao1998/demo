package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CTaskFznb;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 发生防治年报Mapper接口
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
public interface CTaskFznbMapper 
{
    /**
     * 查询发生防治年报
     * 
     * @param id 发生防治年报ID
     * @return 发生防治年报
     */
        CTaskFznb selectCTaskFznbById(Long id);

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
     * 删除发生防治年报
     * 
     * @param id 发生防治年报ID
     * @return 结果
     */
    int deleteCTaskFznbById(Long id);

    /**
     * 批量删除发生防治年报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCTaskFznbByIds(String[] ids);

    /**
     * 获取登录角色判断权限
     *
     * @param
     * @return 修改角色信息
     */
    @Update("update c_task_fznb set ROLENAME = #{roleName}  where ID = #{id}")
    void updateCTaskSczjRoleName(CTaskFznb taskScny);

    /**
     * 查询发生防治年报列表
     *
     * @param cTaskFznb 发生防治年报
     * @return 发生防治年报集合
     */
    List<CTaskFznb> selectCTaskFznbParentList(CTaskFznb cTaskFznb);

    /**
     * 根据省查询区县详情
     * @param province
     * @return
     */
    List<CTaskFznb> selectCTaskFznbAreaListByProvince(String province);

    /**
     * 根据市查询区县详情
     * @param city
     * @return
     */
    List<CTaskFznb> selectCTaskFznbAreaListByCity(String city);

    /**
     * 市级用户
     *
     * @param cTaskFznb 发生防治年报
     * @return 发生防治年报集合
     */
    List<CTaskFznb> selectCTaskFznbCityList(CTaskFznb cTaskFznb);

    /**
     * 县级用户
     *
     * @param cTaskFznb 发生防治年报
     * @return 发生防治年报集合
     */
    List<CTaskFznb> selectCTaskFznbAreaList(CTaskFznb cTaskFznb);
    /**
     * 县级详情页信息
     *
     * @param cTaskFznb 发生防治年报
     * @return 发生防治年报集合
     */
    List<CTaskFznb> selectCTaskFznbCountyList(CTaskFznb cTaskFznb);
}
