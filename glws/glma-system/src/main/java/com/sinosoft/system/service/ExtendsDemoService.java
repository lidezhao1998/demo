package com.sinosoft.system.service;

import com.ruoyi.system.service.ISysUserService;
import com.sinosoft.system.domain.ExtendsDemo;
import com.sinosoft.system.domain.TTaskReport;

import java.util.List;

/**
 * 退牧还草工程进度上报Service接口
 * 
 * @author LiuHongfei
 * @date 2019-11-29
 */
public interface ExtendsDemoService
{
    List<ExtendsDemo> selectUserList(ExtendsDemo extendsDemo);
}
