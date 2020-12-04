package com.ruoyi.zaihai.workflow.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.zaihai.common.utils.GenerateUUID;
import com.ruoyi.zaihai.workflow.domain.FlowIdea;
import com.ruoyi.zaihai.workflow.mapper.FlowIdeaMapper;
import com.ruoyi.zaihai.workflow.service.IFlowIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程意见Service业务层处理
 * 
 * @author sudogndong
 * @date 2020-07-10
 */
@Service
public class FlowIdeaServiceImpl implements IFlowIdeaService 
{
    @Autowired
    private FlowIdeaMapper flowIdeaMapper;

    /**
     * 查询流程意见
     * 
     * @param id 流程意见ID
     * @return 流程意见
     */
    @Override
    public FlowIdea selectFlowIdeaById(String id)
    {
        return flowIdeaMapper.selectFlowIdeaById(id);
    }

    /**
     * 查询流程意见列表
     * 
     * @param flowIdea 流程意见
     * @return 流程意见
     */
    @Override
    public List<FlowIdea> selectFlowIdeaList(FlowIdea flowIdea)
    {
        return flowIdeaMapper.selectFlowIdeaList(flowIdea);
    }

    /**
     * 新增流程意见
     * 
     * @param flowIdea 流程意见
     * @return 结果
     */
    @Override
    public int insertFlowIdea(FlowIdea flowIdea)
    {
        SysUser user=ShiroUtils.getSysUser();
        flowIdea.setId(GenerateUUID.crUid());
        flowIdea.setIdeatime(DateUtils.getNowDate());
        flowIdea.setDeptid(String.valueOf(user.getDeptId()));
        flowIdea.setUserid(String.valueOf(user.getUserId()));
        flowIdea.setUsername(user.getUserName());
        return flowIdeaMapper.insertFlowIdea(flowIdea);
    }

    /**
     * 修改流程意见
     * 
     * @param flowIdea 流程意见
     * @return 结果
     */
    @Override
    public int updateFlowIdea(FlowIdea flowIdea)
    {
        return flowIdeaMapper.updateFlowIdea(flowIdea);
    }

    /**
     * 删除流程意见对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFlowIdeaByIds(String ids)
    {
        return flowIdeaMapper.deleteFlowIdeaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流程意见信息
     * 
     * @param id 流程意见ID
     * @return 结果
     */
    @Override
    public int deleteFlowIdeaById(String id)
    {
        return flowIdeaMapper.deleteFlowIdeaById(id);
    }
}
