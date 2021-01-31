package com.kingja.sample.controller;

import com.kingja.sample.dao.BookRepository;
import com.kingja.sample.entity.DbBook;
import com.kingja.sample.entity.EsBook;
import com.kingja.sample.mapper.BookMapper;
import com.kingja.sample.service.BookService;

import org.apache.commons.codec.binary.StringUtils;
import org.assertj.core.util.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2021/1/15 0015 16:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@RestController
@Slf4j
public class DataController {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;


    @RequestMapping("search")
    public Object search(@RequestParam(name = "keyword") String keyword) {
        log.info("keyword : " + keyword);
        // 先构建查询条件
        BoolQueryBuilder defaultQueryBuilder = QueryBuilders.boolQuery();
        if (keyword!=null){
            defaultQueryBuilder.should(QueryBuilders.matchPhraseQuery("name",keyword));
            defaultQueryBuilder.should(QueryBuilders.matchPhraseQuery("des",keyword));
        }

        // 分页条件
        PageRequest pageRequest = PageRequest.of(0,10);
        // 高亮条件
        HighlightBuilder highlightBuilder = getHighlightBuilder("des","name");
        // 排序条件
//        FieldSortBuilder sortBuilder = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
        //组装条件
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(defaultQueryBuilder)
                .withHighlightBuilder(highlightBuilder)
//                .withPageable(pageRequest)
//                .withSort(sortBuilder)
                .build();

        SearchHits<EsBook> searchHits = elasticsearchRestTemplate.search(searchQuery, EsBook.class);
        // 高亮字段映射
        List<EsBook> list = Lists.newArrayList();
        for (SearchHit<EsBook> searchHit : searchHits) {
            EsBook source = searchHit.getContent();
            EsBook target = searchHit.getContent();
            BeanUtils.copyProperties(source,target);
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            for (String highlightField : highlightFields.keySet()) {
                if(StringUtils.equals(highlightField,"des")){
                    target.setDes(highlightFields.get(highlightField).get(0));
                }
                if(StringUtils.equals(highlightField,"name")){
                    target.setName(highlightFields.get(highlightField).get(0));
                }

            }
            list.add(target);
        }
        return list;
    }
    // 设置高亮字段
    private HighlightBuilder getHighlightBuilder(String... fields) {
        // 高亮条件
        HighlightBuilder highlightBuilder = new HighlightBuilder(); //生成高亮查询器
        for (String field : fields) {
            highlightBuilder.field(field);//高亮查询字段
        }
        highlightBuilder.requireFieldMatch(false);     //如果要多个字段高亮,这项要为false
        highlightBuilder.preTags("<span style=\"color:red\">");   //高亮设置
        highlightBuilder.postTags("</span>");
        //下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等
        highlightBuilder.fragmentSize(800000); //最大高亮分片数
        highlightBuilder.numOfFragments(0); //从第一个分片获取高亮片段

        return highlightBuilder;
    }

    @RequestMapping("books")
    public Object getBooks() {
        log.info("getBooks : ");
        return bookMapper.selectAll();
    }

    @RequestMapping("/addBook")
    public Object add(DbBook dbBook) {
        System.out.println(dbBook);

        bookService.addBook(dbBook);


        return "ok";
    }

}
