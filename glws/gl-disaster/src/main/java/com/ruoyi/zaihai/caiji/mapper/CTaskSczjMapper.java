package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CTaskSczj;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 草原鼠害发生与防治情况Mapper接口
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
public interface CTaskSczjMapper 
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
     * 删除草原鼠害发生与防治情况
     * 
     * @param id 草原鼠害发生与防治情况ID
     * @return 结果
     */
    int deleteCTaskSczjById(Long id);

    /**
     * 批量删除草原鼠害发生与防治情况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCTaskSczjByIds(String[] ids);

    /**
    /**
     * 查询草原鼠害发生与防治情况父列表
     *
     * @param cTaskSczj 草原鼠害发生与防治情况
     * @return 草原鼠害发生与防治情况集合
     */
    List<CTaskSczj> selectCTaskSczjParentList(CTaskSczj cTaskSczj);

    /**
     * 查询草原鼠害发生与防治情况父列表
     *
     * @param cTaskSczj 草原鼠害发生与防治情况
     * @return 草原鼠害发生与防治情况
     */
    List<CTaskSczj> selectCTaskChfzList(CTaskSczj cTaskSczj);
    List<CTaskSczj> selectCTaskChfzList1(CTaskSczj cTaskSczj);
    List<CTaskSczj> selectCTaskChfzList2(CTaskSczj cTaskSczj);
    List<CTaskSczj> selectCTaskChfzList3(CTaskSczj cTaskSczj);

    /**
     * 修改草原鼠害发生与防治情况危害总面积
     *
     * @param
     * @return 结果 危害总面积map地图结果
     */
    @Select(" select  PROVINCE name,  sum(HARM_TOTALAREA) value from  c_task_sczj group by PROVINCE")
    List<Map<String, Object>> getRequestCount();


    /**
     * 修改草原鼠害发生与防治情况危害总面积
     *
     * @param
     * @return 结果危害总面积map地图结果
     */
    @Select(" select  PROVINCE name,  sum(ENGINEERINGHAZARD) value from  c_task_sczj group by PROVINCE")
    List<Map<String, Object>> getEngineeringhazardCount();

    /**
     * 修改草原鼠害发生与防治情况工程区严重危害面积
     *
     * @param
     * @return 结果 工程区严重危害面积map地图结果
     */
    @Select(" select  PROVINCE name,  sum(SERIOUS_AREA) value from  c_task_sczj group by PROVINCE")
    List<Map<String, Object>> getSeriousAreaCount();

    /**
     * 修改草原鼠害发生与防治情况工程区严重危害面积
     *
     * @param
     * @return 结果 工程区严重危害面积map地图结果
     */
    @Select(" select  PROVINCE name,  sum(ENGINEERING_SERIOUS_AREA) value from  c_task_sczj group by PROVINCE")
    List<Map<String, Object>> getEngineeringSeriousAreaCount();

    /**
     * 修改草原鼠害发生与防治情况当年防治总面积
     *
     * @param
     * @return 结果 当年防治总面积map地图结果
     */
    @Select(" select  PROVINCE name,  sum(DEFENSIVE_AREA) value from  c_task_sczj group by PROVINCE")
    List<Map<String, Object>> getDefensiveAreaCount();
    /**
     * 修改草原鼠害发生与防治情况持续控制总面积
     *
     * @param
     * @return 结果 持续控制总面积 map地图结果
     */
    @Select(" select  PROVINCE name,  sum(CONTROLAREA) value from  c_task_sczj group by PROVINCE")
    List<Map<String, Object>> getControlareaCount();
    /**
     * 修获取最大的主键id，获取最新的数据
     *
     * @param
     * @return 主键id结果
     */
    @Select(" select max(id) from  c_task_sczj ")
    long selectCTaskSczjMaxId(CTaskSczj cTaskSczj);

    /**
     * 省级查看市级列表
     *
     * @param
     * @return 市级信息
     */
    List<CTaskSczj> selectCTaskSczjCityList(CTaskSczj cTaskSczj);

    /**
     * 省级查看区县级列表
     * @param province
     * @return
     */
    List<CTaskSczj> selectCTaskSczjAreaListByProvince(String province);

    /**
     * 市级查看区县级列表
     * @param city
     * @return
     */
    List<CTaskSczj> selectCTaskSczjAreaListByCity(String city);

    /**
     * 市级查看县级列表
     *
     * @param
     * @return 县级信息
     */
    List<CTaskSczj> selectCTaskSczjAreaList(CTaskSczj cTaskSczj);

    List<CTaskSczj> selectCTaskChfzCountyList(CTaskSczj cTaskSczj);


    List<CTaskSczj> selectCTaskSczjProvinceList(CTaskSczj cTaskSczj);

    List<CTaskSczj> selectCTaskSczjcityIndexList(CTaskSczj cTaskSczj);


    List<CTaskSczj> selectCTaskSczjParentList1(CTaskSczj cTaskSczj);

    /**
     * 获取登录角色判断权限
     *
     * @param
     * @return 修改角色信息
     */
    @Update("update c_task_sczj set ROLENAME = #{roleName}  where ID = #{id}")
    void updateCTaskSczjRoleName(CTaskSczj taskSczj);

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
     * @return 首页鼠害饼状图
     */
    List<CTaskSczj> selectCTaskSczjSumList(CTaskSczj cTaskSczj);

    @Select(" select CITY name,  sum(HARM_TOTALAREA) value from  c_task_sczj group by CITY")
    List<Map<String, Object>> getCityCount();

    /**
     * 鼠害发生与防治情况危害总面积
     *
     * @param
     * @return 结果危害总面积map市级其中工程危害面积地图结果
     */
    @Select(" select  CITY name,  sum(ENGINEERINGHAZARD) value from  c_task_sczj group by CITY")
    List<Map<String, Object>> getEngineeringhazardCityCount();

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
     * 市级用户查看折线图
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
    @Select(" select  CITY name,  sum(SERIOUS_AREA) value from  c_task_sczj group by CITY")
    List<Map<String, Object>> getSeriousAreaCityCount();

    /**
     * @description 省级用户工程区危害面积
     * @author feiyanxu
     * @date
     */
    @Select(" select  CITY name,  sum(ENGINEERINGHAZARD) value from  c_task_sczj group by CITY")
    List<Map<String, Object>> getEngineeringSeriousAreaCityCount();

    /**
     * @description 省级用户当年防治面积
     * @author feiyanxu
     * @date
     */
    @Select(" select  CITY name,  sum(DEFENSIVE_AREA) value from  c_task_sczj group by CITY")
    List<Map<String, Object>> getDefensiveAreaCityCount();

    /**
     * @description 省级用户持续控制面积
     * @author feiyanxu
     * @date
     */
    @Select(" select  CITY name, sum(CONTROLAREA) value from  c_task_sczj group by CITY")
    List<Map<String, Object>> getControlareaCityCount();
}