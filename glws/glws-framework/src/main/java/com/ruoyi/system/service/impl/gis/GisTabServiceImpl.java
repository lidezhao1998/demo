package com.ruoyi.system.service.impl.gis;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.gis.GisTab;
import com.ruoyi.system.mapper.gis.GisTabMapper;
import com.ruoyi.system.service.gis.EologicalAttributesService;
import com.ruoyi.system.service.gis.IGisTabService;
import com.ruoyi.system.service.impl.SysDeptServiceImpl;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/6/23
 */
@Service
public class GisTabServiceImpl implements IGisTabService {
    @Autowired
    private SysUserServiceImpl sysUserService;
    @Autowired
    private SysDeptServiceImpl sysDeptService;
    @Autowired
    private GisTabMapper gisTabMapper;

 @Autowired
    private EologicalAttributesService eologicalAttributesService;


    @Override
    public List<GisTab> getListGisTab(Long userId, GisTab gis) {
        List<GisTab> gisTabs = new ArrayList<>();
        deptByUserId(userId).stream().forEach(createBy -> {
            gis.setCreateBy(String.valueOf(createBy));
            gisTabMapper.GisTabByCerateBy(gis).stream().forEach(gisTab -> {
                if (gisTab != null) {
                    if(gisTab.getIs_visible()==0){
                        gisTabs.add(gisTab);
                    }else {
                        if(createBy.equals(userId)){
                            gisTabs.add(gisTab);
                        }
                    }
                }
            });

        });
        return gisTabs;
    }

    /**
     * 对象转shpFile树
     *
     * @param gisTabList gisTabList
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<GisTab> gisTabList) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (GisTab gisTab : gisTabList) {
            Ztree ztree = new Ztree();
            ztree.setId(Long.valueOf(gisTab.getId()));
            ztree.setpId(Long.valueOf(gisTab.getId()));
            ztree.setName(gisTab.getName());
            ztree.setTitle(gisTab.getName());
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
    public int updataGisTab(GisTab gisTab) {
        return gisTabMapper.updataGisTab(gisTab);
    }

    @Override
    public int insertGisTab(GisTab gisTab) {
        return gisTabMapper.insertGisTab(gisTab);
    }

    @Override
    public int deleteGisTab(int gisId) {
        return gisTabMapper.deleteGisTab(gisId);
    }

    @Override
    public GisTab getGisTabById(int id) {
        return gisTabMapper.getGisTabById(id);
    }

    @Override
    public long getGisTabMaxId() {
        return gisTabMapper.getGisTabMaxId();
    }

    @Override
    public List<HashMap<String, Object>> getGisTabYear() {
        return gisTabMapper.getGisTabYear();
    }

    @Override
    public List<GisTab> getListById(String shpIds) {
        String[] split = shpIds.split(",");
        return gisTabMapper.getListById(split);
    }

    @Override
    public String getArea(String geo) {
        return gisTabMapper.getArea(geo);
    }

    @Override
    public Map getGrassLandArea(String substring, String host) throws IOException, InterruptedException {

        //String[] strings = JavaRunPython.areaPython(substring, host);
        HashMap<Object, Object> map = new HashMap<>();
        /*for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                //草原总面积
                String string = strings[0];
                map.put("sumArea", string);
            } else if (i == 1) {
                //涵养水源量
                String string = strings[1];
                map.put("wso", string);
            } else if (i == 2) {
                //草原类型面积
                String sub = strings[2].substring(1, strings[2].length() - 1);
                String[] splitArea = sub.split(",");
                for (int j = 0; j < splitArea.length; j++) {
                    HashMap<Object, Object> hashMap = new HashMap<>();
                    String[] split = splitArea[j].split(":");
                    String attributeName = eologicalAttributesService.getAttributeName(Integer.parseInt(split[0]));
                    hashMap.put("area", split[1]);
                    hashMap.put("attributeName ", attributeName);
                    map.put("area", hashMap);
                }
            }
        }*/
        return map;
    }

    @Override
    public GisTab getTabById(int id) {
        return gisTabMapper.getGisTabById(id);
    }

    @Override
    public List<GisTab> listLimit(int num, int size, List<GisTab> list) {
        ArrayList<GisTab> objects = new ArrayList<>();
        int numSize=(num-1)*size;
        int limitSize=size*num;
        for(int i=numSize;i<limitSize ;i++){
            if(i==list.size()){
                return objects;
            }
            objects.add(list.get(i));
        }
        return objects;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GisTabServiceImpl gisMapService = new GisTabServiceImpl();
        gisMapService.getGrassLandArea("", "");
    }
}
