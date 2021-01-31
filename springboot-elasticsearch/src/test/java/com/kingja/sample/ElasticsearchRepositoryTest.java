package com.kingja.sample;


import com.kingja.sample.dao.BookRepository;
import com.kingja.sample.entity.EsBook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2020/12/20 0020 15:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Slf4j
@SpringBootTest
public class ElasticsearchRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ElasticsearchRestTemplate  restTemplate;

    /**
     * 创建索引
     */
    @Test
    public void createIndex() {
        boolean result = restTemplate.createIndex(EsBook.class);
        System.out.println(result);
    }

    /**
     * 删除索引
     */
    @Test
    public void removeIndex() {
    }

    @Test
    public void addDocument1() {
        EsBook esBook = new EsBook();
        esBook.setId(1);
        esBook.setName("百年孤独");
        esBook.setDes("是一本非常经典的魔幻小说");
        esBook.setPrice(12.50d);
        esBook.setStock(99);
        esBook.setCreateTime(new Date());
        esBook.setUpdateTime(new Date());
        EsBook save = bookRepository.save(esBook);
        System.out.println(save);
    }
    @Test
    public void addDocument2() {
        EsBook esBook = new EsBook();
        esBook.setId(2);
        esBook.setName("哈利波特");
        esBook.setDes("是一本非常经典的魔幻小说");
        esBook.setPrice(55.50d);
        esBook.setStock(99);
        esBook.setCreateTime(new Date());
        esBook.setUpdateTime(new Date());
        EsBook save = bookRepository.save(esBook);
        System.out.println(save);
    }
    @Test
    public void removeDocument() {
        bookRepository.deleteById(2);
    }

    /**
     * ES没有修改功能，只有覆盖
     */
    @Test
    public void modifyDocument() {
        EsBook esBook = new EsBook();
        esBook.setId(1);
        esBook.setName("百年孤独");
        esBook.setDes("是一本非常经典的魔幻小说，非常经典");
        esBook.setPrice(15.50d);
        esBook.setStock(100);
        esBook.setCreateTime(new Date());
        esBook.setUpdateTime(new Date());
        EsBook save = bookRepository.save(esBook);
        System.out.println(save);
    }
    @Test
    public void getDocument() {
        EsBook esBook = bookRepository.findById(1).get();
        System.out.println(esBook);
    }
}
