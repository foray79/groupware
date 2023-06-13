package com.foray.gw.Service;

import com.foray.gw.Dto.DocumentDto;
import com.foray.gw.Entity.ApprovalEntity;
import com.foray.gw.Enum.ApprovalType;
import com.foray.gw.Enum.DocuType;
import com.foray.gw.Entity.DocumentEntity;
import com.foray.gw.Entity.PageVo;
import com.foray.gw.Repository.DocumentRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class DocumentService {
    @Autowired
    DocumentRepository documentRepository;

    public String makeCode()
    {
        long seed = System.currentTimeMillis();
        String dept_code = "CM01";
        Random rand = new Random(seed);
        String documentCode="";
        for (int i=0;i<10;i++) {
            int r = rand.nextInt(10);
            documentCode += r;
        }
       /// documentCode = dept_code+"_"+documentCode;
       // document.setDocNo(documentCode);
        return documentCode;
        /*
            double dValue = Math.random();
            char c = (char)((dValue * 26) + 65);// 대문자
            ccode += c;
        */
    }

    public void add(DocumentDto documentDto)
    {
        DocumentEntity document = null;
        document = DocumentDto.Trans(documentDto);
        String documentCode = this.makeCode();
        document.setDocNo(documentCode);
       documentRepository.save(document);
    }
    public void edit(DocumentDto documentDto)
    {
        DocumentEntity document = this.get(documentDto.getIdx());

        document.setTitle(documentDto.getTitle());
        if(documentDto.getType() != null && ! documentDto.getType().isEmpty()) {
            document.setDocuType(DocuType.valueOf(documentDto.getType()));
        }
        document.setContent(documentDto.getContent());
        //document.setReceiverId();
        //document.setReceiver();
    }

    public DocumentEntity get(Long idx) //DocumentEntity
    {
        Optional<DocumentEntity> docu = documentRepository.findById(idx);
        DocumentEntity document = docu.get();

        return document;
    }
    public Page<DocumentEntity> List(PageVo pageVo)
    {
        int limit = pageVo.getLimit();
        int start = pageVo.getStart();

        PageRequest pageRequest = PageRequest.of(start,limit, Sort.by(Sort.Direction.ASC,"idx"));

        Page<DocumentEntity> page = documentRepository.findAll(pageRequest);

        return page;
    }

}
