package com.zihua.webdriver.handler;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AbstractChannelHandler
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/6 15:53
 */
public abstract class AbstractChannelHandler implements ChannelHandler {

    // 抓取数量
    @Getter
    protected AtomicInteger PAGE_COUNT = new AtomicInteger(0);
}
