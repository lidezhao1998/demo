package com.sinosoft.integration.service.impl;

import com.sinosoft.integration.domain.Audit;
import com.sinosoft.integration.mapper.AuditMapper;
import com.sinosoft.integration.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhf
 * @version 1.0
 * @date 2020/6/4 10:53
 */
@Service
public class AuditServiceImpl implements AuditService {
    @Autowired
    AuditMapper auditMapper;

    @Override
    public Audit selectAudit(Audit audit) {
        return auditMapper.selectAudit(audit);
    }

    @Override
    public List<Audit> selectAuditList(Audit audit) {
        return auditMapper.selectAuditList(audit);
    }
}
