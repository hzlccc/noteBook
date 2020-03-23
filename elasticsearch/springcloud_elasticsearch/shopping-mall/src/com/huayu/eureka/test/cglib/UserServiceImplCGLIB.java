package com.huayu.eureka.test.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class UserServiceImplCGLIB implements MethodInterceptor {  
    @SuppressWarnings("unchecked")  
    public <T> T getInstance(Object target) {  
        //字节码加强器：用来创建动态代理类  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(target.getClass()); //代理的目标对象  
        enhancer.setCallback(this); //回调类，在代理类方法调用时会回调Callback类的intercept方法  
          
        Object result = enhancer.create(); //创建代理类  
        System.out.println(result.getClass().getName());  
          
        return (T)result;  
    }  
      
    @Override  
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {  
        //对目标方法进行拦截处理  
        System.out.println("start...");  
        Object result = proxy.invokeSuper(obj, args); //调用目标类（父类）的方法  
        System.out.println("end");  
        return result;  
    }  
}  