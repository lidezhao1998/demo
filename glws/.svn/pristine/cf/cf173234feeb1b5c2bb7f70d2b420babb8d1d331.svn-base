package com.sinosoft.integration.mapper;

import com.ruoyi.common.datasource.DataSource;
import com.sinosoft.integration.domain.RemoteSensing;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/28 16:21
 */
@DataSource("gis")
public interface RemoteSensingMapper {

    RemoteSensing selectRemoteSensing(RemoteSensing remoteSensing);
    /**
     *功能描述 查询列表
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [remoteSensing]
     */
    List<RemoteSensing> selectRemoteSensingList(RemoteSensing remoteSensing);
    List<RemoteSensing> selectRemoteSensingListOrderBy(RemoteSensing remoteSensing);
    int updateRemoteSensing(RemoteSensing remoteSensing);
    int updateRemoteSensingByIds(String[] ids);
    /**
     *功能描述 查看
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    RemoteSensing selectRemoteSensingById(@Param("id")long id);
    /**
     *功能描述 审核通过 stage 改为1
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    int examineRemoteSensing(@Param("id") Long id);
    /**
     *功能描述 未审核
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    int unexamineRemoteSensing(@Param("id")Long id);
    /**
     *功能描述  逻辑删除
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    int defFalgRemoteSensing(@Param("id")Long id);
    /**
     * @Author sunlei
     * @Description: 保存修改
     * @Date 2020/9/20 18:47
     *   * @param remoteSensing
     * @return int
     **/

    int saveEditRemoteSensing(RemoteSensing remoteSensing);

    int insertShpFile(@Param("list") List<RemoteSensing> list);
}
