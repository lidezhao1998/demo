package com.ruoyi.zaihai.caiji.service.impl;

import java.util.List;

import com.ruoyi.zaihai.caiji.domain.CTaskTechnology;
import com.ruoyi.zaihai.caiji.mapper.CTaskTechnologyMapper;
import com.ruoyi.zaihai.caiji.service.ICTaskTechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * 科技成果信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-17
 */
@Service
public class CTaskTechnologyServiceImpl implements ICTaskTechnologyService
{
    @Autowired
    private CTaskTechnologyMapper cTaskTechnologyMapper;


    @Override
    public CTaskTechnology selectCTaskTechnologyById(Long id) {
        return cTaskTechnologyMapper.selectCTaskTechnologyById(id);
    }

    @Override
    public List<CTaskTechnology> selectCTaskTechnologyList(CTaskTechnology cTaskTechnology) {
        return cTaskTechnologyMapper.selectCTaskTechnologyList(cTaskTechnology) ;
    }

    @Override
    public List<CTaskTechnology> selectCTaskTechnologyList1(CTaskTechnology cTaskTechnology) {
        return cTaskTechnologyMapper.selectCTaskTechnologyList1(cTaskTechnology) ;
    }

    @Override
    public int updateCTaskTechnologychehui(CTaskTechnology cTaskTechnology) {
        return cTaskTechnologyMapper.updateCTaskTechnology(cTaskTechnology);
    }




    @Override
    public int insertCTaskTechnology(CTaskTechnology cTaskTechnology) {
        return cTaskTechnologyMapper.insertCTaskTechnology(cTaskTechnology);
    }

    @Override
    public int updateCTaskTechnology(CTaskTechnology cTaskTechnology) {
        return cTaskTechnologyMapper.updateCTaskTechnology(cTaskTechnology);
    }



    @Override
    public int deleteCTaskTechnologyByIds(String ids) {
        return cTaskTechnologyMapper.deleteCTaskTechnologyByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteCTaskTechnologyById(Long id) {
        return cTaskTechnologyMapper.deleteCTaskTechnologyById(id);
    }
}
