package com.zihua.webspider.processor;

import com.zihua.webspider.handler.AbstractChannelHandler;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import java.util.Set;

public class Grasp {
    private PageProcessorImpl pageProcessor;
    private Site site = Site.me().setRetryTimes(10).setTimeOut(10000).setSleepTime(300).setCycleRetryTimes(3);
    private Spider spider;

    public Grasp(AbstractChannelHandler handler) {
        Grasp();
        this.pageProcessor.addHandle(handler);
    }
    public Grasp() {
        Grasp();
    }
    private void Grasp() {
        pageProcessor = new PageProcessorImpl(site);
        spider = new Spider(pageProcessor);
    }

    public static Grasp create(AbstractChannelHandler handler) {
        return new Grasp().addListen(handler);
    }
    public static Grasp create() {
        return new Grasp();
    }

    public Grasp addListen(AbstractChannelHandler handler) {
        pageProcessor.addHandle(handler);
        return this;
    }
    
    public void run() {
        spider.run();
    }

    public Grasp thread(int count) {
        this.spider.thread(count);
        return this;
    }

    public Grasp addUrl(String... urls) {
        this.spider.addUrl(urls);
        return this;
    }

    public Grasp Site(Site siteconfig) {
        site = siteconfig;
        return this;
    }
    
    public Grasp SiteTimeOut(int timeOut) {
        site.setTimeOut(timeOut);
        return this;
    }
    public Grasp SiteAcceptStatCode(Set<Integer> acceptStatCode) {
        site.setAcceptStatCode(acceptStatCode);
        return this;
    }
    public Grasp SiteCharset(String charset) {
        site.setCharset(charset);
        return this;
    }
    public Grasp SiteCycleRetryTimes(int cycleRetryTimes) {
        site.setCycleRetryTimes(cycleRetryTimes);
        return this;
    }
    public Grasp SiteDomain(String domain) {
        site.setDomain(domain);
        return this;
    }
    public Grasp SiteRetrySleepTime(int retrySleepTime) {
        site.setRetrySleepTime(retrySleepTime);
        return this;
    }
    public Grasp SiteRetryTimes(int retryTimes) {
        site.setRetryTimes(retryTimes);
        return this;
    }
    public Grasp SiteSleepTime(int sleepTime) {
        site.setSleepTime(sleepTime);
        return this;
    }
    public Grasp SiteUserAgent(String userAgent) {
        site.setUserAgent(userAgent);
        return this;
    }
    public Grasp SiteHeaderAdd(String key, String value) {
        site.addHeader(key, value);
        return this;
    }
    
}
