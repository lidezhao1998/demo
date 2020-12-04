package com.ruoyi.zaihai.caiji.service.impl;

import java.util.List;

import com.ruoyi.zaihai.caiji.domain.CRatKnowledge;
import com.ruoyi.zaihai.caiji.mapper.CRatKnowledgeMapper;
import com.ruoyi.zaihai.caiji.service.ICRatKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 鼠害知识库信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-04
 */
@Service
public class CRatKnowledgeServiceImpl implements ICRatKnowledgeService
{
    @Autowired
    private CRatKnowledgeMapper cRatKnowledgeMapper;

    /**
     * 查询鼠害知识库信息
     * 
     * @param id 鼠害知识库信息ID
     * @return 鼠害知识库信息
     */
    @Override
    public CRatKnowledge selectCRatKnowledgeById(Long id)
    {
        return cRatKnowledgeMapper.selectCRatKnowledgeById(id);
    }

    /**
     * 查询鼠害知识库信息列表
     * 
     * @param cRatKnowledge 鼠害知识库信息
     * @return 鼠害知识库信息
     */
    @Override
    public List<CRatKnowledge> selectCRatKnowledgeList(CRatKnowledge cRatKnowledge)
    {
        return cRatKnowledgeMapper.selectCRatKnowledgeList(cRatKnowledge);
    }

    /**
     * 新增鼠害知识库信息
     * 
     * @param cRatKnowledge 鼠害知识库信息
     * @return 结果
     */
    @Override
    public int insertCRatKnowledge(CRatKnowledge cRatKnowledge)
    {
        return cRatKnowledgeMapper.insertCRatKnowledge(cRatKnowledge);
    }

    /**
     * 修改鼠害知识库信息
     * 
     * @param cRatKnowledge 鼠害知识库信息
     * @return 结果
     */
    @Override
    public int updateCRatKnowledge(CRatKnowledge cRatKnowledge)
    {
        return cRatKnowledgeMapper.updateCRatKnowledge(cRatKnowledge);
    }

    /**
     * 删除鼠害知识库信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCRatKnowledgeByIds(String ids)
    {
        return cRatKnowledgeMapper.deleteCRatKnowledgeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除鼠害知识库信息信息
     * 
     * @param id 鼠害知识库信息ID
     * @return 结果
     */
    @Override
    public int deleteCRatKnowledgeById(Long id)
    {
        return cRatKnowledgeMapper.deleteCRatKnowledgeById(id);
    }


    /**
     * 删除鼠害知识库图片
     *
     * @param id 鼠害知识库信息ID
     * @return 结果
     */
    @Override
    public int updateCRatKnowledgePicture(String id)
    {
        return cRatKnowledgeMapper.updateCRatKnowledgePicture(id);
    }

    @Override
    public boolean checkCode(String code) {
        List<String> list = cRatKnowledgeMapper.checkCode(code);
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
