package com.ruoyi.zaihai.caiji.service;

import com.ruoyi.zaihai.caiji.domain.CRatKnowledge;

import java.util.List;

/**
 * 鼠害知识库信息Service接口
 * 
 * @author ruoyi
 * @date 2020-09-04
 */
public interface ICRatKnowledgeService 
{
    /**
     * 查询鼠害知识库信息
     * 
     * @param id 鼠害知识库信息ID
     * @return 鼠害知识库信息
     */
        CRatKnowledge selectCRatKnowledgeById(Long id);

    /**
     * 查询鼠害知识库信息列表
     * 
     * @param cRatKnowledge 鼠害知识库信息
     * @return 鼠害知识库信息集合
     */
    List<CRatKnowledge> selectCRatKnowledgeList(CRatKnowledge cRatKnowledge);

    /**
     * 新增鼠害知识库信息
     * 
     * @param cRatKnowledge 鼠害知识库信息
     * @return 结果
     */
    int insertCRatKnowledge(CRatKnowledge cRatKnowledge);

    /**
     * 修改鼠害知识库信息
     * 
     * @param cRatKnowledge 鼠害知识库信息
     * @return 结果
     */
    int updateCRatKnowledge(CRatKnowledge cRatKnowledge);

    /**
     * 批量删除鼠害知识库信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCRatKnowledgeByIds(String ids);

    /**
     * 删除鼠害知识库信息信息
     * 
     * @param id 鼠害知识库信息ID
     * @return 结果
     */
    int deleteCRatKnowledgeById(Long id);

    /**
     * 删除鼠害知识库图片
     *
     * @param id 鼠害知识库信息ID
     * @return 结果
     */
    int updateCRatKnowledgePicture(String id);

    boolean checkCode(String code);
}
