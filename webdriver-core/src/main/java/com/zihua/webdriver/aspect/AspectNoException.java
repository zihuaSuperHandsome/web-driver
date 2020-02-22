package com.zihua.webdriver.aspect;

import java.lang.reflect.Method;

public interface AspectNoException extends Aspect{
	default void afterException(Object target, Method method, Object[] args, Throwable e) {
		
	}
}
