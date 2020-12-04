package com.ruoyi.zaihai.caiji.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.zaihai.caiji.domain.CTaskScny;
import com.ruoyi.zaihai.caiji.mapper.CTaskScnyMapper;
import com.ruoyi.zaihai.caiji.service.ICTaskScnyService;
import com.ruoyi.zaihai.enums.ArabicToChineseNumerals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 草原鼠虫害防治农药使用况Service业务层处理
 * 
 * @author sudongdong
 * @date 2020-05-26
 */
@Service
public class CTaskScnyServiceImpl implements ICTaskScnyService 
{
    @Autowired
    private CTaskScnyMapper cTaskScnyMapper;

    /**
     * 查询草原鼠虫害防治农药使用况
     * 
     * @param id 草原鼠虫害防治农药使用况ID
     * @return 草原鼠虫害防治农药使用况
     */
    @Override
    public CTaskScny selectCTaskScnyById(Long id)
    {
        return cTaskScnyMapper.selectCTaskScnyById(id);
    }



    /**
     * 查询草原鼠虫害防治农药使用况列表
     * 
     * @param cTaskScny 草原鼠虫害防治农药使用况
     * @return 草原鼠虫害防治农药使用况
     */
    @Override
    public List<CTaskScny> selectCTaskScnyList(CTaskScny cTaskScny)
    {
        return cTaskScnyMapper.selectCTaskScnyList(cTaskScny);
    }

    /**
     * 查询草原鼠害发生与防治情况父列表
     *
     * @param cTaskScny 草原鼠害发生与防治情况
     * @return 草原鼠害发生与防治情况
     */

    @Override
    public List<CTaskScny> selectCTaskScnyParentList(CTaskScny cTaskScny) {
        List<CTaskScny> list = cTaskScnyMapper.selectCTaskScnyParentList(cTaskScny);
        if (cTaskScny.getProvince()!=null &&!"".equals(cTaskScny.getProvince())){
            for (int i = list.size()-1; i>=0; i--) {
                if (!cTaskScny.getProvince().contains(list.get(i).getProvince())){
                    list.remove(i);
                }
            }
        }
        return list;
    }

    @Override
    public List<CTaskScny> selectCTaskScnyCityList(CTaskScny cTaskScny) {
        return cTaskScnyMapper.selectCTaskScnyCityList(cTaskScny);
    }

    @Override
    public List<CTaskScny> selectCTaskScnyAreaList(CTaskScny cTaskScny) {
        return cTaskScnyMapper.selectCTaskScnyAreaList(cTaskScny);
    }

    /**
     * 新增草原鼠虫害防治农药使用况
     * 
     * @param cTaskScny 草原鼠虫害防治农药使用况
     * @return 结果
     */
    @Override
    public int insertCTaskScny(CTaskScny cTaskScny)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();
        long id =currentUser.getUserId();
        if(id!=0){
            cTaskScny.setCreateBy(String.valueOf(id));
        }
        cTaskScny.setCreatTime(DateUtils.getNowDate());
        String[] splitWeek = cTaskScny.getWeek().split("-");
        cTaskScny.setYear(splitWeek[0]);
        String week="";
        if (splitWeek[1].length()==1){
            week= ArabicToChineseNumerals.getNumber(splitWeek[1]);
            week="第"+week+"周";
        }else {
            char[] chars = splitWeek[1].toCharArray();
            String one= ArabicToChineseNumerals.getNumber(String.valueOf(chars[0]));
            if (one.equals("一")){
                one="";
            }
            String two= ArabicToChineseNumerals.getNumber(String.valueOf(chars[1]));
            week="第"+one+"十"+two+"周";
        }
        cTaskScny.setWeek(week);
        return cTaskScnyMapper.insertCTaskScny(cTaskScny);
    }

    /**
     * 修改草原鼠虫害防治农药使用况
     * 
     * @param cTaskScny 草原鼠虫害防治农药使用况
     * @return 结果
     */
    @Override
    public int updateCTaskScny(CTaskScny cTaskScny)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        SysDept Dept =currentUser.getDept();
        long id =currentUser.getUserId();
        if(id!=0){
            cTaskScny.setCreateBy(String.valueOf(id));
        }
        cTaskScny.setCreatTime(DateUtils.getNowDate());
        String[] splitWeek = cTaskScny.getWeek().split("-");
        cTaskScny.setYear(splitWeek[0]);
        String week="";
        if (splitWeek[1].length()==1){
            week= ArabicToChineseNumerals.getNumber(splitWeek[1]);
            week="第"+week+"周";
        }else {
            char[] chars = splitWeek[1].toCharArray();
            String one= ArabicToChineseNumerals.getNumber(String.valueOf(chars[0]));
            if (one.equals("一")){
                one="";
            }
            String two= ArabicToChineseNumerals.getNumber(String.valueOf(chars[1]));
            week="第"+one+"十"+two+"周";
        }
        cTaskScny.setWeek(week);
        cTaskScny.setUpdateTime(DateUtils.getNowDate());
        return cTaskScnyMapper.updateCTaskScny(cTaskScny);
    }

    /**
     * 删除草原鼠虫害防治农药使用况对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCTaskScnyByIds(String ids)
    {
        return cTaskScnyMapper.deleteCTaskScnyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除草原鼠虫害防治农药使用况信息
     * 
     * @param id 草原鼠虫害防治农药使用况ID
     * @return 结果
     */
    @Override
    public int deleteCTaskScnyById(Long id)
    {
        return cTaskScnyMapper.deleteCTaskScnyById(id);
    }

    /**
     * 获取登录角色判断权限
     *
     * @param
     * @return 修改角色信息
     */
    @Override
    public void updateCTaskSczjRoleName(CTaskScny taskScny) {
        cTaskScnyMapper.updateCTaskSczjRoleName(taskScny);
    }


}
