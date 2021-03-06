package com.zihua.webdriver.test.config;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.zihua.webdriver.processor.Grasp;
import com.zihua.webdriver.test.handler.CatalogHandler;
import com.zihua.webdriver.test.handler.ContentHandler;
import com.zihua.webdriver.test.handler.ListHandler;
import com.zihua.webdriver.utils.TableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName StartHelper
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/8 17:31
 */
@Component
public class SpiderRunner implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(SpiderRunner.class);

    @Resource
    private ListHandler listHandle;

    @Resource
    private CatalogHandler catalogHandler;

    @Resource
    private ContentHandler contentHandler;

    public void run(String... args) throws Exception {
        TableUtil.tableGenerateByPackage();
        String url = "https://yiduks.com/artlist_511.html";

        DateTime start = DateUtil.date();
        Grasp grasp = new Grasp();
        grasp.addListen(listHandle).addListen(catalogHandler).thread(4).addUrl(url);
        grasp.run();
        DateTime end = DateUtil.date();

        log.info("/***************************/");
        log.info("抓取耗时：{}", DateUtil.formatBetween(start, end, BetweenFormater.Level.SECOND));
        log.info("抓取到的小说数:{}", listHandle.getPAGE_COUNT().get());
        log.info("抓取到的文章数处理数:{}", catalogHandler.getPAGE_COUNT().get());
        log.info("成功存储的文章数:{}", contentHandler.getPAGE_COUNT().get());
        log.info("/***************************/");
    }
}
