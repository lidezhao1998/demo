package com.ruoyi.zaihai.caiji.service.impl;

import java.util.List;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;

import com.ruoyi.zaihai.caiji.domain.*;
import com.ruoyi.zaihai.caiji.mapper.*;
import com.ruoyi.zaihai.caiji.service.ICTaskSelectAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 草原鼠害发生与防治情况Service业务层处理
 *
 * @author sudongdong
 * @date 2020-05-26
 */
@Service
public class CTaskSelectAllServiceImpl implements ICTaskSelectAllService
{
    @Autowired
    private CTaskSelectAllMapper cTaskSelectAllMapper;

    @Autowired
    private CTaskChfzMapper cTaskChfzMapper;

    @Autowired
    private CTaskFznbMapper cTaskFznbMapper;


    @Autowired
    private CTaskScnyMapper cTaskScnyMapper;

    @Autowired
    private CTaskSczjMapper cTaskSczjMapper;


    @Override
    public CTaskSelectAll selectCTaskSelectAllById(Long id) {
        return cTaskSelectAllMapper.selectCTaskSelectAllById(id);
    }

    /**
     * 查询草原鼠害发生与防治情况列表
     *
     * @return 草原鼠害发生与防治情况
     */
    @Override
    public List<CTaskSelectAll> selectCTaskSelectAllList(CTaskSelectAll cTaskSelectAll)
    {
        return cTaskSelectAllMapper.selectCTaskSelectAllList(cTaskSelectAll);
    }

    @Override
    public List<CTaskSelectAll> selectCTaskSelectAllList1(CTaskSelectAll cTaskSelectAll)
    {
        return cTaskSelectAllMapper.selectCTaskSelectAllList1(cTaskSelectAll);
    }

    @Override
    public int insertCTaskSelectAll(CTaskSelectAll cTaskSelectAll)
    {
        return cTaskSelectAllMapper.insertCTaskSelectAll(cTaskSelectAll);
    }

    @Override
    public int updateCTaskSelectAll(CTaskSelectAll cTaskSelectAll)
    {
        cTaskSelectAll.setUpdateTime(DateUtils.getNowDate());
        return cTaskSelectAllMapper.updateCTaskSelectAll(cTaskSelectAll);
    }




    @Override
    public int deleteCTaskSelectAllById(Long id)
    {
        return cTaskSelectAllMapper.deleteCTaskSelectAllById(id) ;
    }


}
