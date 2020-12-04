package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EvalTianbaoConstructionMapper;
import com.ruoyi.system.domain.EvalTianbaoConstruction;
import com.ruoyi.system.service.IEvalTianbaoConstructionService;
import com.ruoyi.common.core.text.Convert;

/**
 * 天然林资源保护工程建设情况统计Service业务层处理
 * 
 * @author hdp
 * @date 2020-06-20
 */
@Service
public class EvalTianbaoConstructionServiceImpl implements IEvalTianbaoConstructionService 
{
    @Autowired
    private EvalTianbaoConstructionMapper evalTianbaoConstructionMapper;

    /**
     * 查询天然林资源保护工程建设情况统计
     * 
     * @param id 天然林资源保护工程建设情况统计ID
     * @return 天然林资源保护工程建设情况统计
     */
    @Override
    public EvalTianbaoConstruction selectEvalTianbaoConstructionById(Long id)
    {
        return evalTianbaoConstructionMapper.selectEvalTianbaoConstructionById(id);
    }

    /**
     * 查询天然林资源保护工程建设情况统计列表
     * 
     * @param evalTianbaoConstruction 天然林资源保护工程建设情况统计
     * @return 天然林资源保护工程建设情况统计
     */
    @Override
    public List<EvalTianbaoConstruction> selectEvalTianbaoConstructionList(EvalTianbaoConstruction evalTianbaoConstruction)
    {
        return evalTianbaoConstructionMapper.selectEvalTianbaoConstructionList(evalTianbaoConstruction);
    }

    /**
     * 新增天然林资源保护工程建设情况统计
     * 
     * @param evalTianbaoConstruction 天然林资源保护工程建设情况统计
     * @return 结果
     */
    @Override
    public int insertEvalTianbaoConstruction(EvalTianbaoConstruction evalTianbaoConstruction)
    {
        return evalTianbaoConstructionMapper.insertEvalTianbaoConstruction(evalTianbaoConstruction);
    }

    /**
     * 修改天然林资源保护工程建设情况统计
     * 
     * @param evalTianbaoConstruction 天然林资源保护工程建设情况统计
     * @return 结果
     */
    @Override
    public int updateEvalTianbaoConstruction(EvalTianbaoConstruction evalTianbaoConstruction)
    {
        return evalTianbaoConstructionMapper.updateEvalTianbaoConstruction(evalTianbaoConstruction);
    }

    /**
     * 删除天然林资源保护工程建设情况统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEvalTianbaoConstructionByIds(String ids)
    {
        return evalTianbaoConstructionMapper.deleteEvalTianbaoConstructionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除天然林资源保护工程建设情况统计信息
     * 
     * @param id 天然林资源保护工程建设情况统计ID
     * @return 结果
     */
    @Override
    public int deleteEvalTianbaoConstructionById(Long id)
    {
        return evalTianbaoConstructionMapper.deleteEvalTianbaoConstructionById(id);
    }
}
