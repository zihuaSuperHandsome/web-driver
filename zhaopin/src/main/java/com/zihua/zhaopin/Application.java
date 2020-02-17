package com.zihua.zhaopin;

import com.zihua.zhaopin.service.IJobService;
import com.zihua.zhaopin.utils.FixedThreadPool;
import com.zihua.zhaopin.utils.PageCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        PageCache cache = ctx.getBean(PageCache.class);
        FixedThreadPool pool = ctx.getBean(FixedThreadPool.class);
    }
}
