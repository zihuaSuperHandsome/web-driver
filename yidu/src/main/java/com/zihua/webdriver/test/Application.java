package com.zihua.webdriver.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName Application
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/8 16:35
 */
@MapperScan("com.zihua.webspider.test.dao")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class);
    }
}
