package com.ruoyi.zaihai.caiji.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.caiji.domain.CDiseaseKnowledge;
import com.ruoyi.zaihai.caiji.mapper.KnowledgeBaseMapper;
import com.ruoyi.zaihai.caiji.service.ICDiseaseKnowledgeService;
import com.ruoyi.zaihai.caiji.service.KnowledgeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 草原病害草知识库信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-29
 */
@Service
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService
{
    @Autowired
    private KnowledgeBaseMapper knowledgeBaseMapper;

    /**
     * 查询草原病害草知识库信息
     * 
     * @param id 草原病害草知识库信息ID
     * @return 草原病害草知识库信息
     */
    @Override
    public CDiseaseKnowledge selectCDiseaseKnowledgeById(Long id)
    {
        return knowledgeBaseMapper.selectCDiseaseKnowledgeById(id);
    }

    /**
     * 查询草原病害草知识库信息列表
     * 
     * @param cDiseaseKnowledge 草原病害草知识库信息
     * @return 草原病害草知识库信息
     */
    @Override
    public List<CDiseaseKnowledge> selectCDiseaseKnowledgeList(CDiseaseKnowledge cDiseaseKnowledge)
    {
        return knowledgeBaseMapper.selectCDiseaseKnowledgeList(cDiseaseKnowledge);
    }

    /**
     * 新增草原病害草知识库信息
     * 
     * @param cDiseaseKnowledge 草原病害草知识库信息
     * @return 结果
     */
    @Override
    public int insertCDiseaseKnowledge(CDiseaseKnowledge cDiseaseKnowledge)
    {
        cDiseaseKnowledge.setCreateTime(DateUtils.getNowDate());
        return knowledgeBaseMapper.insertCDiseaseKnowledge(cDiseaseKnowledge);
    }

    /**
     * 修改草原病害草知识库信息
     * 
     * @param cDiseaseKnowledge 草原病害草知识库信息
     * @return 结果
     */
    @Override
    public int updateCDiseaseKnowledge(CDiseaseKnowledge cDiseaseKnowledge)
    {
        cDiseaseKnowledge.setUpdateTime(DateUtils.getNowDate());
        return knowledgeBaseMapper.updateCDiseaseKnowledge(cDiseaseKnowledge);
    }

    /**
     * 删除草原病害草知识库信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCDiseaseKnowledgeByIds(String ids)
    {
        return knowledgeBaseMapper.deleteCDiseaseKnowledgeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除草原病害草知识库信息信息
     * 
     * @param id 草原病害草知识库信息ID
     * @return 结果
     */
    @Override
    public int deleteCDiseaseKnowledgeById(Long id)
    {
        return knowledgeBaseMapper.deleteCDiseaseKnowledgeById(id);
    }
}
