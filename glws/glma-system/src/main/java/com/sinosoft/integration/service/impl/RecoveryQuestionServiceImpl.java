package com.sinosoft.integration.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.sinosoft.integration.domain.RecoveryQuestion;
import com.sinosoft.integration.mapper.RecoveryQuestionMapper;
import com.sinosoft.integration.service.RecoveryQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 11:05
 */
@Service
public class RecoveryQuestionServiceImpl implements RecoveryQuestionService {
    @Autowired
    RecoveryQuestionMapper recoveryQuestionMapper;

    @Override
    public RecoveryQuestion selectRecoveryQuestion(RecoveryQuestion recoveryQuestion) {
        return recoveryQuestionMapper.selectRecoveryQuestion(recoveryQuestion);
    }

    @Override
    public List<RecoveryQuestion> selectRecoveryQuestionList(RecoveryQuestion recoveryQuestion) {
        //国家级只查看已审核的数据
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            if (roleName.equals("国家级")) {
                recoveryQuestion.setStatus(1);
            }
        }

        return recoveryQuestionMapper.selectRecoveryQuestionList(recoveryQuestion);
    }

    @Override
    public int updateRecoveryQuestion(RecoveryQuestion recoveryQuestion) {
        return recoveryQuestionMapper.updateRecoveryQuestion(recoveryQuestion);
    }

    @Override
    public int insertRecoveryQuestion(RecoveryQuestion recoveryQuestion) {
        return recoveryQuestionMapper.insertRecoveryQuestion(recoveryQuestion);
    }

    @Override
    public int deleteRecoveryQuestions(String ids) {
        return recoveryQuestionMapper.deleteRecoveryQuestions(Convert.toStrArray(ids));
    }

    @Override
    public List<RecoveryQuestion> selectRecoveryQuestionListOrderBy(RecoveryQuestion recoveryQuestion) {
        return recoveryQuestionMapper.selectRecoveryQuestionListOrderBy(recoveryQuestion);
    }

    @Override
    public int updateStatusById(RecoveryQuestion recoveryQuestion) {
        return recoveryQuestionMapper.updateStatusById(recoveryQuestion);
    }

    @Override
    public int updateStatusByIds(String[] ids) {
        return recoveryQuestionMapper.updateStatusByIds(ids);
    }
}
