package com.kingja.springboot.controller;

import com.kingja.springboot.entity.User;
import com.kingja.springboot.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2020/12/28 0028 16:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@RestController
public class TestContoller {

    @Autowired
    UserMapper userMapper;


    @GetMapping("users")
    public List<User> testMybatis() {
        List<User> usersByAnnotation = userMapper.selectUsers();
        List<User> usersByXml = userMapper.findAll();
        System.out.println(usersByAnnotation);
        return usersByXml;
    }
}
