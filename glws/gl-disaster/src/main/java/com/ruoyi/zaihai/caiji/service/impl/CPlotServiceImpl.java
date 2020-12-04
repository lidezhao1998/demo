package com.ruoyi.zaihai.caiji.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.zaihai.caiji.domain.CPlot;
import com.ruoyi.zaihai.caiji.mapper.CPlotMapper;
import com.ruoyi.zaihai.caiji.service.ICPlotService;
import com.ruoyi.zaihai.common.mapper.CResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 样地Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
@Service
public class CPlotServiceImpl implements ICPlotService
{
    @Autowired
    private CPlotMapper cPlotMapper;
    @Autowired
    private CResourceMapper cResourceMapper;

    /**
     * 查询样地
     * 
     * @param plotId 样地ID
     * @return 样地
     */
    @Override
    public CPlot selectCPlotById(Long plotId)
    {
        return cPlotMapper.selectCPlotById(plotId);
    }
  /**
     * 查询样地
     *
     * @param plotId 样地ID
     * @return 样地
     */
    @Override
    public CPlot selectCPlotDetailById(Long plotId)
    {
        return cPlotMapper.selectCPlotDetailById(plotId);
    }
    /**
     * 查询取样
     *
     * @param groundId 样地ID
     * @return 样地
     */
     public   List<CPlot> selectCPlotGyId(Long groundId)
    {
        return cPlotMapper.selectCPlotGyId(groundId);
    }
    /**
     * 查询取样按时间倒序
     *
     * @param groundId 样地ID
     * @return 样地
     */
    public   List<CPlot>  selectCPlotGyIdCount(Long groundId)
    {
        return cPlotMapper.selectCPlotGyIdCount(groundId);
    }
    /**
     * 查询样地列表
     * 
     * @param cPlot 样地
     * @return 样地
     */
    @Override
    public List<CPlot> selectCPlotList(CPlot cPlot)
    {
        return cPlotMapper.selectCPlotList(cPlot);
    }

    @Override
    public List<CPlot> selectCPlotList1(CPlot cPlot)
    {
        return cPlotMapper.selectCPlotList1(cPlot);
    }


    @Override
    public List<CPlot> selectCPlotList2(CPlot cPlot)
    {
        return cPlotMapper.selectCPlotList2(cPlot);
    }



    /**
     * 新增样地
     * 
     * @param cPlot 样地
     * @return 结果
     */
    @Override
    public int insertCPlot(CPlot cPlot)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        cPlot.setCreateBy(String.valueOf(currentUser.getUserId()));
        cPlot.setCreateTime(DateUtils.getNowDate());
        int sum=cPlotMapper.insertCPlot(cPlot);
        return sum;
    }



    /**
     * 修改样地
     * 
     * @param cPlot 样地
     * @return 结果
     */
    @Override
    public int updateCPlot(CPlot cPlot)
    {

       /* if (cPlot.getPhotoUrl()!=null &&!"".equals(cPlot.getPhotoUrl())){
            cPlot.setPhotoUrl(cPlot.getPhotoUrl() + ",");
        }*/
        return cPlotMapper.updateCPlot(cPlot);
    }


    @Override
    public int updateCPlot1(CPlot cPlot)
    {
        return cPlotMapper.updateCPlot1(cPlot);
    }
    /**
     * 删除样地对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCPlotByIds(String ids)
    {
        return cPlotMapper.deleteCPlotByIds(Convert.toStrArray(ids));
    }

    @Override
    public int selectCPlotById1(Long plotId)
    {
        return cPlotMapper.selectCPlotById1(plotId);
    }

    /**
     * 删除样地信息
     * 
     * @param plotId 样地ID
     * @return 结果
     */
    @Override
    public int deleteCPlotById(Long plotId)
    {
        return cPlotMapper.deleteCPlotById(plotId);
    }

    @Override
    public int selectCPlotListById(Long plotId)
    {
        return cPlotMapper.selectCPlotListById(plotId);
    }










}
