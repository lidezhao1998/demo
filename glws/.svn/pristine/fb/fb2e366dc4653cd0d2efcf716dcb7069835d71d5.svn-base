package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CTaskScny;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 草原鼠虫害防治农药使用况Mapper接口
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
public interface CTaskScnyMapper 
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

    /**
     * 查询草原鼠虫害防治农药使用况列表
     * 
     * @param cTaskScny 草原鼠虫害防治农药使用况
     * @return 草原鼠虫害防治农药使用况集合
     */
    List<CTaskScny> selectCTaskScnyList(CTaskScny cTaskScny);

    /**
     * 查询省级列表草原鼠虫害防治农药详情
     * @param province
     * @return
     */
    List<CTaskScny> selectCTaskScnyAreaListByProvince(String province);

    /**
     * 查询市级列表草原鼠虫害防治农药详情
     * @param city
     * @return
     */
    List<CTaskScny> selectCTaskScnyAreaListByCity(String city);

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
     * 删除草原鼠虫害防治农药使用况
     * 
     * @param id 草原鼠虫害防治农药使用况ID
     * @return 结果
     */
    int deleteCTaskScnyById(Long id);

    /**
     * 批量删除草原鼠虫害防治农药使用况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCTaskScnyByIds(String[] ids);


    /**
     * 市级用户登录展示页面
     *
     * @param
     * @return 展示页面
     */
    List<CTaskScny> selectCTaskScnyCityList(CTaskScny cTaskScny);
    /**
     * 县级用户登录展示页面
     *
     * @param
     * @return 展示页面
     */
    List<CTaskScny> selectCTaskScnyAreaList(CTaskScny cTaskScny);

    /**
     * 获取登录角色判断权限
     *
     * @param
     * @return 修改角色信息
     */
    void updateCTaskSczjRoleName( CTaskScny taskScny);

    /**
     * 县级详情信息列表
     *
     * @param
     * @return 县级详情信息
     */
    List<CTaskScny> selectCTaskScnyCountyList(CTaskScny cTaskScny);
}
