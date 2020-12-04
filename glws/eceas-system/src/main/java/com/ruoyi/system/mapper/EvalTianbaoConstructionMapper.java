package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.EvalTianbaoConstruction;
import java.util.List;

/**
 * 天然林资源保护工程建设情况统计Mapper接口
 * 
 * @author hdp
 * @date 2020-06-20
 */
public interface EvalTianbaoConstructionMapper 
{
    /**
     * 查询天然林资源保护工程建设情况统计
     * 
     * @param id 天然林资源保护工程建设情况统计ID
     * @return 天然林资源保护工程建设情况统计
     */
        EvalTianbaoConstruction selectEvalTianbaoConstructionById(Long id);

    /**
     * 查询天然林资源保护工程建设情况统计列表
     * 
     * @param evalTianbaoConstruction 天然林资源保护工程建设情况统计
     * @return 天然林资源保护工程建设情况统计集合
     */
    List<EvalTianbaoConstruction> selectEvalTianbaoConstructionList(EvalTianbaoConstruction evalTianbaoConstruction);

    /**
     * 新增天然林资源保护工程建设情况统计
     * 
     * @param evalTianbaoConstruction 天然林资源保护工程建设情况统计
     * @return 结果
     */
    int insertEvalTianbaoConstruction(EvalTianbaoConstruction evalTianbaoConstruction);

    /**
     * 修改天然林资源保护工程建设情况统计
     * 
     * @param evalTianbaoConstruction 天然林资源保护工程建设情况统计
     * @return 结果
     */
    int updateEvalTianbaoConstruction(EvalTianbaoConstruction evalTianbaoConstruction);

    /**
     * 删除天然林资源保护工程建设情况统计
     * 
     * @param id 天然林资源保护工程建设情况统计ID
     * @return 结果
     */
    int deleteEvalTianbaoConstructionById(Long id);

    /**
     * 批量删除天然林资源保护工程建设情况统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEvalTianbaoConstructionByIds(String[] ids);
}
