package com.zihua.zhaopin.handler;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.ReUtil;
import com.zihua.webspider.handler.AbstractChannelHandler;
import com.zihua.zhaopin.processor.PageThread;
import com.zihua.zhaopin.utils.PageCache;
import com.zihua.zhaopin.utils.FixedThreadPool;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName SearchHandler
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/11 14:57
 */
@Component
public class TagsHandler extends AbstractChannelHandler {
    
    @Resource
    PageCache pageCache;
    
    @Resource
    FixedThreadPool pool;

    FileWriter log = new FileWriter("info.txt");

    @Override
    public boolean condition(Page page) {
        String reg = "https://.*lagou.com/zhaopin/.*/*";
        return ReUtil.isMatch(reg, page.getUrl().get());
    }

    @Override
    public void handler(Page page) {
        for (String url : page.getHtml().$(".s_position_list .position_link").links().all()) {
            pool.excute(new Runnable() {
                @Override
                public void run() {
                    synchronized (this) {
                        log.append("开始抓取：" + url + "\n");
                        try {
                            Thread.sleep(2000);
                            Spider.create(new PageThread()).addUrl(url).run();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    @Override
    public String nextTarget(Page page) {
        Html html = page.getHtml();
        AtomicReference<String> next = new AtomicReference<>("");
        html.$(".pager_container a").nodes().forEach( (a) -> next.set(ReUtil.isMatch(".*下一页.*", a.get()) ? a.links().get() : null));
        return next.get();
    }
}
