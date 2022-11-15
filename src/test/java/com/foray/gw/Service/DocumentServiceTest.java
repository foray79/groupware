package com.foray.gw.Service;

import com.foray.gw.Dto.DocumentDto;
import com.foray.gw.Entity.ApprovalEntity;
import com.foray.gw.Entity.DocuType;
import com.foray.gw.Entity.DocumentEntity;
import com.foray.gw.Entity.PageVo;
import com.foray.gw.Repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

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

    DocumentEntity getdata(int no)
    {
        DocumentEntity document = new DocumentEntity();
        document.setOringCode("self");
        document.setTitle("title Test_"+no);
        document.setContent("페이징 연습입니다... "+no);
        document.setReceiver("");
        document.setReceiverId("");
        document.setFile("");
        document.setWriter("나병연");
        document.setWriterId("foray");
        document.setDocuType(DocuType.INTER_GENERAL);
        document.setApproval(null);
        //document.setRefSite("");
        return document;
    }
    @Test
    @Rollback(value = false)
    void add()
    {
       // DocumentEntity document = this.getdata(1);
      //  service.add(document);
        /*String documentCode = service.makeCode();
        document.setDocNo(documentCode);
        documentRepository.save(document);*/
    }

   // @Test
    DocumentEntity get()
    {
        Long idx  =2l;

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
    void list()
    {
        PageVo pageVo = new PageVo();
        pageVo.setLimit(10);
        pageVo.setStart(1);

        Page<DocumentEntity> page = service.List(pageVo);

        System.out.println(page.getContent().toString());
    }
    @Test
    void set()
    {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setContent("aad");
        documentDto.setWriter("나병연");
        documentDto.setWriter_id("foray");
        documentDto.setType("INTER_GENERAL");
        documentDto.setTitle("test1");
        documentDto.setIdx(null);

/*
        DocumentEntity document = null;
        document = documentDto.Trans();

        String documentCode = this.makeCode();

        document.setDocNo(documentCode);
        System.out.println("document.toString() = " + document.toString());
      //  documentRepository.save(document);
          */

    }
    @Test
    void test()
    {
        DocumentEntity document = this.get();
        List<ApprovalEntity> approval = document.getApproval();

        System.out.println("document = " + document.toString());
        System.out.println("docuType="+document.getDocuType().getValue());
       // System.out.println("approval.toString() = " + approval.toString());
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
/*
public class EnumValue{
    private String key;
    private String value;
    public EnumValue(DocuType docuType){
        key = docuType.getKey();
        value = docuType.getValue();
    }
}
*/