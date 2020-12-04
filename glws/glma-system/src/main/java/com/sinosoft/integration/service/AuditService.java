package com.sinosoft.integration.service;

import com.sinosoft.integration.domain.Audit;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 10:49
 */
public interface AuditService {
    /**
     * 查询审核信息
     * @param audit
     * @return
     */
    Audit selectAudit(Audit audit);

    /**
     * 查询审核列表
     * @param audit
     * @return
     */
    List<Audit> selectAuditList(Audit audit);
}
