package com.ruoyi.system.service.impl.gis;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.gis.GrasslandEcology;
import com.ruoyi.system.domain.gis.GrasslandEcologyType;
import com.ruoyi.system.mapper.gis.GresslandEcologyMapper;
import com.ruoyi.system.mapper.gis.GresslandEcologyTypeMapper;
import com.ruoyi.system.service.gis.PostGisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/9/18
 */
@Service
public class PostGisServiceLmpl implements PostGisService {
    @Autowired
    private GresslandEcologyTypeMapper gresslandEcologyTypeMapper;

    @Autowired
    private GresslandEcologyMapper gresslandEcologyMapper;

    @Override
    public List<Ztree> getGressTypeList() {
        return initZtreeGrasslandEcologyType(gresslandEcologyTypeMapper.getGressTypeList());
    }

    @Override
    public List<GrasslandEcology> getSection() {
        return gresslandEcologyMapper.getSection();
    }
    @Override
    public List<GrasslandEcology> getGeneric(String sectionCode) {
        return gresslandEcologyMapper.getGeneric(sectionCode);
    }

    @Override
    public List<GrasslandEcology> getBundle(String genericCode) {
        return gresslandEcologyMapper.getBundle(genericCode);
    }

    @Override
    public List<GrasslandEcology> getProvince() {
        return gresslandEcologyMapper.getProvince();
    }

    @Override
    public GrasslandEcology getGarssById(int id) {
        GrasslandEcology garssById = gresslandEcologyMapper.getGarssById(id);
        //保留俩位小数
        DecimalFormat df = new DecimalFormat("#.00");
        Double aDouble = Double.valueOf(garssById.getArea())/1000000;
        garssById.setArea(df.format(aDouble));
        return garssById;
    }

    @Override
    public List<Ztree> getSystemGressTypeList() {
        List<Ztree> ztrees = initZtreeGrasslandEcologyType(gresslandEcologyTypeMapper.getGressTypeList());
        List<Ztree> glMa = initSystemTypeZtreeGrasslandEcologyType(gresslandEcologyTypeMapper.getGressGlmaTypeList());
        ztrees.addAll(glMa);
        return ztrees;
    }


    @Override
    public List<GrasslandEcology> getGressList(GrasslandEcology grasslandEcology) {
        return gresslandEcologyMapper.getGressList(grasslandEcology);
    }



    public List<Ztree> initZtreeGrasslandEcologyType(List<GrasslandEcologyType> grasslandEcologyTypes) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (GrasslandEcologyType grasslandEcologyType : grasslandEcologyTypes) {
            Ztree ztree = new Ztree();
            ztree.setId(Long.valueOf(grasslandEcologyType.getId()));
            ztree.setpId(Long.valueOf(grasslandEcologyType.getParent_id()));
            ztree.setName(grasslandEcologyType.getName());
            ztree.setTitle(grasslandEcologyType.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
    public List<Ztree> initSystemTypeZtreeGrasslandEcologyType(List<GrasslandEcologyType> grasslandEcologyTypes) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (GrasslandEcologyType grasslandEcologyType : grasslandEcologyTypes) {
            Ztree ztree = new Ztree();
            ztree.setId(Long.valueOf(grasslandEcologyType.getId()));
            ztree.setpId(Long.valueOf(grasslandEcologyType.getParent_id()));
            ztree.setName(grasslandEcologyType.getName());
            ztree.setTitle(grasslandEcologyType.getName());
            ztree.setSystemType(grasslandEcologyType.getSystemType());
            ztree.setYear(grasslandEcologyType.getValue());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
