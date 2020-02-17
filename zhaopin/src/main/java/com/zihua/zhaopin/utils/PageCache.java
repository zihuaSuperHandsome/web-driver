package com.zihua.zhaopin.utils;

import cn.hutool.cache.impl.FIFOCache;
import org.springframework.stereotype.Component;

/**
 * @ClassName Cache
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/17 11:21
 */
@Component
public class PageCache extends FIFOCache {

    public PageCache(int capacity) {
        super(capacity);
    }

    public PageCache(int capacity, long timeout) {
        super(capacity, timeout);
    }
    
    public PageCache() {
        super(2<<10);
    };
}
