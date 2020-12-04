package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ModelManagementMapper;
import com.ruoyi.system.domain.ModelManagement;
import com.ruoyi.system.service.IModelManagementService;
import com.ruoyi.common.core.text.Convert;

/**
 * 水源涵养模型Service业务层处理
 * 
 * @author hdp
 * @date 2020-08-11
 */
@Service
public class ModelManagementServiceImpl implements IModelManagementService 
{
    @Autowired
    private ModelManagementMapper modelManagementMapper;

    /**
     * 查询水源涵养模型
     * 
     * @param modelId 水源涵养模型ID
     * @return 水源涵养模型
     */
    @Override
    public ModelManagement selectModelManagementById(Long modelId)
    {
        return modelManagementMapper.selectModelManagementById(modelId);
    }

    /**
     * 查询水源涵养模型列表
     * 
     * @param modelManagement 水源涵养模型
     * @return 水源涵养模型
     */
    @Override
    public List<ModelManagement> selectModelManagementList(ModelManagement modelManagement)
    {
        return modelManagementMapper.selectModelManagementList(modelManagement);
    }

    /**
     * 新增水源涵养模型
     * 
     * @param modelManagement 水源涵养模型
     * @return 结果
     */
    @Override
    public int insertModelManagement(ModelManagement modelManagement)
    {
        return modelManagementMapper.insertModelManagement(modelManagement);
    }

    /**
     * 修改水源涵养模型
     * 
     * @param modelManagement 水源涵养模型
     * @return 结果
     */
    @Override
    public int updateModelManagement(ModelManagement modelManagement)
    {
        return modelManagementMapper.updateModelManagement(modelManagement);
    }

    /**
     * 删除水源涵养模型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteModelManagementByIds(String ids)
    {
        return modelManagementMapper.deleteModelManagementByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除水源涵养模型信息
     * 
     * @param modelId 水源涵养模型ID
     * @return 结果
     */
    @Override
    public int deleteModelManagementById(Long modelId)
    {
        return modelManagementMapper.deleteModelManagementById(modelId);
    }
}
