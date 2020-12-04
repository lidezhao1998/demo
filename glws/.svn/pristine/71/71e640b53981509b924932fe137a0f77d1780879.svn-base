package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TTaskIdeaMapper;
import com.ruoyi.system.domain.TTaskIdea;
import com.ruoyi.system.service.ITTaskIdeaService;
import com.ruoyi.common.core.text.Convert;

/**
 * 意见Service业务层处理
 *
 * @author ruoyi
 * @date 2020-04-20
 */
@Service
public class TTaskIdeaServiceImpl implements ITTaskIdeaService
{
    @Autowired
    private TTaskIdeaMapper tTaskIdeaMapper;

    /**
     * 查询意见
     *
     * @param reportId 意见ID
     * @return 意见
     */
    @Override
    public List<TTaskIdea> selectTTaskIdeaById(Integer reportId)
    {
        return tTaskIdeaMapper.selectTTaskIdeaById(reportId);
    }

    /**
     * 查询意见列表
     *
     * @param tTaskIdea 意见
     * @return 意见
     */
    @Override
    public List<TTaskIdea> selectTTaskIdeaList(TTaskIdea tTaskIdea)
    {
        return tTaskIdeaMapper.selectTTaskIdeaList(tTaskIdea);
    }

    /**
     * 新增意见
     *
     * @param tTaskIdea 意见
     * @return 结果
     */
    @Override
    public int insertTTaskIdea(TTaskIdea tTaskIdea)
    {
        tTaskIdea.setCreateTime(DateUtils.getNowDate());
        return tTaskIdeaMapper.insertTTaskIdea(tTaskIdea);
    }

    /**
     * 修改意见
     *
     * @param tTaskIdea 意见
     * @return 结果
     */
    @Override
    public int updateTTaskIdea(TTaskIdea tTaskIdea)
    {
        tTaskIdea.setUpdateTime(DateUtils.getNowDate());
        return tTaskIdeaMapper.updateTTaskIdea(tTaskIdea);
    }

    /**
     * 删除意见对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTTaskIdeaByIds(String ids)
    {
        return tTaskIdeaMapper.deleteTTaskIdeaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除意见信息
     *
     * @param id 意见ID
     * @return 结果
     */
    @Override
    public int deleteTTaskIdeaById(Integer id)
    {
        return tTaskIdeaMapper.deleteTTaskIdeaById(id);
    }
}
