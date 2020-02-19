package com.zihua.webdriver.test.handler;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.CharMatcher;
import com.zihua.webdriver.handler.AbstractChannelHandler;
import com.zihua.webdriver.test.entity.BookCatalog;
import com.zihua.webdriver.test.service.IBookCatalogService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CatalogHandler
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/6 16:44
 */
@Component
public class CatalogHandler extends AbstractChannelHandler {

    private static Logger log = LoggerFactory.getLogger(CatalogHandler.class);

    @Resource
    private IBookCatalogService bookCatalogService;

    public boolean condition(Page page) {
        return page.getUrl().get().indexOf("art_") != -1;
    }

    @Transactional
    public void handler(Page page) {
        Html html = page.getHtml();

        String url = page.getUrl().get();
        int index = url.lastIndexOf('/');
        String origin_id = StrUtil.removeSuffix(url.substring(index + 1), ".html");

        String content_title = html.$(".T1").xpath("//b/text()").get();

        List<Selectable> nodes =  html.$("table").nodes().get(3).$("tr td a").nodes();
        List<BookCatalog> list = Lists.newArrayList();
        for (Selectable node : nodes) {
            String link = node.links().get();
            String span = html.$(".TA").xpath("//span/text()").get();
            String[] texts = CharMatcher.whitespace().trimAndCollapseFrom(span, ' ').split(" ");
            String author = "";
            for (int i = 0; i < texts.length; i++) {
                if ("作者:".equals(texts[i]) && i <= texts.length-1) {
                    author = texts[i + 1];
                    break;
                }
            }
            list.add(BookCatalog.builder().content_title(content_title).author(author).title(node.xpath("//a/text()").get()).url(link).origin_id(origin_id).build());
//            page.addTargetRequest(link);
        }
        if (bookCatalogService.saveBatch(list)) {
            PAGE_COUNT.getAndAdd(list.size());
        }
    }
    
    public String nextTarget(Page page) {
        return null;
    }
}
