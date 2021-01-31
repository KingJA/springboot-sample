package org.kingja.sample.mapper;


import org.apache.ibatis.annotations.Select;
import org.kingja.sample.entity.User;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2020/12/28 0028 16:46
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface UserMapperCus  {

    @Select("select * from user")
    List<User> selectUsers();

    List<User> findAll();
//
//    void insertUser();
}
