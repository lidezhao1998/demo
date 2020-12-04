package com.sinosoft.integration.mapper;

import com.sinosoft.integration.domain.RecoveryQuestion;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 9:52
 * 返青期调查
 */
public interface RecoveryQuestionMapper {
    /**
     * 查询返青期调查详细数据
     * @param recoveryQuestion
     * @return
     */
    RecoveryQuestion selectRecoveryQuestion(RecoveryQuestion recoveryQuestion);

    /**
     *查询返青期调列表
     * @param recoveryQuestion
     * @return
     */
    List<RecoveryQuestion> selectRecoveryQuestionList(RecoveryQuestion recoveryQuestion);
    /**
     * 修改返青期数据
     */
    int updateRecoveryQuestion(RecoveryQuestion recoveryQuestion);

    /**
     * 新增返青期调查数据
     * @param recoveryQuestion
     */
    int insertRecoveryQuestion(RecoveryQuestion recoveryQuestion);

    /**
     * 删除
     * @param ids
     * @return
     */
    int deleteRecoveryQuestions(String[] ids);

    /**
     *查询返青期调列表
     * @param recoveryQuestion
     * @return
     */
    List<RecoveryQuestion> selectRecoveryQuestionListOrderBy(RecoveryQuestion recoveryQuestion);

    int updateStatusById(RecoveryQuestion recoveryQuestion);

    int updateStatusByIds(String[] ids);

}
