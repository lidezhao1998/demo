package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.TTaskRecord;
import com.ruoyi.system.domain.TTaskResolve;
import com.ruoyi.system.mapper.TTaskResolveRecordMapper;
import com.ruoyi.system.service.ITTaskResolveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 退牧还草工程任务调整记录Service业务层处理
 * 
 * @author liuhongfei
 * @date 2020-09-22
 */
@Service
public class TTaskResolveRecordServiceImpl implements ITTaskResolveRecordService
{
    @Autowired
    private TTaskResolveRecordMapper tTaskRecordMapper;

    /**
     * 查询退牧还草工程任务调整记录
     * 
     * @param recordId 退牧还草工程任务调整记录ID
     * @return 退牧还草工程任务调整记录
     */
    @Override
    public TTaskRecord selectTTaskRecordById(Long recordId)
    {
        return tTaskRecordMapper.selectTTaskRecordById(recordId);
    }

    /**
     * 查询退牧还草工程任务调整记录列表
     * 
     * @param tTaskRecord 退牧还草工程任务调整记录
     * @return 退牧还草工程任务调整记录
     */
    @Override
    public List<TTaskRecord> selectTTaskRecordList(TTaskRecord tTaskRecord) {
        //获取登录用户的部门
        String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
        //获取角色
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        String roleNames="";
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            roleNames += sysRole.getRoleName()+",";
        }
        if(!roleNames.contains("超级管理员")){
            if (roleNames.contains("市级")) {
                tTaskRecord.setProvince(deptName);
            } else if (roleNames.contains("省级")) {
                tTaskRecord.setProvince(deptName);
            }
        }
        List<TTaskRecord> list = tTaskRecordMapper.selectTTaskRecordList(tTaskRecord);
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < list.size(); i++) {
            TTaskRecord Publish = list.get(i);
            /** 拼接任务地区 */
            String province = Publish.getProvince();
            String year = Publish.getYear();
            String address = Publish.getAddress();
            Publish.setAddress(year + "_" + province + "_" + address);
            /** 围栏规模合计 */
            Double wlmjCount = Publish.getWlScale();
            Publish.setWlScale(Double.parseDouble(df.format(wlmjCount)));
            /** 围栏投资合计 */
            Double wljeCount = Publish.getWlInvestment();
            Publish.setWlInvestment(Double.parseDouble(df.format(wljeCount)));
            /** 其他规模合计 */
            Double thzyglSize = Publish.getThzyglSize();
            Double rgscdSize = Publish.getRgscdSize();
            Double hspjSize = Publish.getHspjSize();
            Double httSize = Publish.getHttSize();
            Double dhcSize = Publish.getDhcSize();
            Double qtCount = thzyglSize + rgscdSize + hspjSize + httSize + dhcSize;
            Publish.setQtmjCount(Double.parseDouble(df.format(qtCount)));
            /** 其金额合计 */
            Double thzyglMoney = Publish.getThzyglMoney();
            Double rgscdMoney = Publish.getRgscdMoney();
            Double hspjMoney = Publish.getHspjMoney();
            Double httMoney = Publish.getHttMoney();
            Double dhcMoney = Publish.getDhcMoney();
            Double qtjeCount = thzyglMoney + rgscdMoney + hspjMoney + httMoney + dhcMoney;
            Publish.setQtjeCount(Double.parseDouble(df.format(qtjeCount)));
            /** 总规模合计 */
            Double zgmCount = Publish.getQtmjCount() + Publish.getWlScale();
            Publish.setZgmCount(Double.parseDouble(df.format(zgmCount)));
            /** 总金额 */
            Double zjeCount = qtjeCount + wljeCount;
            Publish.setZjeCount(Double.parseDouble(df.format(zjeCount)));
        }
        return list;
    }

    /**
     * 新增退牧还草工程任务调整记录
     * 
     * @param tTaskResolve 退牧还草工程任务调整记录
     * @return 结果
     */
    @Override
    public int insertTTaskRecord(TTaskResolve tTaskResolve)
    {
        tTaskResolve.setCreateTime(DateUtils.getNowDate());
        return tTaskRecordMapper.insertTTaskRecord(tTaskResolve);
    }

    /**
     * 修改退牧还草工程任务调整记录
     * 
     * @param tTaskRecord 退牧还草工程任务调整记录
     * @return 结果
     */
    @Override
    public int updateTTaskRecord(TTaskRecord tTaskRecord)
    {
        tTaskRecord.setUpdateTime(DateUtils.getNowDate());
        return tTaskRecordMapper.updateTTaskRecord(tTaskRecord);
    }

    /**
     * 删除退牧还草工程任务调整记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTTaskRecordByIds(String ids)
    {
        return tTaskRecordMapper.deleteTTaskRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退牧还草工程任务调整记录信息
     * 
     * @param recordId 退牧还草工程任务调整记录ID
     * @return 结果
     */
    @Override
    public int deleteTTaskRecordById(Long recordId)
    {
        return tTaskRecordMapper.deleteTTaskRecordById(recordId);
    }

}
