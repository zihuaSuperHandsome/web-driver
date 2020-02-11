package com.zihua.zhaopin.handler;

import cn.hutool.core.util.ReUtil;
import com.zihua.webspider.handler.AbstractChannelHandler;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName SearchHandler
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/11 14:57
 */
@Component
public class TagsHandler extends AbstractChannelHandler {
    
    @Override
    public boolean condition(Page page) {
        String reg = "https://.*lagou.com/zhaopin/.*/*";
        return ReUtil.isMatch(reg, page.getUrl().get());
    }

    @Override
    public void handler(Page page) {
        
        page.addTargetRequests(page.getHtml().$(".s_position_list .position_link").links().all());
    }

    @Override
    public String nextTarget(Page page) {
        Html html = page.getHtml();
        AtomicReference<String> next = new AtomicReference<>("");
        html.$(".pager_container a").nodes().forEach( (a) -> next.set(ReUtil.isMatch(".*下一页.*", a.get()) ? a.links().get() : null));
        return next.get();
    }
}
