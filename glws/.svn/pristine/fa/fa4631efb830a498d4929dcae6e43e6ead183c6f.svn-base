package com.ruoyi.system.service;

import com.ruoyi.system.domain.FileResources;
import java.util.List;

/**
 * 文件资源Service接口
 * 
 * @author hdp
 * @date 2020-08-21
 */
public interface IFileResourcesService 
{
    /**
     * 查询文件资源
     * 
     * @param fileId 文件资源ID
     * @return 文件资源
     */
        FileResources selectFileResourcesById(Long fileId);

    /**
     * 查询文件资源列表
     * 
     * @param fileResources 文件资源
     * @return 文件资源集合
     */
    List<FileResources> selectFileResourcesList(FileResources fileResources);

    /**
     * 新增文件资源
     * 
     * @param fileResources 文件资源
     * @return 结果
     */
    int insertFileResources(FileResources fileResources);

    /**
     * 修改文件资源
     * 
     * @param fileResources 文件资源
     * @return 结果
     */
    int updateFileResources(FileResources fileResources);

    /**
     * 批量删除文件资源
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteFileResourcesByIds(String ids);

    /**
     * 删除文件资源信息
     * 
     * @param fileId 文件资源ID
     * @return 结果
     */
    int deleteFileResourcesById(Long fileId);

    /**
     * 批量审核导入文件
     * @param ids
     * @return
     */
    int updateFileResourcesByIds(String[] fileIds);
}
