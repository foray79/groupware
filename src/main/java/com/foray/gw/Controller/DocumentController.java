package com.foray.gw.Controller;

import com.foray.gw.Dto.ApprovalDto;
import com.foray.gw.Dto.ApprovalVo;
import com.foray.gw.Dto.DocumentDto;
import com.foray.gw.Entity.*;
import com.foray.gw.Enum.ApprovalType;
import com.foray.gw.Enum.DocuType;
import com.foray.gw.Service.ApprovalService;
import com.foray.gw.Service.DocumentService;
import com.foray.gw.Service.FileService;
import com.foray.gw.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value={"/doc","/document"})
public class DocumentController {
    @Autowired
    private DocumentService service;
    @Autowired
    private UserService userService;
    @Autowired
    FileService fileService;
    @Autowired
    ApprovalService approvalService;

    private String Session_id;
    private String Session_name;

    void init() {
        System.out.println("init");
        this.Session_id = "foray";
        this.Session_name = "나병연";
    }
    @GetMapping(value={"","/","/index"})
    public String index() {
        return "redirect:/document/list?page=1";
    }

    @PostMapping("/register")
    public String register(DocumentDto documentDto, ApprovalVo approvalVo) {
        List<ApprovalDto> approvalDtoList = approvalService.makeApprovalDto(documentDto.getIdx(), approvalVo);

        service.add(documentDto);//문서저장
        for(ApprovalDto approvalDto : approvalDtoList) {
            approvalService.add(approvalDto);
        }

        return "redirect:/document/list?page=1";
    }

