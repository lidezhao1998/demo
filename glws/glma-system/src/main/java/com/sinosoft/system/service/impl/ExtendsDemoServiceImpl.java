package com.sinosoft.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import com.sinosoft.system.domain.ExtendsDemo;
import com.sinosoft.system.mapper.ExtendsDemoMapper;
import com.sinosoft.system.service.ExtendsDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author  lhf
 * date  2020/10/21 20:23
 * version 1.0
 */
@Service("testService")
public class ExtendsDemoServiceImpl implements ExtendsDemoService {

    @Autowired
    ExtendsDemoMapper extendsDemoMapper;

    @DataScope(deptAlias = "d", userAlias = "u")
    public List<ExtendsDemo> selectUserList(ExtendsDemo extendsDemo) {
        return extendsDemoMapper.selectExtendsDemoList(extendsDemo);
    }
}
