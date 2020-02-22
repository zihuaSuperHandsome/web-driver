package com.zihua.webdriver.proxy;

import com.zihua.webdriver.aspect.Aspect;

public class Proxy {
	public static <T> T proxy(T target, Aspect aspect) {
        return ProxyFactory.createProxy(target, aspect);
    }
}
