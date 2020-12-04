package com.ruoyi.system.service.impl.gis;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.gis.GisFeatures;
import com.ruoyi.system.mapper.gis.GisFeaturesMapper;
import com.ruoyi.system.service.gis.IGisFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class GisFeaturesServiceImpl implements IGisFeaturesService {
    @Autowired
    private GisFeaturesMapper gisFeaturesMapper;


    @Override
    public List<GisFeatures> selectGisFeaturesList(GisFeatures gisFeature) {
        return gisFeaturesMapper.selectShpFileList(gisFeature);
    }

    @Override
    public List<Ztree> selectGisFeaturesTree(GisFeatures gisFeatures) {
        List<GisFeatures> gisFeaturesList = gisFeaturesMapper.selectShpFileList(gisFeatures);
        List<Ztree> ztrees = initZtree(gisFeaturesList);
        return ztrees;
    }

    @Override
    public List<Ztree> roleGisFeaturesTreeData(SysRole role) {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<GisFeatures> gisFeaturesList = selectGisFeaturesList(new GisFeatures());
        if (StringUtils.isNotNull(roleId)) {
            List<String> rolegisFeaturesList = gisFeaturesMapper.selectRoleShpFileTree(roleId);
            ztrees = initZtree(gisFeaturesList, rolegisFeaturesList);
        } else {
            ztrees = initZtree(gisFeaturesList);
        }
        return ztrees;
    }

    @Override
    public int selectGisFeaturesCount(Long parentId) {
        GisFeatures shpFile = new GisFeatures();
        shpFile.setParentId(parentId);
        return gisFeaturesMapper.selectShpFileCount(shpFile);
    }

    public List<Ztree> initZtree(List<GisFeatures> gisFeaturesList) {
        return initZtree(gisFeaturesList, null);
    }


    public List<Ztree> initZtree(List<GisFeatures> gisFeaturesList, List<String> rolegisFeaturesList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (GisFeatures gisFeatures : gisFeaturesList) {
            Ztree ztree = new Ztree();
            ztree.setId(gisFeatures.getId());
            ztree.setpId(gisFeatures.getParentId());
            ztree.setName(gisFeatures.getName());
            ztree.setTitle(gisFeatures.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }


    @Override
    public int deleteGisFeaturesById(Long id) {
        return gisFeaturesMapper.deleteShpFileById(id);
    }


    @Override
    public int insertGisFeatures(GisFeatures gisFeature) {
        GisFeatures info = gisFeaturesMapper.selectShpFileById(gisFeature.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus())) {
            throw new BusinessException("GisFeatures停用，不允许新增");
        }
        gisFeature.setAncestors(info.getAncestors() + "," + gisFeature.getParentId());
        return gisFeaturesMapper.insertShpFile(gisFeature);
    }

    @Override
    @Transactional
    public int updateGisFeatures(GisFeatures gisFeature) {
        GisFeatures newParentShpFile = gisFeaturesMapper.selectShpFileById(gisFeature.getParentId());
        GisFeatures oldShpFile = selectGisFeaturesById(gisFeature.getId());
        if (StringUtils.isNotNull(newParentShpFile) && StringUtils.isNotNull(oldShpFile)) {
            String newAncestors = newParentShpFile.getAncestors() + "," + newParentShpFile.getId();
            String oldAncestors = oldShpFile.getAncestors();
            gisFeature.setAncestors(newAncestors);
            updateGisFeatureChildren(gisFeature.getId(), newAncestors, oldAncestors);
        }
        int result = gisFeaturesMapper.updateShpFile(gisFeature);
        if (UserConstants.DEPT_NORMAL.equals(gisFeature.getStatus())) {
            // 如果该shpFile是启用状态，则启用该shpFile的所有上级shpFile
            updateParentGisFeatureStatus(gisFeature);
        }
        return result;
    }


    private void updateParentGisFeatureStatus(GisFeatures gisFeature) {
        String updateBy = gisFeature.getUpdateBy();
        gisFeature = gisFeaturesMapper.selectShpFileById(gisFeature.getId());
        gisFeature.setUpdateBy(updateBy);
        gisFeaturesMapper.updateShpFileStatus(gisFeature);
    }


    public void updateGisFeatureChildren(Long shpFileId, String newAncestors, String oldAncestors) {
        List<GisFeatures> children = gisFeaturesMapper.selectChildrenShpFileById(shpFileId);
        for (GisFeatures child : children) {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0) {
            gisFeaturesMapper.updateShpFileChildren(children);
        }
    }

    @Override
    public GisFeatures selectGisFeaturesById(Long shpFileId) {
        return gisFeaturesMapper.selectShpFileById(shpFileId);
    }

    @Override
    public String checkGisFeaturesNameUnique(GisFeatures gisFeature) {
        Long shpFileId = StringUtils.isNull(gisFeature.getId()) ? -1L : gisFeature.getId();
        GisFeatures info = gisFeaturesMapper.checkShpFileNameUnique(gisFeature.getName(), gisFeature.getParentId());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != shpFileId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public void updateStstus(Long id, int status) {
        gisFeaturesMapper.updateStstus(id, status);
    }

    @Override
    public List<Ztree> getListByType() {
        List<Ztree> ztrees = initZtree(gisFeaturesMapper.getListByType());
        return ztrees;
    }
}
