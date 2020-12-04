package com.sinosoft.integration.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.integration.domain.NormalInspection;
import com.sinosoft.integration.mapper.NormalInspectionMapper;
import com.sinosoft.integration.service.NormalInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 10:56
 */
@Service
public class NormalInspectionServiceImpl implements NormalInspectionService {
    @Autowired
    NormalInspectionMapper normalInspectionMapper;
    @Override
    public NormalInspection selectNormalInspection(NormalInspection normalInspection) {
        return normalInspectionMapper.selectNormalInspection(normalInspection);
    }

    @Override
    public List<NormalInspection> selectNormalInspectionList(NormalInspection normalInspection) {
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("国家级")) {
                normalInspection.setStatus(1);
            }
        }
        return normalInspectionMapper.selectNormalInspectionList(normalInspection);
    }

    @Override
    public int updateNormalInspection(NormalInspection normalInspection) {
        return normalInspectionMapper.updateNormalInspection(normalInspection);
    }

    @Override
    public int insertNormalInspection(NormalInspection normalInspection) {
        return normalInspectionMapper.insertNormalInspection(normalInspection);
    }

    @Override
    public int deleteNormalInspectionByIds(String ids) {
        return normalInspectionMapper.deleteNormalInspectionByIds(Convert.toStrArray(ids));
    }

    @Override
    public int updateStatusByIds(String[] ids) {
        return normalInspectionMapper.updateStatusByIds(ids);
    }
}
