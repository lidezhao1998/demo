package com.ruoyi.zaihai.caiji.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.zaihai.caiji.mapper.CTaskFznbMapper;
import com.ruoyi.zaihai.caiji.domain.CTaskFznb;
import com.ruoyi.zaihai.caiji.service.ICTaskFznbService;
import com.ruoyi.common.core.text.Convert;

/**
 * 发生防治年报Service业务层处理
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
@Service
public class CTaskFznbServiceImpl implements ICTaskFznbService 
{
    @Autowired
    private CTaskFznbMapper cTaskFznbMapper;

    /**
     * 查询发生防治年报
     * 
     * @param id 发生防治年报ID
     * @return 发生防治年报
     */
    @Override
    public CTaskFznb selectCTaskFznbById(Long id)
    {
        return cTaskFznbMapper.selectCTaskFznbById(id);
    }

    @Override
    public List<CTaskFznb> selectCTaskFznbParentList(CTaskFznb cTaskFznb) {
        List<CTaskFznb> list = cTaskFznbMapper.selectCTaskFznbParentList(cTaskFznb);
        if (cTaskFznb.getProvince()!=null &&!"".equals(cTaskFznb.getProvince())){
            for (int i = list.size()-1; i>=0; i--) {
                if (!cTaskFznb.getProvince().contains(list.get(i).getProvince())){
                    list.remove(i);
                }
            }
        }
        return list;
    }

    /**
     * 查询发生防治年报列表
     * 
     * @param cTaskFznb 发生防治年报
     * @return 发生防治年报
     */
    @Override
    public List<CTaskFznb> selectCTaskFznbList(CTaskFznb cTaskFznb)
    {
        return cTaskFznbMapper.selectCTaskFznbList(cTaskFznb);
    }

    /**
     * 新增发生防治年报
     * 
     * @param cTaskFznb 发生防治年报
     * @return 结果
     */
    @Override
    public int insertCTaskFznb(CTaskFznb cTaskFznb)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        long id =currentUser.getUserId();
        if(id!=0){
            cTaskFznb.setCreateBy(String.valueOf(id));
        }
        cTaskFznb.setCreatTime(DateUtils.getNowDate());
        return cTaskFznbMapper.insertCTaskFznb(cTaskFznb);
    }

    /**
     * 修改发生防治年报
     * 
     * @param cTaskFznb 发生防治年报
     * @return 结果
     */
    @Override
    public int updateCTaskFznb(CTaskFznb cTaskFznb)
    {
        cTaskFznb.setUpdateTime(DateUtils.getNowDate());
        return cTaskFznbMapper.updateCTaskFznb(cTaskFznb);
    }

    /**
     * 删除发生防治年报对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCTaskFznbByIds(String ids)
    {
        return cTaskFznbMapper.deleteCTaskFznbByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除发生防治年报信息
     * 
     * @param id 发生防治年报ID
     * @return 结果
     */
    @Override
    public int deleteCTaskFznbById(Long id)
    {
        return cTaskFznbMapper.deleteCTaskFznbById(id);
    }

    /**
     * 省级用户查看柱状图根据市级分组
     *
     * @param id 发生防治年报ID
     * @return 结果
     */
    @Override
    public List<CTaskFznb> selectCTaskFznbCityList(CTaskFznb cTaskFznb) {
        return cTaskFznbMapper.selectCTaskFznbCityList(cTaskFznb);
    }
}
