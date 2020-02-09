package com.zihua.zhaopin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName Application
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/9 16:13
 */
@MapperScan("com.zihua.zhaopin.dao")
@SpringBootApplication
public class Application { 

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }
}
