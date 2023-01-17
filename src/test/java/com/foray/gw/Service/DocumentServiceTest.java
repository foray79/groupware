package com.foray.gw.Service;

import com.foray.gw.Dto.ApprovalDto;
import com.foray.gw.Dto.ApprovalVo;
import com.foray.gw.Dto.DocumentDto;
import com.foray.gw.Entity.ApprovalEntity;
import com.foray.gw.Entity.UserEntity;
import com.foray.gw.Enum.ApprovalType;
import com.foray.gw.Enum.DocuType;
import com.foray.gw.Entity.DocumentEntity;
import com.foray.gw.Entity.PageVo;
import com.foray.gw.Repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;

@SpringBootTest
@Transactional
class DocumentServiceTest {
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    DocumentService service;
    @Autowired
    ApprovalService approvalService;

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


        DocumentDto documentDto = new DocumentDto();
        documentDto.setType("INTER_GENERAL");

        if(documentDto.getType() != null && ! documentDto.getType().isEmpty()) {
            System.out.println("documentDto.getDocuType() = " + DocuType.valueOf(documentDto.getType()));
        }else{
            System.out.println("maybe ... type is null");
        }


       // DocumentEntity document = this.getdata(1);
      //  service.add(document);
        /*String documentCode = service.makeCode();
        document.setDocNo(documentCode);
        documentRepository.save(document);*/
    }
    @Test
    void edit()
    {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setIdx(1l);
        documentDto.setTitle("title Test_12");
        documentDto.setType("INTER_GENERAL");


    }
    @Test
    DocumentEntity get(Long idx)
    {
        //Long idx  =2l;

        //Optional<DocumentEntity> docu = documentRepository.findById(idx);
        //DocumentEntity document = service.get(idx, Sort.sort(null));
        Optional<DocumentEntity> docu = documentRepository.findById(idx);
        DocumentEntity document = docu.get();
        System.out.println("document = " + document.toString());
        return document;

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
    void tojson()
    {

        HashMap<ApprovalType, String> approvalType = new HashMap();

        for (ApprovalType type : ApprovalType.values()) {
            approvalType.put(type, type.getValue());
        }
        String json = approvalService.toJson(approvalType);


        System.out.println("json = " + json);


        /*approvalType.forEach((key,value)->
                System.out.println("'"+key+"' : '"+value+"'") );*/
    }
    @Test
    public void test()
    {
        Long idx = Long.valueOf(2);
        DocumentEntity document = service.get(idx);
        List<ApprovalEntity> approvalList = document.getApproval();
        TreeMap<ApprovalType,ArrayList<ApprovalEntity>> groupApproval =  new TreeMap<>();

        List<ApprovalEntity> ArrApprovalList = new ArrayList<>();
        List<ApprovalEntity> ArrRefernceList = new ArrayList<>();

        for(ApprovalEntity approval:approvalList){
            ApprovalType key = approval.getApprovalType();
            Long userIdx = approval.getUserIdx();
            System.out.println("userIdx="+userIdx+",type="+key);

            if(key == ApprovalType.REFERENCE){
                ArrRefernceList.add(approval);
            }else {
                ArrApprovalList.add(approval);
            }
        }

        System.out.println("RefernceList = " + ArrRefernceList.toString());
        System.out.println("ApprovalList = " + ArrApprovalList.toString());

       // groupApproval.comparator();
       // System.out.println("groupApproval = " + groupApproval.toString());

    }
    ApprovalVo getApproval()
    {
        /*ApprovalVo(idx=1, delIdx=null, apprIdx=null, userIdx=[5, 1, 5], sign=[N, Y, Y], ApprovalType=[REFERENCE, APPROVAL, APPROVAL])*/


        String[] approvalTypes = {null};
        Long[] appr_idx = {null};
        Long[] user_idx = {null};
        String[] sign = {null};

        ApprovalVo approvalVo = new ApprovalVo();
        approvalVo.setApprIdx(appr_idx);
        approvalVo.setIdx(2l);
        //approvalVo.setDelIdx(del_idx);
        approvalVo.setUserIdx(user_idx);
        approvalVo.setSign(sign);
        approvalVo.setApprovalType(approvalTypes);


        return approvalVo;
    }
    DocumentDto getDocument()
    {
        DocumentDto documentDto = new DocumentDto();

        String content="1. 행정업무로 수고 많으십니다.\n" +
                "2. 다음과 같이 시행 하려고 합니다.\n" +
                "\n" +
                "<b>태그 적용 안되게</b>\n" +
                "<script>alert('스크립트 무력화');</script>";

        documentDto.setIdx(1l);
        documentDto.setTitle("title Test_1211");
        documentDto.setType(DocuType.INTER_GENERAL.getValue());
        documentDto.setWriter("나병연");
        documentDto.setWriter_id("foray");
        documentDto.setContent(content);

        return documentDto;
    }


}
