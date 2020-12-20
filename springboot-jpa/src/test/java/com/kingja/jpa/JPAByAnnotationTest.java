package com.kingja.jpa;

import com.kingja.jpa.dao.StudentRepository;
import com.kingja.jpa.entity.Student;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2020/12/20 0020 15:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Slf4j
@SpringBootTest
public class JPAByAnnotationTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    @Order(1)
    public void testFindByAnnotation() {
        List<Student> studentList = studentRepository.findByAnnotation(18);
        System.out.println(studentList);
    }

    @Test
    @Order(1)
    public void testInsertByAnnotation() {
        int insertResult = studentRepository.insertByAnnotation("ooo", 2, 1);
        System.out.println("【insertResult】 : "+ insertResult);
    }

    @Test
    @Order(1)
    public void testUpdateByAnnotation() {
        int updateResult = studentRepository.updateByAnnotation(6L, "***");
        System.out.println("【updateResult】 : "+ updateResult);
    }

    @Test
    @Order(1)
    public void testDeleteByAnnotation() {
        int deleteResult = studentRepository.deleteByAnnotation(19L);
        System.out.println("【deleteResult】 : "+ deleteResult);
    }
}
