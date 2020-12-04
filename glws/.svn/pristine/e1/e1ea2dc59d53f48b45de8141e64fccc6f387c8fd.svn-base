package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FileResourcesMapper;
import com.ruoyi.system.domain.FileResources;
import com.ruoyi.system.service.IFileResourcesService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件资源Service业务层处理
 * 
 * @author hdp
 * @date 2020-08-21
 */
@Service
public class FileResourcesServiceImpl implements IFileResourcesService 
{
    @Autowired
    private FileResourcesMapper fileResourcesMapper;

    /**
     * 查询文件资源
     * 
     * @param fileId 文件资源ID
     * @return 文件资源
     */
    @Override
    public FileResources selectFileResourcesById(Long fileId)
    {
        return fileResourcesMapper.selectFileResourcesById(fileId);
    }

    /**
     * 查询文件资源列表
     * 
     * @param fileResources 文件资源
     * @return 文件资源
     */
    @Override
    public List<FileResources> selectFileResourcesList(FileResources fileResources)
    {
        return fileResourcesMapper.selectFileResourcesList(fileResources);
    }

    /**
     * 新增文件资源
     * 
     * @param fileResources 文件资源
     * @return 结果
     */
    @Override
    public int insertFileResources(FileResources fileResources)
    {
        return fileResourcesMapper.insertFileResources(fileResources);
    }

    /**
     * 修改文件资源
     * 
     * @param fileResources 文件资源
     * @return 结果
     */
    @Override
    public int updateFileResources(FileResources fileResources)
    {
        return fileResourcesMapper.updateFileResources(fileResources);
    }

    /**
     * 删除文件资源对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFileResourcesByIds(String ids)
    {
        return fileResourcesMapper.deleteFileResourcesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件资源信息
     * 
     * @param fileId 文件资源ID
     * @return 结果
     */
    @Override
    public int deleteFileResourcesById(Long fileId)
    {
        return fileResourcesMapper.deleteFileResourcesById(fileId);
    }

    /**
     * 批量审核通过导入文件
     * @param fileIds
     * @return
     */
    @Override
    public int updateFileResourcesByIds(String[] fileIds) {
        return fileResourcesMapper.updateFileResourcesByIds(fileIds);
    }


}
