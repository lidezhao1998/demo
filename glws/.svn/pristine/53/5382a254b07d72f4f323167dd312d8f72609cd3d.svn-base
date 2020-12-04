package com.ruoyi.zaihai.caiji.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.zaihai.caiji.domain.Special;
import com.ruoyi.zaihai.caiji.mapper.SpecialMapper;
import com.ruoyi.zaihai.caiji.service.ISpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专题Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-19
 */
@Service
public class SpecialServiceImpl implements ISpecialService
{
    @Autowired
    private SpecialMapper specialMapper;

    /**
     * 查询专题
     * 
     * @param id 专题ID
     * @return 专题
     */
    @Override
    public Special selectSpecialById(Long id)
    {
        return specialMapper.selectSpecialById(id);
    }

    /**
     * 查询专题列表
     * 
     * @param special 专题
     * @return 专题
     */
    @Override
    public List<Special> selectSpecialList(Special special)
    {
        return specialMapper.selectSpecialList(special);
    }

    /**
     * 新增专题
     * 
     * @param special 专题
     * @return 结果
     */
    @Override
    public int insertSpecial(Special special)
    {
        special.setCreateTime(DateUtils.getNowDate());
        return specialMapper.insertSpecial(special);
    }

    /**
     * 修改专题
     * 
     * @param special 专题
     * @return 结果
     */
    @Override
    public int updateSpecial(Special special)
    {
        special.setUpdateTime(DateUtils.getNowDate());
        return specialMapper.updateSpecial(special);
    }

    /**
     * 删除专题对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSpecialByIds(String ids)
    {
        return specialMapper.deleteSpecialByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专题信息
     * 
     * @param id 专题ID
     * @return 结果
     */
    @Override
    public int deleteSpecialById(Long id)
    {
        return specialMapper.deleteSpecialById(id);
    }
}
