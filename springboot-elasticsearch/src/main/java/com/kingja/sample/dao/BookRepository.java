package com.kingja.sample.dao;

import com.kingja.sample.entity.EsBook;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description:TODO
 * Create Time:2021/1/13 0013 17:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BookRepository extends ElasticsearchRepository<EsBook, Integer> {
}
