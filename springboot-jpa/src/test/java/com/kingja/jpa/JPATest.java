package com.kingja.jpa;

import com.kingja.jpa.dao.StudentRepository;
import com.kingja.jpa.entity.Student;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2020/12/20 0020 15:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Slf4j
@SpringBootTest
public class JPATest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    @Order(1)
    public void testFind() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList);
        log.info("studentList : ", studentList);
    }

    @Test
    @Order(1)
    public void testInsert() {
        Student student = new Student();
        student.setAge(new Random().nextInt(10+20));
        student.setGender(1);
        student.setName("jack");
        Student result = studentRepository.saveAndFlush(student);
        log.info("【result】 : ", result);
    }


    @Test
    @Order(1)
    public void testUpdate() {
        Student student = studentRepository.findById(6L).get();
        student.setName("修改5");
        Student result = studentRepository.saveAndFlush(student);
        log.info("【result】 : ", result);
    }
    @Test
    @Order(1)
    public void testUpdateByAnnotation() {
        int updateResult = studentRepository.updateByAnnotation(6L, "***");
        log.info("【updateResult】 : ", updateResult);
    }

    @Test
    @Order(1)
    public void testDelete() {
        studentRepository.deleteById(6L);
    }
}
