package com.huayu.eureka.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huayu.eureka.test.cglib.UserServiceImpl;
import com.huayu.eureka.test.cglib.UserServiceImplCGLIB;
import com.huayu.eureka.test.jdk.InvocationHandlerImpl;
import com.huayu.eureka.test.jdk.RealSubject;
import com.huayu.eureka.test.jdk.Subject;

@RestController
@RequestMapping("/dtdl")
public class TestController {

	@RequestMapping("/jdk")
	public  void getJDK() throws Exception {
		System.out.println("动态代理JDK");
		//代理的真实对象
        Subject realSubject = new RealSubject();
        
        /**
         * InvocationHandlerImpl 实现了 InvocationHandler 接口，并能实现方法调用从代理类到委托类的分派转发
         * 其内部通常包含指向委托类实例的引用，用于真正执行分派转发过来的方法调用.
         * 即：要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法
         */
        InvocationHandler handler = new InvocationHandlerImpl(realSubject);
 
        ClassLoader loader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();
        /**
         * 该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
         */
        Subject subject = (Subject) Proxy.newProxyInstance(loader, interfaces, handler);
 
        System.out.println("动态代理对象的类型："+subject.getClass().getName());
 
        String hello = subject.SayHello("");
        System.out.println(hello);
//        String goodbye = subject.SayGoodBye();
//        System.out.println(goodbye);

		
		
    }
	
	@RequestMapping("/cglib")
	public  void getCGLIB() throws Exception {
		UserServiceImpl proxy = new UserServiceImplCGLIB().getInstance(new UserServiceImpl()); 
        System.out.println("原方法前");
        String result = proxy.getUserById(100L);  
        System.out.println("原方法后");
        System.out.println(result);  

	}
}
