package com.ruoyi.zaihai.caiji.service;

import com.ruoyi.zaihai.caiji.domain.CTaskChfz;
import java.util.List;
import java.util.Map;

/**
 * 草原虫害发生与防治情况Service接口
 * 
 * @author sudonngdong
 * @date 2020-05-26
 */
public interface ICTaskChfzService 
{
    /**
     * 查询草原虫害发生与防治情况
     * 
     * @param id 草原虫害发生与防治情况ID
     * @return 草原虫害发生与防治情况
     */
        CTaskChfz selectCTaskChfzById(Long id);

        /**
     * 查询草原虫害发生与防治情况列子表
     *
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 草原虫害发生与防治情况集合
     */
    List<CTaskChfz> selectCTaskChfzList(CTaskChfz cTaskChfz);

    /**
     * 查询草原虫害发生与防治情况列表
     *
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 草原虫害发生与防治情况集合
     */
    List<CTaskChfz> selectCTaskChfzParentList(CTaskChfz cTaskChfz);

    /**
     * 新增草原虫害发生与防治情况
     * 
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 结果
     */
    int insertCTaskChfz(CTaskChfz cTaskChfz);

    /**
     * 修改草原虫害发生与防治情况
     * 
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 结果
     */
    int updateCTaskChfz(CTaskChfz cTaskChfz);

    /**
     * 批量删除草原虫害发生与防治情况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCTaskChfzByIds(String ids);

    /**
     * 删除草原虫害发生与防治情况信息
     * 
     * @param id 草原虫害发生与防治情况ID
     * @return 结果
     */
    int deleteCTaskChfzById(Long id);

    List<Map<String, Object>> selectCTaskChfzList2();

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return map地图
     */
    List<Map<String, String>> getRequestCount();

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 趋势预测
     */
    List<CTaskChfz> selectCTaskChfzAnalyze(CTaskChfz cTaskChfz);

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 折线图
     */
    List<CTaskChfz> selectCTaskChfzListLine(CTaskChfz cTaskChfz);

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 柱状图
     */
    List<CTaskChfz> selectCTaskChfzListColumnar(CTaskChfz cTaskChfz);

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 查询虫害表最大id
     */
    long selectCTaskChfzMaxId(CTaskChfz cTaskChfz);

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页鼠害折线图
     */
    List<CTaskChfz> selectCTaskChfzListLinePage(CTaskChfz cTaskChfz);

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页鼠害省级用户柱状图
     */
    List<CTaskChfz> selectCTaskChfzCityList(CTaskChfz cTaskChfz);

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页鼠害市级用户柱状图
     */
    List<CTaskChfz> selectCTaskChfzCountyList(CTaskChfz cTaskChfz);

    List<Map<String,Object>> getCityCount();

    List<Map<String,Object>> getEngineeringhazardCityCount();
}