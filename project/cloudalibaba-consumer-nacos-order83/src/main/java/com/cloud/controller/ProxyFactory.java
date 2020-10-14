package com.cloud.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wuyue on 2020/9/18.
 */
public class ProxyFactory {
    private Object object;
    private BeforeAdvice beforeAdvice;
    private AfterAdvice afterAdvice;

    public ProxyFactory(Object o) {
        this.object = o;
    }

    public Object createProxy() {

        ClassLoader loader = object.getClass().getClassLoader();

        Class[] interfaces = object.getClass().getInterfaces();

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                beforeAdvice.before();
                Object result = method.invoke(object, args);
                afterAdvice.after();
                return result;
            }
        };

        return Proxy.newProxyInstance(loader, interfaces, invocationHandler);
    }

    public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    public void setAfterAdvice(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }
}
