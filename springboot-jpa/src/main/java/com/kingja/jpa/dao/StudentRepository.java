package com.kingja.jpa.dao;


import com.kingja.jpa.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.transaction.Transactional;

/**
 * Description:TODO
 * Create Time:2020/12/20 0020 15:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query(value = "select * from student where student.age > :age", nativeQuery = true)
    List<Student> findByAnnotation(int age);

    @Modifying
    @Query(value = "update Student as student set student.name = :name where student.id = :id")
    int updateByAnnotation(@Param(value = "id") Long id, @Param(value = "name") String name);

    @Modifying
    @Query(value = "insert into student (student.name,student.age,student.gender) values (?1,?2,?3)", nativeQuery =
            true)
    int insertByAnnotation(@Param(value = "name") String name, @Param(value = "age") int age,
                           @Param(value = "gender") int gender);

    @Modifying
    @Query(value = "delete from student where student.id = :id", nativeQuery = true)
    int deleteByAnnotation(@Param(value = "id") Long id);
}
