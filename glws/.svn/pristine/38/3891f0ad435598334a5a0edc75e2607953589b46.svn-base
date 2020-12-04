package com.ruoyi.zaihai.caiji.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateTime;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.zaihai.caiji.mapper.CRatpestKnowledgeMapper;
import com.ruoyi.zaihai.caiji.domain.CRatpestKnowledge;
import com.ruoyi.zaihai.caiji.service.ICRatpestKnowledgeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 草原鼠虫害知识库信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-05-07
 */
@Service
public class CRatpestKnowledgeServiceImpl implements ICRatpestKnowledgeService {
    @Autowired
    private CRatpestKnowledgeMapper cRatpestKnowledgeMapper;

    /**
     * 查询草原鼠虫害知识库信息
     *
     * @param id 草原鼠虫害知识库信息ID
     * @return 草原鼠虫害知识库信息
     */
    @Override
    public CRatpestKnowledge selectCRatpestKnowledgeById(Long id) {
        return cRatpestKnowledgeMapper.selectCRatpestKnowledgeById(id);
    }

    /**
     * 查询草原鼠虫害知识库信息列表
     *
     * @param cRatpestKnowledge 草原鼠虫害知识库信息
     * @return 草原鼠虫害知识库信息
     */
    @Override
    public List<CRatpestKnowledge> selectCRatpestKnowledgeList(CRatpestKnowledge cRatpestKnowledge) {
        return cRatpestKnowledgeMapper.selectCRatpestKnowledgeList(cRatpestKnowledge);
    }

    /**
     * 新增草原鼠虫害知识库信息
     *
     * @param cRatpestKnowledge 草原鼠虫害知识库信息
     * @return 结果
     */
    @Override
    public int insertCRatpestKnowledge(CRatpestKnowledge cRatpestKnowledge) {
        Long userId = ShiroUtils.getSysUser().getUserId();
        Date date = DateUtils.getNowDate();
        cRatpestKnowledge.setCreateTime(date);
        cRatpestKnowledge.setUpdateTime(date);
        cRatpestKnowledge.setCreateBy(userId.toString());
        cRatpestKnowledge.setUpdateBy(userId.toString());
        return cRatpestKnowledgeMapper.insertCRatpestKnowledge(cRatpestKnowledge);
    }

    /**
     * 修改草原鼠虫害知识库信息
     *
     * @param cRatpestKnowledge 草原鼠虫害知识库信息
     * @return 结果
     */
    @Override
    public int updateCRatpestKnowledge(CRatpestKnowledge cRatpestKnowledge) {
        Long userId = ShiroUtils.getSysUser().getUserId();
        Date date = DateUtils.getNowDate();
        cRatpestKnowledge.setUpdateTime(date);
        cRatpestKnowledge.setUpdateBy(userId.toString());
        return cRatpestKnowledgeMapper.updateCRatpestKnowledge(cRatpestKnowledge);
    }

    /**
     * 删除草原鼠虫害知识库信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCRatpestKnowledgeByIds(String ids) {
        return cRatpestKnowledgeMapper.deleteCRatpestKnowledgeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除草原鼠虫害知识库信息信息
     *
     * @param id 草原鼠虫害知识库信息ID
     * @return 结果
     */
    @Override
    public int deleteCRatpestKnowledgeById(Long id) {
        return cRatpestKnowledgeMapper.deleteCRatpestKnowledgeById(id);
    }

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    @Override
    public int CRatpestKnowledgeUpdatePhoto(String id) {
        return cRatpestKnowledgeMapper.CRatpestKnowledgeUpdatePhoto(id);
    }

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    @Override
    public int CRatpestKnowledgeUpdatesixPhotos(String id) {
        return cRatpestKnowledgeMapper.CRatpestKnowledgeUpdatesixPhotos(id);

    }

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    @Override
    public int CRatpestKnowledgeUpdatepupaPhotos(String id) {
        return cRatpestKnowledgeMapper.CRatpestKnowledgeUpdatepupaPhotos(id);

    }

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    @Override
    public int CRatpestKnowledgeUpdatehazardPhotos(String id) {
        return cRatpestKnowledgeMapper.CRatpestKnowledgeUpdatehazardPhotos(id);

    }

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    @Override
    public int CRatpestKnowledgeUpdatenationalMap(String id) {
        return cRatpestKnowledgeMapper.CRatpestKnowledgeUpdatenationalMap(id);

    }

    /**
     * 删除草原鼠虫害知识库信息图片
     */
    @Override
    public int CRatpestKnowledgeUpdateprovincialMap(String id) {
        return cRatpestKnowledgeMapper.CRatpestKnowledgeUpdateprovincialMap(id);

    }

    @Override
    public boolean checkCode(String code) {
        List<String> list = cRatpestKnowledgeMapper.checkCode(code);
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
