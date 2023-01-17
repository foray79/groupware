package com.foray.gw.Controller;

import com.foray.gw.Contains.DeptCode;
import com.foray.gw.Contains.DeptRank;
import com.foray.gw.Contains.Postion;
import com.foray.gw.Dto.DocumentDto;
import com.foray.gw.Dto.UserDto;
import com.foray.gw.Entity.*;

import com.foray.gw.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Array;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    /*상수클래스*/
    @Autowired
    private DeptCode deptCode;
    @Autowired
    private Postion postion;
    @Autowired
    private DeptRank deptRank;

    @GetMapping("/")
    public String index()
    {
        return "redirect:/user/list?page=1";
    }

    void init() {
        System.out.println("init");
      }

    @PostMapping("/register")
    public String register(UserDto userDto)
    {
        System.out.println("userDto.toString() = " + userDto.toString());
        service.add(userDto);

        return "redirect:/user/list?page=1";
    }
    @PostMapping("/modify")
    public String modify(UserDto userDto)
    {
        System.out.println("userDto.toString() = " + userDto.toString());
        service.edit(userDto);

        return "redirect:/user/list?page=1";
    }

    @GetMapping("/write")
    public String write(Model model)
    {

        List<ContainsEntity> deptcode =  deptCode.getCode();
        List<ContainsEntity> _deptRank = deptRank.getCode();
        List<ContainsEntity> _postion = postion.getCode();

        model.addAttribute("deptCode",deptcode); //부서
        model.addAttribute("_deptRank",_deptRank); //직급
        model.addAttribute("_postion",_postion); //직책

        return "approval/user/write";
    }
    @GetMapping("view")
    public String view(@RequestParam(value="idx",required = true,defaultValue = "1") long idx, Model model)
    {
        System.out.println("idx : " +idx);
        UserEntity userEntity = service.get(idx);
        System.out.println("userEntity = " + userEntity.toString());


        List<ContainsEntity> deptcode =  deptCode.getCode();

        model.addAttribute("userEntity",userEntity);
        model.addAttribute("deptCode",deptcode); //문서타입

        return "approval/user/view";
    }

    @GetMapping("list")

    public String list(@RequestParam(value="page",required = true,defaultValue = "0") int page,Model model)
    {
        if( page<1 ){
            return "redirect:/user/list?page=1";
        }
        else {
            System.out.println("page:"+page);
        }
        int start = page-1;
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setLimit(10);
        pageVo.setStart(start);
        pageVo.setSearchText("");

        Page<UserEntity> pager = service.List(pageVo);
        List<UserEntity> user = pager.getContent();

        int totalPage = pager.getTotalPages();
        pageVo.setTotalCount(totalPage);

        pageVo.calc();
        System.out.println("UserEntity="+user.toString());
        model.addAttribute("paging",pageVo);
        model.addAttribute("UserEntity",user);

        return "approval/user/list";
    }
}
