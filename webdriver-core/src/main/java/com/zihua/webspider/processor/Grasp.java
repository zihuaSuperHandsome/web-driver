package com.zihua.webspider.processor;

import com.zihua.webspider.handler.AbstractChannelHandler;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

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
}
