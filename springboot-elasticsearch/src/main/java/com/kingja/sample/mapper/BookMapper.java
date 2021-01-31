package com.kingja.sample.mapper;

import com.kingja.sample.entity.DbBook;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import tk.mybatis.mapper.common.Mapper;

/**
 * Description:TODO
 * Create Time:2021/1/17 0017 20:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BookMapper extends Mapper<DbBook> {


//    @Insert({"insert into book (name,des,author,stock,price) values(#{name},#{des},#{author},#{stock},#{price})"})
//    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
//    Integer addBook(DbBook dbBook);
}
