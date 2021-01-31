package com.kingja.sample.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2021/1/13 0013 17:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

@Data
@Document(indexName = "book", useServerConfiguration = true, createIndex = false)
public class EsBook {
    @Id
    private Integer id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String des;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String author;
    private Double price;
    private Integer stock;
    private Date createTime;
    private Date updateTime;

}
