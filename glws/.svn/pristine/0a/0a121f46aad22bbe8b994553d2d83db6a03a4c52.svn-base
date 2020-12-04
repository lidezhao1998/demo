package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PublicDataMapper;
import com.ruoyi.system.domain.PublicData;
import com.ruoyi.system.service.IPublicDataService;
import com.ruoyi.common.core.text.Convert;

/**
 * 生态效益价值评价社会公共数据Service业务层处理
 * 
 * @author hdp
 * @date 2020-06-28
 */
@Service
public class PublicDataServiceImpl implements IPublicDataService 
{
    @Autowired
    private PublicDataMapper publicDataMapper;

    /**
     * 查询生态效益价值评价社会公共数据
     * 
     * @param dataid 生态效益价值评价社会公共数据ID
     * @return 生态效益价值评价社会公共数据
     */
    @Override
    public PublicData selectPublicDataById(Long dataid)
    {
        return publicDataMapper.selectPublicDataById(dataid);
    }

    /**
     * 查询生态效益价值评价社会公共数据列表
     * 
     * @param publicData 生态效益价值评价社会公共数据
     * @return 生态效益价值评价社会公共数据
     */
    @Override
    public List<PublicData> selectPublicDataList(PublicData publicData)
    {
        return publicDataMapper.selectPublicDataList(publicData);
    }

    /**
     * 新增生态效益价值评价社会公共数据
     * 
     * @param publicData 生态效益价值评价社会公共数据
     * @return 结果
     */
    @Override
    public int insertPublicData(PublicData publicData)
    {
        return publicDataMapper.insertPublicData(publicData);
    }

    /**
     * 修改生态效益价值评价社会公共数据
     * 
     * @param publicData 生态效益价值评价社会公共数据
     * @return 结果
     */
    @Override
    public int updatePublicData(PublicData publicData)
    {
        return publicDataMapper.updatePublicData(publicData);
    }

    /**
     * 删除生态效益价值评价社会公共数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePublicDataByIds(String ids)
    {
        return publicDataMapper.deletePublicDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生态效益价值评价社会公共数据信息
     * 
     * @param dataid 生态效益价值评价社会公共数据ID
     * @return 结果
     */
    @Override
    public int deletePublicDataById(Long dataid)
    {
        return publicDataMapper.deletePublicDataById(dataid);
    }
}
