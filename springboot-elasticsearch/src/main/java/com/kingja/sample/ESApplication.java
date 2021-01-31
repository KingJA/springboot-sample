package com.kingja.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@EnableScheduling
@MapperScan("com.kingja.sample.mapper")
public class ESApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ESApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ESApplication.class);
    }

}
