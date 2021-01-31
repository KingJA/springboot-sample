package com.kingja.sample.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2021/1/13 0013 17:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

@Data
@Table(name = "book")
public class DbBook {

    /**
     * 加@Id和@GeneratedValue可以把自增id注入到对象中
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String name;
    private String des;
    private String author;
    private Double price;
    private Integer stock;
    private Date createTime ;
    private Date updateTime ;

}
