package com.zihua.zhaopin.handler;

import com.zihua.webspider.handler.AbstractChannelHandler;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

/**
 * @ClassName PageHandle
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/9 17:35
 */
@Component
public class PageHandler extends AbstractChannelHandler {

    @Override
    public boolean condition(Page page) {
        return false;
    }

    @Override
    public void handler(Page page) {
        Html html = page.getHtml();
        System.out.println(page.getUrl());
    }

    @Override
    public String nextTarget(Page page) {
        return null;
    }
}
