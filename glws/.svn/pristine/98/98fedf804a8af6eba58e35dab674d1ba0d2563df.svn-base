package com.sinosoft.common.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.gis.GisFeatures;
import com.sinosoft.common.mapper.CommonMapper;
import com.sinosoft.common.service.CommonService;
import com.sinosoft.common.service.SharedServiceEnum;
import com.sinosoft.extinterface.domain.LandSurvey;
import com.sinosoft.extinterface.service.LandSurveyService;
import com.sinosoft.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunlei
 * @description glma 公共方法
 * @date 2020/11/10/18:00
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    CommonMapper commonMapper;
    @Autowired
    LandSurveyService landSurveyService;

    @Override
    public List<Ztree> selectFeaturesTree() {
        List<GisFeatures> featuresList = commonMapper.selectFeaturesTree();
        List<Ztree> ztrees = initZtree(featuresList, null);
        return ztrees;
    }

    @Override
    public String sharedData(String id, String treeId, String treePid) {
        //获取信息
        LandSurvey landSurvey = landSurveyService.selectLandSurvey(Long.parseLong(id));
        //组装消息体
        String result = SharedServiceEnum.valueOf("sys_" + treePid).sharingMethod(id, treeId, landSurvey);
        //发送
        String post = HttpClientUtils.post(result);
        return post;
    }


    public List<Ztree> initZtree(List<GisFeatures> list, List<String> roleDeptList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (GisFeatures features : list) {
            if (UserConstants.DEPT_NORMAL.equals(features.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(features.getId());
                ztree.setpId(features.getParentId());
                ztree.setName(features.getName());
                ztree.setTitle(features.getName());
                if (isCheck) {
                    ztree.setChecked(roleDeptList.contains(features.getId() + features.getName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

}
