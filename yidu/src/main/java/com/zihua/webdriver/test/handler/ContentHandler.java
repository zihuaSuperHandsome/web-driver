package com.zihua.webdriver.test.handler;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zihua.webdriver.handler.AbstractChannelHandler;
import com.zihua.webdriver.test.entity.BookCatalog;
import com.zihua.webdriver.test.service.IBookCatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;

@Component
public class ContentHandler extends AbstractChannelHandler {

    private static Logger log = LoggerFactory.getLogger(ContentHandler.class);

    @Resource
    private IBookCatalogService bookCatalogService;


    public boolean condition(Page page) {
        return page.getUrl().get().indexOf("read_") != -1;
    }


    @Transactional
    public void handler(Page page) {
        Html html = page.getHtml();

        String catalog_url = "";
        for (Selectable node : html.$(".MC").nodes().get(1).xpath("//a").nodes()) {
            if (node.get().indexOf("art_") != -1) {
                catalog_url = node.links().get();
                break;
            }
        }

        int index = catalog_url.lastIndexOf('/');
        String origin_id = StrUtil.removeSuffix(catalog_url.substring(index + 1), ".html");
        String author = html.$(".MC").xpath("//a/text()").get();
        String b = html.$(".MC").xpath("//b/text()").get();
        String content_title = ReUtil.get("《.*》", b, 0);
        String title = ReUtil.get("(?<=》).*", b, 0);

        if(bookCatalogService.update(BookCatalog.builder().content(html.$(".ART").get()).build(),
                new UpdateWrapper<BookCatalog>().eq("title", title).eq("content_title", content_title).eq("author", author).eq("origin_id", origin_id))) {
            PAGE_COUNT.getAndIncrement();
        }
    }

    public String nextTarget(Page page) {
        return null;
    }
}