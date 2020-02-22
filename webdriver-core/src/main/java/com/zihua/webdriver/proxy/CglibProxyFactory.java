package com.zihua.webdriver.proxy;

import java.lang.reflect.Method;

import com.zihua.webdriver.aspect.Aspect;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyFactory extends ProxyFactory{

	@Override
	public <T> T proxy(T target, Aspect aspect) {
		Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new CglibInterceptor(target, aspect));
		return (T) enhancer.create();
	}
	
	class CglibInterceptor implements MethodInterceptor {
		private final Object target;
	    private final Aspect aspect;
		
		public CglibInterceptor(Object target, Aspect aspect) {
	        this.target = target;
	        this.aspect = aspect;
	    }
		
		public Object getTarget() {
	        return this.target;
	    }
		
		@Override
		public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
			Object result = null;
			this.aspect.before(this.target, method, args);
			try {
				result = methodProxy.invokeSuper(target, args);
			} catch (Exception e) {
				this.aspect.afterException(target, method, args, e);
			}
			this.aspect.after(this.target, method, args, result);
			return result;
		}
	}
}
