package com.sinosoft.analyze.service.impl;

import com.ruoyi.system.service.impl.SysDeptServiceImpl;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import com.sinosoft.analyze.domain.SysGisMap;
import com.sinosoft.analyze.mapper.SysGisMapMapper;
import com.sinosoft.analyze.service.ISysGisMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
@Service
public class SysSysGisMapServiceImpl implements ISysGisMapService {
    private static final Logger log = LoggerFactory.getLogger(SysSysGisMapServiceImpl.class);
    @Autowired
    private SysUserServiceImpl sysUserService;
    @Autowired
    private SysDeptServiceImpl sysDeptService;
    @Autowired
    private SysGisMapMapper sysGisMapMapper;


    @Override
    public List<SysGisMap> getListGisMap(Long userId) {
        List<SysGisMap> gisMaps = new ArrayList<>();
        deptByUserId(userId).stream().forEach(createBy -> {
            sysGisMapMapper.GisMapByCerateBy(String.valueOf(createBy)).stream().forEach(gisMap ->{
                if(gisMap!=null){
                    gisMaps.add(gisMap);
                }
            });

        });
        return gisMaps;
    }

    public List deptByUserId(Long userId) {
        List gisMapList = sysDeptService.deptIdByAncestors(sysUserService.selectUserById(userId).getDeptId());
        gisMapList.add(sysUserService.selectUserById(userId).getDeptId());
        List list = sysUserService.selectUserIdByDeptId(gisMapList);
        return list;
    }

    @Override
    public int updataGisMap(SysGisMap gisMap) {
        return  sysGisMapMapper.updataGisMap(gisMap);
    }

    @Override
    public int insertGisMap(SysGisMap gisMap) {
        return  sysGisMapMapper.insertGisMap(gisMap);
    }

    @Override
    public int deleteGisMap(int gisId) {
        return sysGisMapMapper.deleteGisMap(gisId);
    }

    @Override
    public SysGisMap getGisMapById(int id) {
        return sysGisMapMapper.getGisMapById(id);
    }

    @Override
    public long getGisMapMaxId() {
        return sysGisMapMapper.getGisMapMaxId();
    }
}
