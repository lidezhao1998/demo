package com.sinosoft.integration.service;

import com.sinosoft.integration.domain.RemoteSensing;

import java.util.HashMap;
import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/28 16:22
 */
public interface RemoteSensingService {
    RemoteSensing selectRemoteSensing(RemoteSensing remoteSensing);
    List<RemoteSensing> selectRemoteSensingList(RemoteSensing remoteSensing);
    int updateRemoteSensing(RemoteSensing remoteSensing);
    int updateRemoteSensingByIds(String[] ids);

    List<HashMap> gisShow(String url);
    /**
     *功能描述 查看
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    RemoteSensing selectRemoteSensingById(Long id);
    /**
     *功能描述 审核通过
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    int examineRemoteSensing(Long id);
    /**
     *功能描述  未审核
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    int unexamineRemoteSensing(Long id);
    /**
     *功能描述 删除
     * @author sunlei
     * @date 2020/9/19
     * @param
     * @return [id]
     */
    int deleteRemoteSensing(Long id);
    /**
     * @Author sunlei
     * @Description: 修改保存
     * @Date 2020/9/20 18:46
     *   * @param remoteSensing
     * @return int
     **/

    int saveEditRemoteSensing(RemoteSensing remoteSensing);

    int insertShpFile(String filePath, String color);
}
