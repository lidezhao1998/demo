package com.ruoyi.zaihai.caiji.mapper;

import com.ruoyi.zaihai.caiji.domain.CRatpestKnowledge;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 草原鼠虫害知识库信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-05-07
 */
public interface CRatpestKnowledgeMapper
{
    /**
     * 查询草原鼠虫害知识库信息
     *
     * @param id 草原鼠虫害知识库信息ID
     * @return 草原鼠虫害知识库信息
     */
        CRatpestKnowledge selectCRatpestKnowledgeById(Long id);

    /**
     * 查询草原鼠虫害知识库信息列表
     *
     * @param cRatpestKnowledge 草原鼠虫害知识库信息
     * @return 草原鼠虫害知识库信息集合
     */
    List<CRatpestKnowledge> selectCRatpestKnowledgeList(CRatpestKnowledge cRatpestKnowledge);

    /**
     * 新增草原鼠虫害知识库信息
     *
     * @param cRatpestKnowledge 草原鼠虫害知识库信息
     * @return 结果
     */
    int insertCRatpestKnowledge(CRatpestKnowledge cRatpestKnowledge);

    /**
     * 修改草原鼠虫害知识库信息
     *
     * @param cRatpestKnowledge 草原鼠虫害知识库信息
     * @return 结果
     */
    int updateCRatpestKnowledge(CRatpestKnowledge cRatpestKnowledge);

    /**
     * 删除草原鼠虫害知识库信息
     *
     * @param id 草原鼠虫害知识库信息ID
     * @return 结果
     */
    int deleteCRatpestKnowledgeById(Long id);

    /**
     * 批量删除草原鼠虫害知识库信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCRatpestKnowledgeByIds(String[] ids);


    /**
     * 删除草原鼠虫害知识库信息图片
     */
    int CRatpestKnowledgeUpdatePhoto(String id);

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    int CRatpestKnowledgeUpdatesixPhotos(String id);

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    int CRatpestKnowledgeUpdatepupaPhotos(String id);

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    int CRatpestKnowledgeUpdatehazardPhotos(String id);

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    int CRatpestKnowledgeUpdatenationalMap(String id);

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    int CRatpestKnowledgeUpdateprovincialMap(String id);

    List<String> checkCode(String code);

}
