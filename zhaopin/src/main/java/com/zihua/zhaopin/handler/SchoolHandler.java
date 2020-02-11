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

/**
 * @ClassName SchoolHandler
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/10 1:31
 */
@Component
public class SchoolHandler extends AbstractChannelHandler {

    @Resource
    ITagService jobTagService;
    
    @Override
    public boolean condition(Page page) {
        String reg = "https://xiaoyuan.lagou.com/*";
        return ReUtil.isMatch(reg, page.getUrl().get());
    }
    

    @Override
    public void handler(Page page) {
        Html html = page.getHtml();
        List<Selectable> nodes = html.$(".main-navs").nodes();
        List<Tag> list = Lists.newArrayList();
        for (Selectable node : nodes) {
            String parent = StrUtil.space(node.xpath("//div[@class='menu-main']/dl/dt/text()").get());
            list.add(Tag.builder().name(parent).origin_id(page.getUrl().get()).note("校招").build());
            List<Selectable> childrens = node.$(".menu-sub dl dd a").nodes();
            for (Selectable children : childrens) {
                list.add(Tag.builder().name(children.xpath("//a/text()").get()).url(children.links().get()).origin_id(children.xpath("//a/@data-reactid").get()).p_name(parent).note("校招").build());
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
