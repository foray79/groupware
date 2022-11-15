package com.foray.gw.Controller;

import com.foray.gw.Dto.DocumentDto;
import com.foray.gw.Dto.UserDto;
import com.foray.gw.Entity.*;
import com.foray.gw.Service.DocumentService;
import com.foray.gw.Service.FileService;
import com.foray.gw.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentService service;
    @Autowired
    private UserService userService;
    @Autowired
    FileService fileService;

    private String Session_id;
    private String Session_name;

    void init() {
        System.out.println("init");
        this.Session_id = "foray";
        this.Session_name = "나병연";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/document/list?page=1";
    }

    @PostMapping("/register")
    public String register(DocumentDto documentDto, UserDto userDto) {
        System.out.println("userDto = " + userDto.toString());
        System.out.println("documentDto = " + documentDto.toString());
        System.exit(0);
        //service.add(documentDto);

        return "redirect:/document/list?page=1";
    }

    @GetMapping("/write")
    public String wirte(Model model) {
        this.init();
        HashMap<DocuType, String> docType = new HashMap();

        for (DocuType type : DocuType.values()) {
            docType.put(type, type.getValue());
        }
        /*결재선*/
        List<UserEntity> user =userService.All();
        
        model.addAttribute("approval", null);
        model.addAttribute("docType", docType); //문서타입

        model.addAttribute("user",user); //결재선
        model.addAttribute("Session_id", Session_id); //등록자ID
        model.addAttribute("Session_name", Session_name); //등록자 이름
        return "approval/document/write";
    }
/*
    @PostMapping("upload")
    public ResponseEntity<> uploadFile(@RequestParam("file")MultipartFile uploadfile) {
        if (uploadfile.isEmpty()) {
            return new ResponseEntity("파일을 선택하세요", HttpStatus.OK);
        }
        try {
            fileService.saveUploadFiles(Arrays.asList(uploadfile));
        } catch (IOException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("업로드 성공- "+uploadfile.getOriginalFilename(), new HttpHeaders(),HttpStatus.OK);
    }
 */
    @GetMapping("view")
    public String view(@RequestParam(value = "idx", required = true, defaultValue = "1") long idx, Model model) {
        System.out.println("idx : " + idx);

        DocumentEntity document = service.get(idx);

        List<ApprovalEntity> approval = document.getApproval();

        model.addAttribute("approval", approval);
        model.addAttribute("document", document);
        System.out.println("document = " + document.toString());
        System.out.println("approval=" + approval.toString());
        return "approval/document/view";
    }

    @GetMapping("list")

    public String list(@RequestParam(value = "page", required = true, defaultValue = "0") int page, Model model) {
        if (page < 1) {
            return "redirect:/document/list?page=1";
        } else {
            System.out.println("page:" + page);
        }
        int start = page - 1;
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setLimit(10);
        pageVo.setStart(start);
        pageVo.setSearchText("");

        Page<DocumentEntity> pager = service.List(pageVo);
        List<DocumentEntity> document = pager.getContent();

        int totalPage = pager.getTotalPages();
        pageVo.setTotalCount(totalPage);

        pageVo.calc();
        //  ApprovalEntity approval= null;
        System.out.println("pageVo=" + pageVo.toString());
        System.out.println("DocumentEntity=" + document.toString());
        model.addAttribute("paging", pageVo);
        model.addAttribute("DocumentEntity", document);

        return "approval/document/list";
    }

    @GetMapping("sign")
    public String sign(Model model)
    {
        PageVo pageVo = new PageVo();
        pageVo.setStart(0);
        pageVo.setLimit(100);
        Page<UserEntity> page = userService.List(pageVo);

        List<UserEntity> user = page.getContent();
        model.addAttribute("approval", null);
        model.addAttribute("user",user);
        return "approval/sign/list";
    }

}
