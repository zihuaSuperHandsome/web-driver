package com.zihua.zhaopin.handler;

import com.zihua.webspider.handler.AbstractChannelHandler;
import com.zihua.webspider.utils.StrUtil;
import com.zihua.zhaopin.entity.JobTag;
import com.zihua.zhaopin.service.IJobTagService;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;
import java.util.List;

@Component
public class IndexHandler extends AbstractChannelHandler {
    
    @Resource
    IJobTagService jobTagService;
    
    @Override
    public boolean condition(Page page) {
        String url = page.getUrl().get();
        return url.equalsIgnoreCase("https://www.lagou.com/")
                || url.equalsIgnoreCase("https://www.lagou.com")
                || url.equalsIgnoreCase("http://www.lagou.com/")
                || url.equalsIgnoreCase("http://www.lagou.com");
    }

    @Override
    public void handler(Page page) {
        Html html = page.getHtml();
        List<Selectable> nodes = html.$(".category-list").nodes();
        List<JobTag> list = Lists.newArrayList();
        for (Selectable node : nodes) {
            String parent = StrUtil.space(node.xpath("//h2/text()").get());
            list.add(JobTag.builder().name(parent).origin_id(page.getUrl().get()).build());
            List<Selectable> childrens = node.$("a").nodes();
            for (Selectable children : childrens) {
                list.add(JobTag.builder().name(children.xpath("//h3/text()").get()).url(children.links().get()).origin_id(children.$("a", "data-lg-tj-no").get()).p_name(parent).build());
                page.addTargetRequest(children.links().get());
            }
        }

        if (jobTagService.saveBatch(list)) {
            PAGE_COUNT.getAndAdd(list.size());
        }
    }

    @Override
    public String nextTarget(Page page) {
        return null;
        
    }
}
