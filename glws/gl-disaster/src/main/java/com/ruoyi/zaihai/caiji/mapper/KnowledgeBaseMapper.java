package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CDiseaseKnowledge;

import java.util.List;

/**
 * 草原病害草知识库信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-29
 */
public interface KnowledgeBaseMapper
{
    /**
     * 查询草原病害草知识库信息
     * 
     * @param id 草原病害草知识库信息ID
     * @return 草原病害草知识库信息
     */
        CDiseaseKnowledge selectCDiseaseKnowledgeById(Long id);

    /**
     * 查询草原病害草知识库信息列表
     * 
     * @param cDiseaseKnowledge 草原病害草知识库信息
     * @return 草原病害草知识库信息集合
     */
    List<CDiseaseKnowledge> selectCDiseaseKnowledgeList(CDiseaseKnowledge cDiseaseKnowledge);

    /**
     * 新增草原病害草知识库信息
     * 
     * @param cDiseaseKnowledge 草原病害草知识库信息
     * @return 结果
     */
    int insertCDiseaseKnowledge(CDiseaseKnowledge cDiseaseKnowledge);

    /**
     * 修改草原病害草知识库信息
     * 
     * @param cDiseaseKnowledge 草原病害草知识库信息
     * @return 结果
     */
    int updateCDiseaseKnowledge(CDiseaseKnowledge cDiseaseKnowledge);

    /**
     * 删除草原病害草知识库信息
     * 
     * @param id 草原病害草知识库信息ID
     * @return 结果
     */
    int deleteCDiseaseKnowledgeById(Long id);

    /**
     * 批量删除草原病害草知识库信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCDiseaseKnowledgeByIds(String[] ids);
}
