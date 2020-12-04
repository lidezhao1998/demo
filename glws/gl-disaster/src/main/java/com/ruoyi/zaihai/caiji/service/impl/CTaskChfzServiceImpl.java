package com.ruoyi.zaihai.caiji.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.zaihai.caiji.domain.CTaskChfz;
import com.ruoyi.zaihai.caiji.mapper.CTaskChfzMapper;
import com.ruoyi.zaihai.caiji.service.ICTaskChfzService;
import com.ruoyi.zaihai.enums.ArabicToChineseNumerals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 草原虫害发生与防治情况Service业务层处理
 * 
 * @author sudonngdong
 * @date 2020-05-26
 */
@Service
public class CTaskChfzServiceImpl implements ICTaskChfzService 
{
    @Autowired
    private CTaskChfzMapper cTaskChfzMapper;

    /**
     * 查询草原虫害发生与防治情况
     * 
     * @param id 草原虫害发生与防治情况ID
     * @return 草原虫害发生与防治情况
     */
    @Override
    public CTaskChfz selectCTaskChfzById(Long id)
    {
        return cTaskChfzMapper.selectCTaskChfzById(id);
    }

    /**
     * 查询草原虫害发生与防治情况列表
     * 
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 草原虫害发生与防治情况
     */
    @Override
    public List<CTaskChfz> selectCTaskChfzList(CTaskChfz cTaskChfz)
    {
        return cTaskChfzMapper.selectCTaskChfzList(cTaskChfz);
    }

    @Override
    public List<CTaskChfz> selectCTaskChfzParentList(CTaskChfz cTaskChfz) {
        List<CTaskChfz> list = cTaskChfzMapper.selectCTaskChfzParentList(cTaskChfz);
        if (cTaskChfz.getProvince()!=null &&!"".equals(cTaskChfz.getProvince())){
            for (int i = list.size()-1; i>=0; i--) {
                if (!cTaskChfz.getProvince().contains(list.get(i).getProvince())){
                    list.remove(i);
                }
            }
        }
        return list;
    }

    /**
     * 新增草原虫害发生与防治情况
     * 
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 结果
     */
    @Override
    public int insertCTaskChfz(CTaskChfz cTaskChfz)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        long id =currentUser.getUserId();
        if(id!=0){
            cTaskChfz.setCreateBy(String.valueOf(id));
        }
        cTaskChfz.setCreatTime(DateUtils.getNowDate());
        String[] splitWeek = cTaskChfz.getWeek().split("-");
        cTaskChfz.setYear(splitWeek[0]);
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
        cTaskChfz.setWeek(week);

        return cTaskChfzMapper.insertCTaskChfz(cTaskChfz);
    }

    /**
     * 修改草原虫害发生与防治情况
     * 
     * @param cTaskChfz 草原虫害发生与防治情况
     * @return 结果
     */
    @Override
    public int updateCTaskChfz(CTaskChfz cTaskChfz)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        long id =currentUser.getUserId();
        if(id!=0){
            cTaskChfz.setCreateBy(String.valueOf(id));
        }
        cTaskChfz.setCreatTime(DateUtils.getNowDate());
        String[] splitWeek = cTaskChfz.getWeek().split("-");
        cTaskChfz.setYear(splitWeek[0]);
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
        cTaskChfz.setWeek(week);
        cTaskChfz.setUpdateTime(DateUtils.getNowDate());
        return cTaskChfzMapper.updateCTaskChfz(cTaskChfz);
    }

    /**
     * 删除草原虫害发生与防治情况对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCTaskChfzByIds(String ids)
    {
        return cTaskChfzMapper.deleteCTaskChfzByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除草原虫害发生与防治情况信息
     * 
     * @param id 草原虫害发生与防治情况ID
     * @return 结果
     */
    @Override
    public int deleteCTaskChfzById(Long id)
    {
        return cTaskChfzMapper.deleteCTaskChfzById(id);
    }

    @Override
    public List<Map<String, Object>> selectCTaskChfzList2() {
        return cTaskChfzMapper.selectCTaskChfzList2();
    }

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return map地图
     */
    @Override
    public List<Map<String, String>> getRequestCount() {
        return cTaskChfzMapper.getRequestCount();
    }

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 趋势预测
     */
    @Override
    public List<CTaskChfz> selectCTaskChfzAnalyze(CTaskChfz cTaskChfz) {
        return cTaskChfzMapper.selectCTaskChfzAnalyze(cTaskChfz);
    }


    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 折线图
     */
    @Override
    public List<CTaskChfz> selectCTaskChfzListLine(CTaskChfz cTaskChfz) {
        return cTaskChfzMapper.selectCTaskChfzListLine(cTaskChfz);
    }

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 柱状图
     */
    @Override
    public List<CTaskChfz> selectCTaskChfzListColumnar(CTaskChfz cTaskChfz) {
        return cTaskChfzMapper.selectCTaskChfzListColumnar(cTaskChfz);
    }
    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 查询虫害表最大id
     */    @Override
    public long selectCTaskChfzMaxId(CTaskChfz cTaskChfz) {
        return cTaskChfzMapper.selectCTaskChfzMaxId(cTaskChfz);
    }

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页鼠害折线图
     */
    @Override
    public List<CTaskChfz> selectCTaskChfzListLinePage(CTaskChfz cTaskChfz) {
        return cTaskChfzMapper.selectCTaskChfzListLinePage(cTaskChfz);
    }

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页鼠害省级用户柱状图
     */
    @Override
    public List<CTaskChfz> selectCTaskChfzCityList(CTaskChfz cTaskChfz) {
        return cTaskChfzMapper.selectCTaskChfzCityList(cTaskChfz);
    }

    /**
     * 查询草原虫害发生与防治情况信息
     *
     * @param
     * @return 首页鼠害市级用户柱状图
     */
    @Override
    public List<CTaskChfz> selectCTaskChfzCountyList(CTaskChfz cTaskChfz) {
        return cTaskChfzMapper.selectCTaskChfzCountyList(cTaskChfz);
    }

    @Override
    public List<Map<String, Object>> getCityCount() {
        return cTaskChfzMapper.getCityCount();
    }

    @Override
    public List<Map<String, Object>> getEngineeringhazardCityCount() {
        return cTaskChfzMapper.getEngineeringhazardCityCount();
    }


}
