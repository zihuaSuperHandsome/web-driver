package com.webdriver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ClassName SpringBootApplication
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/19 18:00
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {
}
