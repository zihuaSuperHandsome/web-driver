package com.zihua.webspider.handler;

import cn.hutool.core.collection.ConcurrentHashSet;
import lombok.Getter;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AbstractChannelHandler
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/6 15:53
 */
public abstract class AbstractChannelHandler implements ChannelHandler {

    // 已经访问过的页面集合
    protected Set<String> PAGE_CACHE = new ConcurrentHashSet(2<<10);

    // 缓存信息集合
    protected Map<String, Object> CACHE = new ConcurrentHashMap(2<<22);

    // 抓取数量
    @Getter
    protected AtomicInteger PAGE_COUNT = new AtomicInteger(0);
}
