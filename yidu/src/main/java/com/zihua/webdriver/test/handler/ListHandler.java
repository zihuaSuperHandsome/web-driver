package com.zihua.webdriver.test.handler;

import cn.hutool.core.util.StrUtil;
import com.zihua.webdriver.handler.AbstractChannelHandler;
import com.zihua.webdriver.test.entity.BookBean;
import com.zihua.webdriver.test.service.IBookService;
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
 *  文章页处理器
 */
@Component
public class ListHandler extends AbstractChannelHandler {

    private static Logger log = LoggerFactory.getLogger(ListHandler.class);

    @Resource
    private IBookService bookService;

    public boolean condition(Page page) {
        return page.getUrl().get().indexOf("artlist_") != -1;
    }

    @Transactional
    public void handler(Page page) {
        Html html = page.getHtml();
        List<Selectable> nodes = html.$(".b_row").nodes();
        List<BookBean> books = Lists.newArrayList();
        for (Selectable node : nodes) {
            String url = node.$(".b_read").links().get();
            int index = url.lastIndexOf('/');
            String origin_id = StrUtil.removeSuffix(url.substring(index + 1), ".html");
            books.add(BookBean.builder()
                    .title(node.$(".b_title").xpath("//a/text()").get())
                    .author(node.$(".b_auth").xpath("//a/text()").get())
                    .type(node.$(".b_artc").xpath("//div/text()").get())
                    .status(node.$(".b_staus").xpath("//div/text()").get())
                    .url("https://yiduks.com/" + origin_id + ".html")
                    .origin_id(origin_id)
                .build());
        }
        if (bookService.saveBatch(books)) {
            PAGE_COUNT.getAndAdd(books.size());
            List<String> urls = html.$(".b_row .b_read").links().all();
            page.addTargetRequests(urls);
        }
    }

    public String nextTarget(Page page) {
        return page.getHtml().$(".n_goright").links().get();
    }
}
