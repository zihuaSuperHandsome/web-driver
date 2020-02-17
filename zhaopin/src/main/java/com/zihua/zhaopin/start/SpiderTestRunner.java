package com.zihua.zhaopin.start;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import com.zihua.webspider.processor.Grasp;
import com.zihua.webspider.utils.TableUtil;
import com.zihua.zhaopin.handler.IndexHandler;
import com.zihua.zhaopin.handler.PageHandler;
import com.zihua.zhaopin.handler.SchoolHandler;
import com.zihua.zhaopin.handler.TagsHandler;
import com.zihua.zhaopin.processor.PageThread;
import com.zihua.zhaopin.rule.PageRule;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName StartHelper
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/8 17:31
 */
@Component
public class SpiderTestRunner implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(SpiderTestRunner.class);
    
    @Resource
    PageThread page;

    public void run(String... args) throws Exception {
        TableUtil.tableGenerateByPackage();
        FileUtil.mkdir("error_page");
        
        String[] urls = {
                "https://www.lagou.com/jobs/3111818.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/5603397.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6742174.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/5552135.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6811184.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/1207717.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/5935213.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6806160.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6124096.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6669810.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/5313751.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6224362.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6776209.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6663936.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6224383.html?show=39ae8745abb64a539555e13be09e5352"
        };
        
        DateTime start = DateUtil.date();
        Site site = Site.me().setRetryTimes(1).setTimeOut(10000).setSleepTime(3000).setCycleRetryTimes(1);
        CountDownLatch countDownLatch = new CountDownLatch(urls.length);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < urls.length; i++) {
            int finalI = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(800);
                        Spider.create(page).addUrl(urls[finalI]).run();
                    } catch (InterruptedException e) {
                        
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        
        countDownLatch.await();
        fixedThreadPool.shutdown();

        DateTime end = DateUtil.date();
        log.info("/***************************/");
        log.info("抓取耗时：{}", DateUtil.formatBetween(start, end, BetweenFormater.Level.SECOND));
        log.info("失败次数：{}", page.getErrCount());
        log.info("成功次数：{}", page.getSuccCount());
        log.info("/***************************/");
    }
}


