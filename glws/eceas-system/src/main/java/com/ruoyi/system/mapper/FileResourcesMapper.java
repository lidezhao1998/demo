package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.FileResources;
import java.util.List;

/**
 * 文件资源Mapper接口
 * 
 * @author hdp
 * @date 2020-08-21
 */
public interface FileResourcesMapper 
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
     * 删除文件资源
     * 
     * @param fileId 文件资源ID
     * @return 结果
     */
    int deleteFileResourcesById(Long fileId);

    /**
     * 批量删除文件资源
     * 
     * @param fileIds 需要删除的数据ID
     * @return 结果
     */
    int deleteFileResourcesByIds(String[] fileIds);

    /**
     * 批量审核通过导入文件
     * @param fileIds
     * @return
     */
    int updateFileResourcesByIds(String[] fileIds);
}
