package org.kingja.sample.controller;


import org.kingja.sample.entity.User;
import org.kingja.sample.mapper.UserMapper;
import org.kingja.sample.mapper.UserMapperCus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

/**
 * Description:TODO
 * Create Time:2020/12/28 0028 16:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@RestController
public class TestContoller {

    @Autowired
    UserMapperCus userMapperCus;

    @Autowired
    UserMapper userMapper;


    @GetMapping("users")
    public List<User> testMybatis() {
        List<User> usersByAnnotation = userMapperCus.selectUsers();
        List<User> usersByXml = userMapperCus.findAll();
        System.out.println(usersByXml);


        return usersByAnnotation;
    }
}
