package com.zihua.zhaopin.start;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.zihua.webspider.processor.Grasp;
import com.zihua.webspider.utils.TableUtil;
import com.zihua.zhaopin.handler.IndexHandler;
import com.zihua.zhaopin.handler.PageHandler;
import com.zihua.zhaopin.handler.SchoolHandler;
import com.zihua.zhaopin.handler.TagsHandler;
import com.zihua.zhaopin.rule.PageRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;

import javax.annotation.Resource;
import java.util.concurrent.Executors;

/**
 * @ClassName StartHelper
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/8 17:31
 */
//@Component
public class SpiderRunner implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(SpiderRunner.class);

    @Resource
    private IndexHandler indexHandle;

    @Resource
    private SchoolHandler schoolHandler;
    
    @Resource
    private PageHandler pageHandler;

    @Resource
    private TagsHandler tagsHandler;

    public void run(String... args) throws Exception {
        TableUtil.tableGenerateByPackage();

        String url1 = "https://www.lagou.com/";
        String url2 = "https://xiaoyuan.lagou.com/";
        String url3 = "https://www.lagou.com/zhaopin/Java/30";
        String url4 = "https://www.lagou.com/jobs/5413385.html";

        DateTime start = DateUtil.date();
        Site site = Site.me().setRetryTimes(1).setTimeOut(10000).setSleepTime(3000).setCycleRetryTimes(1);
        Grasp.create(site)
                .addListen(indexHandle)
                .addListen(schoolHandler)
                .addListen(tagsHandler)
                .addListen(pageHandler)
                .addRule(new PageRule())
                .addUrl(url1)
//                .addUrl(url2)
//                .addUrl(url3)
//                .addUrl(url4)
                .thread(1)
                .run();
        DateTime end = DateUtil.date();
        
        log.info("/***************************/");
        log.info("抓取耗时：{}", DateUtil.formatBetween(start, end, BetweenFormater.Level.SECOND));
        log.info("/***************************/");
    }
}


