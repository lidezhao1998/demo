package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CTaskChfz;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 草原虫害发生与防治情况Mapper接口
 * 
 * @author sudonngdong
 * @date 2020-05-26
 */
public interface CTaskChfzMapper 
{
    /**
     * 查询草原虫害发生与防治情况
     * 
     * @param id 草原虫害发生与防治情况ID
     * @return 草原虫害发生与防治情况
     */
        CTaskChfz selectCTaskChfzById(Long id);

    /**
     * 查询草原虫害发生与防治情况子列表
     * 
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 草原虫害发生与防治情况集合
     */
    List<CTaskChfz> selectCTaskChfzList(CTaskChfz cTaskChfz);

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
     * 删除草原虫害发生与防治情况
     * 
     * @param id 草原虫害发生与防治情况ID
     * @return 结果
     */
    int deleteCTaskChfzById(Long id);

    /**
     * 批量删除草原虫害发生与防治情况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCTaskChfzByIds(String[] ids);

    /**
     * 查询草原虫害发生与防治情况父列表
     *
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 草原虫害发生与防治情况集合
     */
    List<CTaskChfz> selectCTaskChfzParentList(CTaskChfz cTaskChfz);


    @Select("select PROVINCE,HARM_TOTALAREA from c_task_chfz")
    List<Map<String, Object>> selectCTaskChfzList2();

    /**
     * 草原虫害发生与防治情况
     *
     * @param
     * @return 危害总面积map地图结果
     */
    @Select(" select  PROVINCE name, sum(HARM_TOTALAREA) value from  c_task_chfz group by PROVINCE")
    List<Map<String, String>> getRequestCount();

    /**
     * 草原虫害发生与防治情况
     *
     * @param
     * @return 工程区危害面积map地图结果
     */
    @Select(" select  PROVINCE name, sum(ENGINEERINGHAZARD) value from  c_task_chfz group by PROVINCE")
    List<Map<String, String>> getEngineeringhazardCount();

    /**
     * 草原虫害发生与防治情况
     *
     * @param
     * @return 严重危害总面积map地图结果
     */
    @Select(" select  PROVINCE name, sum(SERIOUS_AREA) value from  c_task_chfz group by PROVINCE")
    List<Map<String, String>> getSeriousAreaCount();

    /**
     * 草原虫害发生与防治情况
     *
     * @param
     * @return 工程区严重危害面积map地图结果
     */
    @Select(" select  PROVINCE name, sum(ENGINEERING_SERIOUS_AREA) value from  c_task_chfz group by PROVINCE")
    List<Map<String, String>> getEngineeringSeriousAreaCount();

    /**
     * 草原虫害发生与防治情况市级列表
     *
     * @param
     *
     *
     * @return 市级统计信息
     */
    List<CTaskChfz> selectCTaskChfzCityList(CTaskChfz cTaskChfz);

    /**
     * 省级列表查询区县级详情
     * @param province
     * @return
     */
    List<CTaskChfz> selectCTaskChfzAreaListByProvince(String province);

    /**
     * 市级列表查询区县级详情
     * @param city
     * @return
     */
    List<CTaskChfz> selectCTaskChfzAreaListByCity(String city);

    /**
     * 草原虫害发生与防治情况县级列表
     *
     * @param
     * @return 县级统计信息
     */
    List<CTaskChfz> selectCTaskChfzCountyList(CTaskChfz cTaskChfz);

    /**
     * 获取登录角色判断权限
     *
     * @param
     * @return 修改角色信息
     */
    @Update("update c_task_chfz set ROLENAME = #{roleName}  where ID = #{id}")
    void updateCTaskChfzRoleName(CTaskChfz taskChfz);

    /**
     * 省级登录展示页面
     *
     * @param
     * @return 查看页面
     */
    List<CTaskChfz> selectCTaskChfzProvinceList(CTaskChfz cTaskChfz);

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
    @Select(" select max(id) from  c_task_chfz")
    long selectCTaskChfzMaxId(CTaskChfz cTaskChfz);

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页虫害折线图
     */
    List<CTaskChfz> selectCTaskChfzListLinePage(CTaskChfz cTaskChfz);


    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页虫害省级地图--危害总面积
     */
    @Select("select CITY name, sum(HARM_TOTALAREA) value from  c_task_chfz group by CITY")
    List<Map<String, Object>> getCityCount();

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页虫害省级地图--工程区危害面积
     */
    @Select(" select  CITY name, sum(ENGINEERINGHAZARD) value from  c_task_chfz group by CITY")
    List<Map<String, Object>> getEngineeringhazardCityCount();


    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页虫害省级地图--严重危害总面积
     */
    @Select("select CITY name, sum(SERIOUS_AREA) value from  c_task_chfz group by CITY")
    List<Map<String, Object>> getSeriousAreaCityCount();

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页虫害省级地图--工程区严重危害面积
     */
    @Select(" select  CITY name, sum(ENGINEERING_SERIOUS_AREA) value from  c_task_chfz group by CITY")
    List<Map<String, Object>> getEngineeringSeriousAreaCityCount();
}
