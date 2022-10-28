package com.foray.gw.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "sign")
public class SignController {

    @GetMapping("list")
    String signList()
    {

        return "/approval/sign/list";
    }
}
