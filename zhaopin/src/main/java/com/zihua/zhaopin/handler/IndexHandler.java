package com.zihua.zhaopin.handler;

import cn.hutool.core.util.ReUtil;
import com.zihua.webspider.handler.AbstractChannelHandler;
import com.zihua.webspider.utils.StrUtil;
import com.zihua.zhaopin.entity.Tag;
import com.zihua.zhaopin.service.ITagService;
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
    ITagService jobTagService;
    
    @Override
    public boolean condition(Page page) {
        String reg = "https://.*lagou.com/*";
        return ReUtil.isMatch(reg, page.getUrl().get());
    }

    @Override
    public void handler(Page page) {
        page.getHeaders().forEach((k,v)->System.out.println(k + ":" + v));
        
        Html html = page.getHtml();
        List<Selectable> nodes = html.$(".category-list").nodes();
        List<Tag> list = Lists.newArrayList();
        for (Selectable node : nodes) {
            String parent = StrUtil.space(node.xpath("//h2/text()").get());
            list.add(Tag.builder().name(parent).note("普通").origin_id(page.getUrl().get()).build());
            List<Selectable> childrens = node.$("a").nodes();
            for (Selectable children : childrens) {
                list.add(Tag.builder().name(children.xpath("//h3/text()").get()).url(children.links().get()).origin_id(children.$("a", "data-lg-tj-no").get()).p_name(parent).note("普通").build());
                // 将搜索名继续放至队列中
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
