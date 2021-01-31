package com.kingja.sample.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:TODO
 * Create Time:2020/12/20 0020 19:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @RequestMapping("home")
    public String user() {
        return "用户界面";
    }

//    @RequestMapping("doLogin")
//    public String doLogin(@RequestParam String username, @RequestParam String password) {
//        System.out.println("username:" + username);
//        System.out.println("password:" + password);
//        System.out.println("执行登录");
//        return "登录";
//    }


    @RequestMapping("print")
    @ResponseBody
    public Object whoIm() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
