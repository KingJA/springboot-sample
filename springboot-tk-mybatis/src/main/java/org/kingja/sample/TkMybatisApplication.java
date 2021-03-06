package org.kingja.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
//如果用了@Mapper注解，则不需要加入@MapperScan
//@MapperScan("com.kingja.springboot.mapper")
@MapperScan("org.kingja.sample.mapper")
public class TkMybatisApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(TkMybatisApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TkMybatisApplication.class);
    }
}
