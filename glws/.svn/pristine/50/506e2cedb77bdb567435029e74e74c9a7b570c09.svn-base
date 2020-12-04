package com.ruoyi.zaihai.caiji.service;

import com.ruoyi.zaihai.caiji.domain.CTaskSczj;
import java.util.List;
import java.util.Map;

/**
 * 草原鼠害发生与防治情况Service接口
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
public interface ICTaskSczjService 
{
    /**
     * 查询草原鼠害发生与防治情况
     * 
     * @param id 草原鼠害发生与防治情况ID
     * @return 草原鼠害发生与防治情况
     */
        CTaskSczj selectCTaskSczjById(Long id);

    /**
     * 查询草原鼠害发生与防治情况列表
     * 
     * @param cTaskSczj 草原鼠害发生与防治情况
     * @return 草原鼠害发生与防治情况集合
     */
    List<CTaskSczj> selectCTaskSczjList(CTaskSczj cTaskSczj);

    List<CTaskSczj> selectCTaskSczjList1(String code);


    List<CTaskSczj> selectCTaskSczjAreaList(CTaskSczj cTaskSczj);
    /**
     * 新增草原鼠害发生与防治情况
     * 
     * @param cTaskSczj 草原鼠害发生与防治情况
     * @return 结果
     */
    int insertCTaskSczj(CTaskSczj cTaskSczj);

    /**
     * 修改草原鼠害发生与防治情况
     * 
     * @param cTaskSczj 草原鼠害发生与防治情况
     * @return 结果
     */
    int updateCTaskSczj(CTaskSczj cTaskSczj);

    /**
     * 批量删除草原鼠害发生与防治情况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCTaskSczjByIds(String ids);

    /**
     * 删除草原鼠害发生与防治情况信息
     * 
     * @param id 草原鼠害发生与防治情况ID
     * @return 结果
     */
    int deleteCTaskSczjById(Long id);

    /**
     * 查询草原鼠害发生与防治情况父列表
     *
     * @param cTaskSczj 草原鼠害发生与防治情况
     * @return 草原鼠害发生与防治情况集合
     */
    List<CTaskSczj> selectCTaskSczjParentList(CTaskSczj cTaskSczj);

    /**
     * 查询草原鼠害发生与防治情况标头统计数量
     *
     * @param cTaskSczj 草原鼠害发生与防治情况标题统计数量
     * @return 草原鼠害发生与防治情况标题统计数量集合
     */
    List<CTaskSczj> selectCTaskChfzList(CTaskSczj cTaskSczj);

    List<CTaskSczj> selectCTaskChfzList1(CTaskSczj cTaskSczj);
    List<CTaskSczj> selectCTaskChfzList2(CTaskSczj cTaskSczj);
    List<CTaskSczj> selectCTaskChfzList3(CTaskSczj cTaskSczj);
    /**selectCTaskChfzList3
     * 修改草原鼠害发生与防治情况危害总面积
     *
     * @param  
     * @return 结果
     */
    List<Map<String,Object>> getRequestCount();

    /**
     * 修改草原鼠害发生与防治情况危害总面积
     *
     * @param
     * @return 结果
     */
    List<Map<String,Object>> getEngineeringhazardCount();

    /**
     * 修改草原鼠害发生与防治情严重危害面积
     *
     * @param
     * @return 结果
     */
    List<Map<String,Object>> getSeriousAreaCount();
    /**
     * 修改草原鼠害发生与防治情况工程区严重危害面积
     *
     * @param
     * @return 结果
     */
    List<Map<String,Object>> getEngineeringSeriousAreaCount();


    /**
     * 修改草原鼠害发生与防治情况当年防治总面积
     *
     * @param
     * @return 结果
     */
    List<Map<String,Object>> getDefensiveAreaCount();

    /**
     * 修改草原鼠害发生与防治情况持续控制总面积
     *
     * @param
     * @return 结果
     */
    List<Map<String,Object>> getControlareaCount();

    /**
     * 修获取最大的主键id，获取最新的数据
     *
     * @param
     * @return 主键id结果
     */
    long selectCTaskSczjMaxId(CTaskSczj cTaskSczj);

    /**
     * 查询草原鼠害发生与防治情况当年防治总面积
     *
     * @param
     * @return 鼠害趋势预测
     */
    List<CTaskSczj> selectCTaskSczjAnalyze(CTaskSczj cTaskSczj);

    /**
     * 查询草原鼠害发生与防治情况当年防治总面积
     *
     * @param
     * @return 鼠害趋势预警
     */
    List<CTaskSczj> selectCTaskSczjLine(CTaskSczj cTaskSczj);

    /**
     * 查询草原鼠害发生与防治情况当年防治总面积
     *
     * @param
     * @return 鼠害趋势预警柱状图
     */
    List<CTaskSczj> selectCTaskSczjCityList(CTaskSczj cTaskSczj);

    /**
     * 查询草原鼠害发生与防治情况当年防治总面积
     *
     * @param
     * @return 首页鼠害饼状图
     */
    List<CTaskSczj> selectCTaskSczjSumList(CTaskSczj cTaskSczj);

    List<Map<String,Object>> getCityCount( );

    List<Map<String,Object>> getEngineeringhazardCityCount();

    List<CTaskSczj> selectCTaskSczjParentList1(CTaskSczj cTaskSczj);

    /**
     * 上报草原鼠害发生与防治情况任务
     */
    int updateCTaskSczjStatus(long id);

    /**
     * @description 获取 地区上报统计首页鼠害折线图
     * @author feiyanxu
     * @date
     */
    List<CTaskSczj> selectCTaskSczjRatLine(CTaskSczj cTaskSczj);

    /**
     * @description 获取 地区上报统计首页省级鼠害折线图
     * @author feiyanxu
     * @date
     */
    List<CTaskSczj> selectCTaskSczjRatLineProvince(CTaskSczj cTaskSczj);

    /**
     *  市级用户查看折线图
     * @param cTaskSczj
     * @return
     */
    List<CTaskSczj> selectCTaskSczjRatLineCounty(CTaskSczj cTaskSczj);

    /**
     * @description 获取 地区上报统计首页省级鼠害柱状图
     * @author feiyanxu
     * @date
     */
    List<CTaskSczj> selectCTaskSczjRatcity(CTaskSczj cTaskSczj);

    /**
     * @description 获取 地区上报统计首页市级鼠害柱状图
     * @author feiyanxu
     * @date
     */
    List<CTaskSczj> selectCTaskSczjRatcounty(CTaskSczj cTaskSczj);

    /**
     * @description 数据统计 省级用户上报统计市级鼠害折线图
     * @author feiyanxu
     * @date
     */
    List<CTaskSczj> selectCTaskSczjRatProvince(CTaskSczj cTaskSczj);


    /**
     * @description 省级用户严重危害总面积
     * @author feiyanxu
     * @date
     */
    List<Map<String,Object>> getSeriousAreaCityCount();

    /**
     * @description 省级用户工程区危害面积
     * @author feiyanxu
     * @date
     */
    List<Map<String,Object>> getEngineeringSeriousAreaCityCount();
    /**
     * @description 省级用户当年防治面积
     * @author feiyanxu
     * @date
     */
    List<Map<String,Object>> getDefensiveAreaCityCount();
    /**
     * @description 省级用户持续控制面积
     * @author feiyanxu
     * @date
     */
    List<Map<String,Object>> getControlareaCityCount();
}
