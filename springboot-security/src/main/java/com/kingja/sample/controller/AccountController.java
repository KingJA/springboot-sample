package com.kingja.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:TODO
 * Create Time:2020/12/20 0020 19:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
public class AccountController {




    @RequestMapping("login_page")
    public String goLogin() {
        return "login_page";
    }
}
