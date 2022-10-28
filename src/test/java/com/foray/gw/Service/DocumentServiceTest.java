package com.foray.gw.Service;

import com.foray.gw.Entity.ApprovalEntity;
import com.foray.gw.Entity.DocuType;
import com.foray.gw.Entity.DocumentEntity;
import com.foray.gw.Repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.DoubleStream;

@SpringBootTest
@Transactional
class DocumentServiceTest {
@Autowired
    DocumentRepository documentRepository;
    @Autowired
    DocumentService service;

    DocumentEntity getdata()
    {
        DocumentEntity document = new DocumentEntity();
        document.setOringCode("self");
        document.setTitle("title Test");
        document.setContent("연습입니다... ");
        document.setReceive("");
        document.setFile("");
        document.setDocuType(DocuType.INTER_GENERAL);
        document.setApproval(null);
        //document.setRefSite("");
        return document;
    }
    @Test
    void add()
    {
        DocumentEntity document = this.getdata();
        service.add(document);
        /*String documentCode = service.makeCode();
        document.setDocNo(documentCode);
        documentRepository.save(document);*/
    }

   // @Test
    DocumentEntity get()
    {
        Long idx  =1l;

        //Optional<DocumentEntity> docu = documentRepository.findById(idx);
        DocumentEntity document = service.get(idx);

        return document;
        /*
        List<ApprovalEntity> approval = document.getApproval();

        System.out.println("document = " + document.toString());
        System.out.println("approval.toString() = " + approval.toString());

         */
    }

    @Test
    void test()
    {
        DocumentEntity document = this.get();
        List<ApprovalEntity> approval = document.getApproval();

        System.out.println("document = " + document.toString());
        System.out.println("approval.toString() = " + approval.toString());
    /*
        String dept_code = "CM01";
        System.out.println("seed = " + seed);
        Random rand = new Random(seed);
        String code="";
        String ccode="";
        for (int i=0;i<10;i++) {
            double dValue = Math.random();
            char c = (char)((dValue * 26) + 65);// 대문자
            ccode += c;
            int r = rand.nextInt(10);
            code += r;
        }
        System.out.println("code = " + dept_code+"_"+code);
        System.out.println("code = " + dept_code+"_"+ccode);
    */
    }
}