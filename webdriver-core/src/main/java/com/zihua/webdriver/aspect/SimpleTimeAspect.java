package com.zihua.webdriver.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName TimeAspect
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/21 22:38
 */
public class SimpleTimeAspect implements AspectNoException{
    private Long time = 0l;
    
    @Override
    public void before(Object target, Method method, Object[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        time = System.currentTimeMillis();
    }

    @Override
    public void after(Object target, Method method, Object[] args, Object returnVal) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println(System.currentTimeMillis() - time + "毫秒");
    }
}
