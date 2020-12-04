package com.sinosoft.integration.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.integration.domain.NprojectSampleData;
import com.sinosoft.integration.mapper.NprojectSampleDataMapper;
import com.sinosoft.integration.service.NprojectSampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 10:58
 */
@Service
public class NprojectSampleDataServiceImpl implements NprojectSampleDataService {

    @Autowired
    NprojectSampleDataMapper nprojectSampleDataMapper;
    @Override
    public NprojectSampleData selectNprojectSampleData(NprojectSampleData nprojectSampleData) {
        return nprojectSampleDataMapper.selectNprojectSampleData(nprojectSampleData);
    }

    @Override
    public List<NprojectSampleData> selectNprojectSampleDataList(NprojectSampleData nprojectSampleData) {
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("国家级")) {
                nprojectSampleData.setStatus(1);
            }
        }
        return nprojectSampleDataMapper.selectNprojectSampleDataList(nprojectSampleData);
    }

    @Override
    public int updateNprojectSampleData(NprojectSampleData nprojectSampleData) {

        return nprojectSampleDataMapper.updateNprojectSampleData(nprojectSampleData);
    }

    @Override
    public int insertNprojectSampleData(NprojectSampleData nprojectSampleData) {
        return nprojectSampleDataMapper.insertNprojectSampleData(nprojectSampleData);
    }

    @Override
    public int deleteNprojectSampleDataByIds(String ids) {
        return nprojectSampleDataMapper.deleteNprojectSampleDataByIds(Convert.toStrArray(ids));
    }

    @Override
    public int updateStatusByIds(String[] ids) {
        return nprojectSampleDataMapper.updateStatusByIds(ids);
    }
}
