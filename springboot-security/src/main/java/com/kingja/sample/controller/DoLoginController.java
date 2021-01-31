package com.kingja.sample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:TODO
 * Create Time:2020/12/20 0020 19:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@RestController
public class DoLoginController {


    @RequestMapping("doLogin")
    public String  doLogin(@RequestParam String username, @RequestParam String password) {
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        System.out.println("执行登录");
        return "登录";
    }
}
