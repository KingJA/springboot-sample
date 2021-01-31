package com.kingja.sample.service;

import com.kingja.sample.dao.BookRepository;
import com.kingja.sample.entity.DbBook;
import com.kingja.sample.entity.EsBook;
import com.kingja.sample.mapper.BookMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2021/1/17 0017 22:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Service
@Slf4j
public class BookService {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    BookRepository bookRepository;
    @Transactional
    public void addBook(DbBook dbBook) {
        int result = bookMapper.insertSelective(dbBook);
        if (result > 0) {
            EsBook esBook = new EsBook();
            BeanUtils.copyProperties(dbBook,esBook);
            bookRepository.save(esBook);
        }
    }
}
