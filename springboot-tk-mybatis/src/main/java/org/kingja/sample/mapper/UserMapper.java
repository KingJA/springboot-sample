package org.kingja.sample.mapper;

import org.kingja.sample.entity.User;
import org.kingja.sample.entity.UserExample;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    long countByExample(UserExample example);
}