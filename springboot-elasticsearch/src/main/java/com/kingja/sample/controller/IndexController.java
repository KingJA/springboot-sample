package com.kingja.sample.controller;

import com.kingja.sample.entity.DbBook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2021/1/15 0015 16:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/add")
    public String add() {
        log.info("add ------------->");
        return "add.html";
    }


}
