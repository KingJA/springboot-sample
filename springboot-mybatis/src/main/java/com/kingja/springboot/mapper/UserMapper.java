package com.kingja.springboot.mapper;

import com.kingja.springboot.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2020/12/28 0028 16:46
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> selectUsers();

    List<User> findAll();
}
