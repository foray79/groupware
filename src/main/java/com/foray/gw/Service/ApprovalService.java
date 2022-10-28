package com.foray.gw.Service;

import com.foray.gw.Entity.ApprovalEntity;
import com.foray.gw.Repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class ApprovalService {

    @Autowired
    ApprovalRepository approvalRepository;

    public void add(ApprovalEntity approval)
    {
        approvalRepository.save(approval);
    }
}
