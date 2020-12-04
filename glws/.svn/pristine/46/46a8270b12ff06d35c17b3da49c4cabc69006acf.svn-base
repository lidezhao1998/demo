package com.ruoyi.zaihai.caiji.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.zaihai.caiji.domain.CGroundSurvey;
import com.ruoyi.zaihai.caiji.domain.CPlot;
import com.ruoyi.zaihai.caiji.mapper.CGroundSurveyMapper;
import com.ruoyi.zaihai.caiji.mapper.CPlotMapper;
import com.ruoyi.zaihai.caiji.service.ICGroundSurveyService;
import com.ruoyi.zaihai.common.mapper.CResourceMapper;
import com.ruoyi.zaihai.enums.FlowStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 地面调查数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
@Service
public class CGroundSurveyServiceImpl implements ICGroundSurveyService
{
    @Autowired
    private CGroundSurveyMapper cGroundSurveyMapper;

    @Autowired
    private CPlotMapper cPlotMapper;
    @Autowired
    private CResourceMapper cResourceMapper;

    /**
     * 查询地面调查数据
     * 
     * @param groundId 地面调查数据ID
     * @return 地面调查数据
     */
    @Override
    public CGroundSurvey selectCGroundSurveyById(Long groundId)
    {
        return cGroundSurveyMapper.selectCGroundSurveyById(groundId);
    }

    /**
     * 查询地面调查数据列表
     * 
     * @param cGroundSurvey 地面调查数据
     * @return 地面调查数据
     */

    @Override
    public List<CGroundSurvey> selectCGroundSurveyList(CGroundSurvey cGroundSurvey)
    {
        return cGroundSurveyMapper.selectCGroundSurveyList(cGroundSurvey);
    }
   /* @DataScope(deptAlias = "g", userAlias = "u")*/
    @Override
    public List<CGroundSurvey> selectCGroundSurveyList1(CGroundSurvey cGroundSurvey)
    {
        return cGroundSurveyMapper.selectCGroundSurveyList1(cGroundSurvey);
    }

    /**
     * 新增地面调查数据
     * 
     * @param cGroundSurvey 地面调查数据
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCGroundSurvey(CGroundSurvey cGroundSurvey)
    {
       /* if(!cGroundSurvey.getPhotoUrl().isEmpty()){
        String[] photoIdList = cGroundSurvey.getPhotoUrl().split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i<photoIdList.length; i++) {
            list.add(photoIdList[i]);
        }
        for (int x = 0; x < list.size(); x++) {
            String a = list.get(x);
            CResource cResource = cResourceMapper.selectCResourceById(Long.parseLong(a));
            String str = cResource.getFilename().substring(cResource.getFilename().lastIndexOf(".") + 1);
            cResource.setFilename(cGroundSurvey.getCodeId()+"-"+x+1+"."+str);
            cResourceMapper.updateCResource(cResource);
        }
        }*/
        //新增样地数据
        int rows=0;
        if(cGroundSurvey!=null){
        if(cGroundSurvey.getGroundId()!=null){
            rows=cGroundSurveyMapper.updateCGroundSurvey(cGroundSurvey);
        }else{
            cGroundSurvey.setStaute(FlowStatus.DRAF.getStatus());
            cGroundSurvey.setCreateTime(DateUtils.getNowDate());
            SysUser currentUser = ShiroUtils.getSysUser();
            SysDept Dept =currentUser.getDept();
            long id =currentUser.getUserId();
            if(id!=0){
                cGroundSurvey.setCreateBy(String.valueOf(id));
            }
            if(Dept!=null){
                cGroundSurvey.setDept(String.valueOf(Dept.getDeptId()));
                cGroundSurvey.setCode(Dept.getCode());
            }
            rows=cGroundSurveyMapper.insertCGroundSurvey(cGroundSurvey);
        }

        }

        //新增取样数据
        //insertQuYang(cGroundSurvey);

        return rows;
    }
    /**
     * 新增取样数据
     *
     * @param cGroundSurvey 地面调查数据
     *
     */
    public void insertQuYang(CGroundSurvey cGroundSurvey)
    {

        List<CPlot> cPlotList=cGroundSurvey.getCPlotList();
        long groundId=cGroundSurvey.getGroundId();
        if(StringUtils.isNotNull(groundId)){
            if(cPlotList!=null&&cPlotList.size()>0){
                for(int i=0; i<cPlotList.size(); i++){
                    CPlot  cPlotnew=cPlotList.get(i);
                    cPlotnew.setGroundId(groundId);
                    cPlotMapper.insertCPlot(cPlotnew);
                }

            }
        }

    }

    /**
     * 修改地面调查数据
     * 
     * @param cGroundSurvey 地面调查数据
     * @return 结果
     */
    @Override
    public int updateCGroundSurvey(CGroundSurvey cGroundSurvey)
    {
        cGroundSurvey.setUpdateTime(DateUtils.getNowDate());
        return cGroundSurveyMapper.updateCGroundSurvey(cGroundSurvey);
    }
    /**
     * 修改地面调查数据状态
     *
     * @param cGroundSurvey 地面调查数据
     * @return 结果
     */
    @Override
    public int updateCGroundSurveySt(CGroundSurvey cGroundSurvey)
    {
        return cGroundSurveyMapper.updateCGroundSurveyStaute(cGroundSurvey);
    }
    /**
     * 删除地面调查数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCGroundSurveyByIds(String ids)
    {
                cPlotMapper.deleteCPlotGyIds(Convert.toStrArray(ids));
        return cGroundSurveyMapper.deleteCGroundSurveyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除地面调查数据信息
     * 
     * @param groundId 地面调查数据ID
     * @return 结果
     */
    @Override
    public int deleteCGroundSurveyById(Long groundId)
    {
        return cGroundSurveyMapper.deleteCGroundSurveyById(groundId);
    }


    /**
     * 查询总条数
     *
     *
     * @param code 地面调查数据ID
     * @return 结果
     */
    @Override
    public int countCGroundSurvey(String code , Date data){
        return cGroundSurveyMapper.countCGroundSurvey(code ,data);
    }
}
