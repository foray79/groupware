package com.foray.gw.Controller;

import com.foray.gw.Entity.ApprovalEntity;
import com.foray.gw.Entity.DocuType;
import com.foray.gw.Entity.DocumentEntity;
import com.foray.gw.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentService service;

    @GetMapping("/write")
    public String wirte(Model model)
    {
        HashMap<DocuType,String> docType = new HashMap();

        for ( DocuType type : DocuType.values()) {
            docType.put( type, type.name());
        }

        model.addAttribute("docType",docType);
        return "approval/document/write";
    }
    @GetMapping("view")
    public String view(@RequestParam(value="idx",required = true,defaultValue = "1") long idx,Model model)
    {
        System.out.println("idx : " +idx);
      /*
        DocumentEntity document = service.get(idx);

        List<ApprovalEntity> approval = document.getApproval();

        model.addAttribute("approval",approval);
        System.out.println("approval = " + approval.toString());
       */
        return "approval/sign/list";
    }
}
