package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TTaskIdea;
import java.util.List;

/**
 * 意见Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-20
 */
public interface TTaskIdeaMapper 
{
    /**
     * 查询意见
     * 
     * @param reportId 意见ID
     * @return 意见
     */
    List<TTaskIdea> selectTTaskIdeaById(Integer reportId);

    /**
     * 查询意见列表
     * 
     * @param tTaskIdea 意见
     * @return 意见集合
     */
    List<TTaskIdea> selectTTaskIdeaList(TTaskIdea tTaskIdea);

    /**
     * 新增意见
     * 
     * @param tTaskIdea 意见
     * @return 结果
     */
    int insertTTaskIdea(TTaskIdea tTaskIdea);

    /**
     * 修改意见
     * 
     * @param tTaskIdea 意见
     * @return 结果
     */
    int updateTTaskIdea(TTaskIdea tTaskIdea);

    /**
     * 删除意见
     * 
     * @param id 意见ID
     * @return 结果
     */
    int deleteTTaskIdeaById(Integer id);

    /**
     * 批量删除意见
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTTaskIdeaByIds(String[] ids);
}
