package com.zihua.webdriver.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract interface Aspect {
	
	void before(Object target, Method method, Object[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	void after(Object target, Method method, Object[] args, Object returnVal) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	void afterException(Object target, Method method, Object[] args, Throwable e) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
