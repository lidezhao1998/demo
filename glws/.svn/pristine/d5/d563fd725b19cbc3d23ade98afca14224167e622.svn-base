package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CPoisonKnowledge;

import java.util.List;

/**
 * 【毒害草知识库信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-15
 */
public interface CPoisonKnowledgeMapper 
{
    /**
     * 查询【毒害草知识库信息
     * 
     * @param id 【毒害草知识库信息ID
     * @return 【毒害草知识库信息
     */
        CPoisonKnowledge selectCPoisonKnowledgeById(Long id);

    /**
     * 查询【毒害草知识库信息列表
     * 
     * @param cPoisonKnowledge 【毒害草知识库信息
     * @return 【毒害草知识库信息集合
     */
    List<CPoisonKnowledge> selectCPoisonKnowledgeList(CPoisonKnowledge cPoisonKnowledge);

    /**
     * 新增【毒害草知识库信息
     * 
     * @param cPoisonKnowledge 【毒害草知识库信息
     * @return 结果
     */
    int insertCPoisonKnowledge(CPoisonKnowledge cPoisonKnowledge);

    /**
     * 修改【毒害草知识库信息
     * 
     * @param cPoisonKnowledge 【毒害草知识库信息
     * @return 结果
     */
    int updateCPoisonKnowledge(CPoisonKnowledge cPoisonKnowledge);

    /**
     * 删除【毒害草知识库信息
     * 
     * @param id 【毒害草知识库信息ID
     * @return 结果
     */
    int deleteCPoisonKnowledgeById(Long id);

    /**
     * 批量删除【毒害草知识库信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCPoisonKnowledgeByIds(String[] ids);

    List<String> checkCode(String code);


    /**
     * 删除毒害草知识库信息生态图图片
     */
    int CPoisonKnowledgeUpdateEcoloPhotos(String id);

    /**
     * 删除毒害草知识库信息分布图图片
     */
    int CPoisonKnowledgeUpdateDistrPhotos(String id);

    /**
     * 删除毒害草知识库信息形态图图片
     */
    int CPoisonKnowledgeUpdateMorphPhotos(String id);
}
