package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.PublicData;
import java.util.List;

/**
 * 生态效益价值评价社会公共数据Mapper接口
 * 
 * @author hdp
 * @date 2020-06-28
 */
public interface PublicDataMapper 
{
    /**
     * 查询生态效益价值评价社会公共数据
     * 
     * @param dataid 生态效益价值评价社会公共数据ID
     * @return 生态效益价值评价社会公共数据
     */
        PublicData selectPublicDataById(Long dataid);

    /**
     * 查询生态效益价值评价社会公共数据列表
     * 
     * @param publicData 生态效益价值评价社会公共数据
     * @return 生态效益价值评价社会公共数据集合
     */
    List<PublicData> selectPublicDataList(PublicData publicData);

    /**
     * 新增生态效益价值评价社会公共数据
     * 
     * @param publicData 生态效益价值评价社会公共数据
     * @return 结果
     */
    int insertPublicData(PublicData publicData);

    /**
     * 修改生态效益价值评价社会公共数据
     * 
     * @param publicData 生态效益价值评价社会公共数据
     * @return 结果
     */
    int updatePublicData(PublicData publicData);

    /**
     * 删除生态效益价值评价社会公共数据
     * 
     * @param dataid 生态效益价值评价社会公共数据ID
     * @return 结果
     */
    int deletePublicDataById(Long dataid);

    /**
     * 批量删除生态效益价值评价社会公共数据
     * 
     * @param dataids 需要删除的数据ID
     * @return 结果
     */
    int deletePublicDataByIds(String[] dataids);
}
