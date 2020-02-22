package com.zihua.webdriver.proxy;

import com.zihua.webdriver.aspect.Aspect;

public abstract class ProxyFactory {
	
	public abstract <T> T proxy(T var1, Aspect var2);
	
    public static <T> T createProxy(T target, Aspect aspect) {
        return create().proxy(target, aspect);
    }
	
	public static ProxyFactory create() {
        try {
            return new CglibProxyFactory();
        } catch (NoClassDefFoundError var1) {
            return new JdkProxyFactory();
        }
    }
}
