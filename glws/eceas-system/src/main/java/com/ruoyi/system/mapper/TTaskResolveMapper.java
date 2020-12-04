package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TTaskPublish;
import com.ruoyi.system.domain.TTaskResolve;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 退牧还草工程任务领取分解Mapper接口
 * 
 * @author ruoyi
 * @date 2019-12-16
 */
public interface TTaskResolveMapper 
{
    /**
     * 查询退牧还草工程任务领取分解
     * 
     * @param resolveId 退牧还草工程任务领取分解ID
     * @return 退牧还草工程任务领取分解
     */
        TTaskResolve selectTTaskResolveById(Long resolveId);

    /**
     * 查询退牧还草工程任务领取分解列表
     * 
     * @param tTaskResolve 退牧还草工程任务领取分解
     * @return 退牧还草工程任务领取分解集合
     */
    List<TTaskResolve> selectTTaskResolveList(TTaskResolve tTaskResolve);

    /**
     * 新增退牧还草工程任务领取分解
     * 
     * @param tTaskResolve 退牧还草工程任务领取分解
     * @return 结果
     */
    int insertTTaskResolve(TTaskResolve tTaskResolve);

    /**
     * 修改退牧还草工程任务领取分解
     * 
     * @param tTaskResolve 退牧还草工程任务领取分解
     * @return 结果
     */
    int updateTTaskResolve(TTaskResolve tTaskResolve);

    /**
     * 删除退牧还草工程任务领取分解
     * 
     * @param resolveId 退牧还草工程任务领取分解ID
     * @return 结果
     */
    int deleteTTaskResolveById(Long resolveId);

    /**
     * 批量删除退牧还草工程任务领取分解
     * 
     * @param resolveIds 需要删除的数据ID
     * @return 结果
     */
    int deleteTTaskResolveByIds(String[] resolveIds);

    List<TTaskResolve> selectTTaskResolveByPublishId(TTaskPublish tTaskPublish);

    /**
     * 修改查看后的状态数据
     *
     * @param tTaskResolve 退牧还草工程任务领取分解ID
     * @return 结果
     */
    void updateTTaskResolveStatus(TTaskResolve tTaskResolve);

    @Insert("insert into t_task_resolve jmSize=#{jmSize0},jmMoney=#{jmMoney0},httSize=#{httSize0},httMoney={httMoney0}")
    void addSaveFenJie(Map<String, Object> map);

    //获取省份的dictcode
    @Select("Select dict_label a from sys_dict_data where  dict_code=(select dict_parent from sys_dict_data where dict_code=#{addrCity})")
    String selectDictValue(String addrCity);
    /**
     * 获取所有分解完成的所有任务任务
     *
     * @param publishId 根据发布总任务id查询出分解的id
     * @return 结果
     */
    List<TTaskResolve> selectTTaskResolve(Long publishId);
}
