package com.cloud.controller;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wuyue on 2020/9/17.
 */
public class cglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("增强");
        // 此处为方法调用，不是反射
        Object object = methodProxy.invokeSuper(o, objects);
        return object;
    }
}
