package com.foray.gw.Service;

import com.foray.gw.Entity.ApprovalEntity;
import com.foray.gw.Entity.ApprovalType;
import com.foray.gw.Entity.DocumentEntity;
import com.foray.gw.Repository.ApprovalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
class ApprovalServiceTest {
    @Autowired
    ApprovalRepository approvalRepository;

    @Autowired
    DocumentService documentService;

    @Autowired
    ApprovalService service;

    ApprovalEntity getdata()
    {
        ApprovalEntity approval = new ApprovalEntity();

        DocumentEntity document = documentService.get(1l);

        approval.setApprovalType(ApprovalType.APPROVAL);
        approval.setName("나병연");
        approval.setSign("N");
        approval.setSignDate(null);
        approval.setSorty(1);
        approval.setWriteDate(new Date());
        approval.setDocument(document);

        return approval;
    }
    @Test
    public void add()
    {
        ApprovalEntity approval = this.getdata();
        System.out.println("approval = " + approval.toString());
        service.add(approval);
        //approvalRepository.save(approval);
    }

}