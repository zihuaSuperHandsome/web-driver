package com.zihua.zhaopin.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName FixedThreadPool
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/17 13:41
 */
@Component
public class FixedThreadPool {
    ExecutorService fixedThreadPool;
    
    public void excute(Runnable command) {
        fixedThreadPool.execute(command);
    }
    
    public void shutdown() {
        fixedThreadPool.shutdown();
    }

    public FixedThreadPool() {
        fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public FixedThreadPool(ExecutorService executor) {
        fixedThreadPool = executor;
    }
}
