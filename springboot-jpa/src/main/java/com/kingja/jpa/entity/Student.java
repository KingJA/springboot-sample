package com.kingja.jpa.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2020/12/20 0020 15:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Data
@Entity
@Table(name = "student")
//自动更新时间
@EntityListeners(AuditingEntityListener.class)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private int gender;
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;
    @Column(name = "update_time")
    @LastModifiedDate
    private Date updateTime;
}
