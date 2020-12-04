package com.ruoyi.system.service.impl.gis;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.gis.GisMap;
import com.ruoyi.system.mapper.gis.GisMapMapper;
import com.ruoyi.system.service.gis.IGisMapService;
import com.ruoyi.system.service.impl.SysDeptServiceImpl;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
@Service
public class GisMapServiceImpl implements IGisMapService {
    @Autowired
    private SysUserServiceImpl sysUserService;
    @Autowired
    private SysDeptServiceImpl sysDeptService;
    @Autowired
    private GisMapMapper gisMapMapper;


    @Override
    public List<Ztree> getListGisMap(Long userId, String year) {
        List<GisMap> gisMaps = new ArrayList<>();
        deptByUserId(userId).stream().forEach(createBy -> {
            gisMapMapper.GisMapByCerateBy(String.valueOf(createBy), year).stream().forEach(gisMap -> {
                if (gisMap != null) {
                    gisMaps.add(gisMap);
                }
            });

        });
        List<Ztree> ztrees = initZtree(gisMaps);
        return ztrees;
    }

    /**
     * 对象转shpFile树
     *
     * @param gisMapList gisMapList
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<GisMap> gisMapList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (GisMap gisMap : gisMapList) {
            Ztree ztree = new Ztree();
            ztree.setId(Long.valueOf(gisMap.getId()));
            ztree.setpId(Long.valueOf(gisMap.getId()));
            ztree.setName(gisMap.getName());
            ztree.setTitle(gisMap.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public List deptByUserId(Long userId) {
        Long deptId = sysUserService.selectUserById(userId).getDeptId();
        List gisMapList = sysDeptService.deptIdByAncestors(deptId);
        gisMapList.add(deptId);
        List list = sysUserService.selectUserIdByDeptId(gisMapList);
        return list;
    }

    @Override
    public int updataGisMap(GisMap gisMap) {
        return gisMapMapper.updataGisMap(gisMap);
    }

    @Override
    public int insertGisMap(GisMap gisMap) {
        if(StringUtils.isEmpty(gisMap.getHazard_rating())){
            gisMap.setHazard_rating("#07dd17");
        }
        return gisMapMapper.insertGisMap(gisMap);
    }

    @Override
    public int deleteGisMap(int gisId) {
        return gisMapMapper.deleteGisMap(gisId);
    }

    @Override
    public GisMap getGisMapById(int id) {
        return gisMapMapper.getGisMapById(id);
    }

    @Override
    public long getGisMapMaxId() {
        return gisMapMapper.getGisMapMaxId();
    }

    @Override
    public List<HashMap<String, Object>> getGisMapYear() {
        return gisMapMapper.getGisMapYear();
    }

    @Override
    public List<GisMap> getListById(String shpIds) {
        String[] split = shpIds.split(",");
        return gisMapMapper.getListById(split);
    }

    @Override
    public String getArea(String geo) {
        return gisMapMapper.getArea(geo);
    }
    @Override
    public GisMap getTabById(int id) {
        return gisMapMapper.getGisMapById(id);
    }

}
