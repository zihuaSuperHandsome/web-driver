package com.zihua.webspider.processor;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.util.StrUtil;
import com.zihua.webspider.handler.AbstractChannelHandler;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName DefaultPageProcessor
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/6 13:25
 */
public class PageProcessorImpl implements PageProcessor {

    private static Logger log = LoggerFactory.getLogger(PageProcessorImpl.class);
    private List<AbstractChannelHandler> handlerList = Lists.newArrayList();
    private Site site;
    private AtomicInteger  handleCount = new AtomicInteger(0);
    public Cache<Class<?>, Object> CACHE = CacheUtil.newFIFOCache(2<<10);

    public PageProcessorImpl(Site site) {
        this.site = site;
    }
    public PageProcessorImpl() {
        this.site = Site.me().setRetryTimes(3).setSleepTime(100);
    }
    public PageProcessorImpl(List<AbstractChannelHandler> handlerList) {
        this.handlerList = handlerList;
    }

    public void addHandle(AbstractChannelHandler handle) {
        handlerList.add(handle);
    }

    private void defaultHandle(Page page) {
        log.info("由于没有处理程序或处理程序均没有生效。");
    }

    @Override
    public void process(Page page) {
        for (AbstractChannelHandler handle : handlerList) {
            if (handle.condition(page)) {
                handle.handler(page);
                String nextUrl = handle.nextTarget(page);
                if (StrUtil.isNotBlank(nextUrl)) {
                    page.addTargetRequest(nextUrl);
                }
                handleCount.getAndIncrement();
            }
        }
        if (handleCount.get() == 0) {
            defaultHandle(page);
            return;
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