    @GetMapping("/write")
    public String write(Model model) {
        this.init();

        HashMap<DocuType, String> docType = this.getDocuType();
        HashMap<ApprovalType, String> approvalType = getApprovalType();

        String jsonApprovalType = approvalService.toJson(approvalType);

        DocumentEntity document = new DocumentEntity();
        /*기본값 설정 - 타임리프 오류방지-*/
        document.setDocuType(DocuType.INTER_GENERAL);
        document.setIdx(null);
        /*결재선*/
        List<UserEntity> user =userService.All();
        
        model.addAttribute("approval", null);
        model.addAttribute("docType", docType); //문서타입
        model.addAttribute("jsonApprovalType",jsonApprovalType);
        model.addAttribute("document",document); //결재선
        model.addAttribute("user",user); //결재선
        model.addAttribute("jsonApprovalList", null); //결재선
        model.addAttribute("ListCount",-1); //결재선 등록 버튼
        model.addAttribute("Session_id", Session_id); //등록자ID
        model.addAttribute("Session_name", Session_name); //등록자 이름
        model.addAttribute("mode","write");
        return "approval/document/write";
    }
    @PostMapping("modify")
    public String modify(DocumentDto documentDto, ApprovalVo approvalVo ) {//List<ApprovalDto> approvalDtos


        System.out.println("approvalVo.toString() = " + approvalVo.toString());
        System.out.println("documentDto.toString() = " + documentDto.toString());
        List<ApprovalDto> approvalDtoList = approvalService.makeApprovalDto(documentDto.getIdx(), approvalVo);

        System.out.println("approvalDtoList = " + approvalDtoList.toString());

        service.edit(documentDto);
        for(ApprovalDto approvalDto : approvalDtoList) {
            System.out.println("approvalDto = " + approvalDto.toString());
            approvalService.add(approvalDto);
        }
        approvalService.del(approvalVo);
        return "redirect:/document/list?page=1";
    }
    public HashMap<DocuType, String> getDocuType()
    {
        HashMap<DocuType, String> docType = new HashMap();

        for (DocuType type : DocuType.values()) {
            docType.put(type, type.getValue());
        }
        return docType;
    }
    public  HashMap<ApprovalType, String> getApprovalType()
    {
        HashMap<ApprovalType, String> approvalType = new HashMap();

        for (ApprovalType type : ApprovalType.values()) {
            approvalType.put(type, type.getValue());
        }
        return approvalType;
    }
    @GetMapping("test")
    public String test(Model model,@RequestParam(value = "page", required = true, defaultValue = "0") int page)
    {

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
        model.addAttribute("paging", pageVo);
        model.addAttribute("DocumentEntity", document);
/*
        System.out.println("document = " + document.toString());
  */
        return "approval/sign/test";
    }
    @PostMapping("tet_save")
    public void saveeee(ApprovalVo approvalVo){
        Long idx = Long.valueOf(1);
        List<ApprovalDto> approvalDtoList = approvalService.makeApprovalDto(idx, approvalVo);
        //System.out.println("List<approvalDto> = " + approvalDtoList.toString());

        for(ApprovalDto approvalDto : approvalDtoList){
            System.out.println("approvalDto : "+approvalDto.toString());
        }
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "idx", required = true, defaultValue = "1") long idx, Model model){
        this.init();

        DocumentEntity document = service.get(idx);

        HashMap<DocuType, String> docType = this.getDocuType();
        HashMap<ApprovalType, String> approvalType = getApprovalType();
        String jsonApprovalType = approvalService.toJson(approvalType);
        List<ApprovalEntity> approvalList = document.getApproval();


        /*결재선*/
        String jsonApprovalList = approvalService.toJson(approvalList);

        List<UserEntity> user =userService.All();
        System.out.println("approvalList = " + approvalList.toString());

        model.addAttribute("approval", null);
        model.addAttribute("approvalList", approvalList);
        model.addAttribute("docType", docType); //문서타입
        model.addAttribute("jsonApprovalType",jsonApprovalType); //결재선타입
        model.addAttribute("document",document); //결재선
        model.addAttribute("ListCount",-1); //결재선 등록 버튼
        model.addAttribute("user",user); //결재선
        model.addAttribute("Session_id", Session_id); //등록자ID
        model.addAttribute("Session_name", Session_name); //등록자 이름
        model.addAttribute("jsonApprovalList", jsonApprovalList); //결재선
        model.addAttribute("mode","edit");
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
    public String view(@RequestParam(value = "idx", required = true, defaultValue = "1") long idx,
                       @RequestParam(value = "mode", required = false, defaultValue = "view") String mode, Model model)
    {
        //https://all-record.tistory.com/148
        DocumentEntity document = service.get(idx);

        List<ApprovalEntity> approvalList = document.getApproval();
        HashMap<ApprovalType, String> approvalType = getApprovalType();
        List<ApprovalEntity> ArrApprovalList = new ArrayList<>();
        List<ApprovalEntity> ArrRefernceList = new ArrayList<>();

        String jsonApprovalType = approvalService.toJson(approvalType);

        for(ApprovalEntity approval:approvalList){
            ApprovalType key = approval.getApprovalType();

            if(key == ApprovalType.REFERENCE){
                ArrRefernceList.add(approval);
            }else {
                ArrApprovalList.add(approval);
            }

        }
        int ListCount = approvalList.size();

        /*결재선*/
        List<UserEntity> user =userService.All();
        /*결재선 json으로*/
        String jsonApprovalList = approvalService.toJson(approvalList);

        model.addAttribute("jsonApprovalType", jsonApprovalType);
        model.addAttribute("jsonApprovalList", jsonApprovalList);

        model.addAttribute("user", user);
        model.addAttribute("ApprovalList", ArrApprovalList);
        model.addAttribute("RefernceList", ArrRefernceList);
        model.addAttribute("ListCount",ListCount);
        model.addAttribute("document", document);

        if(! mode.equals("view")) mode="cap";
        model.addAttribute("mode", mode);
        model.addAttribute("cn","\n");
        model.addAttribute("br", "<br />");

        model.addAttribute("lt","<");
        model.addAttribute("llt", "&lt;");
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
