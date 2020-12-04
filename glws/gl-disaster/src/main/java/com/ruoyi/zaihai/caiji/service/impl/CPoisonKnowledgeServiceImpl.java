package com.ruoyi.zaihai.caiji.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.caiji.domain.CPoisonKnowledge;
import com.ruoyi.zaihai.caiji.mapper.CPoisonKnowledgeMapper;
import com.ruoyi.zaihai.caiji.service.ICPoisonKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

import java.util.List;

/**
 * 【毒害草知识库信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-15
 */
@Service
public class CPoisonKnowledgeServiceImpl implements ICPoisonKnowledgeService
{
    @Autowired
    private CPoisonKnowledgeMapper cPoisonKnowledgeMapper;

    /**
     * 查询【毒害草知识库信息
     * 
     * @param id 【毒害草知识库信息ID
     * @return 【毒害草知识库信息
     */
    @Override
    public CPoisonKnowledge selectCPoisonKnowledgeById(Long id)
    {
        return cPoisonKnowledgeMapper.selectCPoisonKnowledgeById(id);
    }

    /**
     * 查询【毒害草知识库信息列表
     * 
     * @param cPoisonKnowledge 【毒害草知识库信息
     * @return 【毒害草知识库信息
     */
    @Override
    public List<CPoisonKnowledge> selectCPoisonKnowledgeList(CPoisonKnowledge cPoisonKnowledge)
    {
        return cPoisonKnowledgeMapper.selectCPoisonKnowledgeList(cPoisonKnowledge);
    }

    /**
     * 新增【毒害草知识库信息
     * 
     * @param cPoisonKnowledge 【毒害草知识库信息
     * @return 结果
     */
    @Override
    public int insertCPoisonKnowledge(CPoisonKnowledge cPoisonKnowledge)
    {
        cPoisonKnowledge.setCreateTime(DateUtils.getNowDate());
        return cPoisonKnowledgeMapper.insertCPoisonKnowledge(cPoisonKnowledge);
    }

    /**
     * 修改【毒害草知识库信息
     * 
     * @param cPoisonKnowledge 【毒害草知识库信息
     * @return 结果
     */
    @Override
    public int updateCPoisonKnowledge(CPoisonKnowledge cPoisonKnowledge)
    {
        cPoisonKnowledge.setUpdateTime(DateUtils.getNowDate());
        return cPoisonKnowledgeMapper.updateCPoisonKnowledge(cPoisonKnowledge);
    }

    /**
     * 删除【毒害草知识库信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCPoisonKnowledgeByIds(String ids)
    {
        return cPoisonKnowledgeMapper.deleteCPoisonKnowledgeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除毒害草知识库信息
     * 
     * @param id 【毒害草知识库信息ID
     * @return 结果
     */
    @Override
    public int deleteCPoisonKnowledgeById(Long id)
    {
        return cPoisonKnowledgeMapper.deleteCPoisonKnowledgeById(id);
    }

    @Override
    public boolean checkCode(String code) {
        List<String> list = cPoisonKnowledgeMapper.checkCode(code);
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除毒害草知识库信息生态图图片
     */
    @Override
    public int CPoisonKnowledgeUpdateEcoloPhotos(String id){
        return cPoisonKnowledgeMapper.CPoisonKnowledgeUpdateEcoloPhotos(id);
    }

    /**
     * 删除毒害草知识库信息分布图图片
     */
    @Override
    public int CPoisonKnowledgeUpdateDistrPhotos(String id){
        return cPoisonKnowledgeMapper.CPoisonKnowledgeUpdateDistrPhotos(id);
    }
    /**
     * 删除毒害草知识库信息形态图图片
     */
    @Override
    public int CPoisonKnowledgeUpdateMorphPhotos(String id){
        return cPoisonKnowledgeMapper.CPoisonKnowledgeUpdateMorphPhotos(id);
    }
}
