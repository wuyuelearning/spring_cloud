package com.cloud.controller;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

/**
 * Created by wuyue on 2020/9/17.
 */
public class Test {
    public static void main(String[] args) {
        // 在指定目录下生成动态代理类，非必须
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\admin\\Desktop");
        // 创建Enhance对象，类似于jdk的Proxy类
        Enhancer enhancer = new Enhancer();
        // 设置被代理类的字节码文件
        enhancer.setSuperclass(BeiDaiLi.class);
        // 设置回调方法
        enhancer.setCallback(new cglibProxy());
        // 这里使用的多态，并非直接生成一个BeiDaiLi
        // 创建代理类,代理类继承了被代理类，使用的是集成的思想，
        // 代理类通过继承得到与被代理类相同的类结构，而不是集成同样的接口
        BeiDaiLi daiLi = (BeiDaiLi) enhancer.create();
        // 打印出“增强” ，说明走的是intercept方法
        daiLi.fun1();

//        Enhancer enhancer1 =new Enhancer();
//        enhancer1.setInterfaces(new Class[]{ICgLibProxy.class});
//        enhancer1.setCallback(new cglibProxy());
//        BeiDaiLi1 daiLi1 = (BeiDaiLi1)enhancer1.create();
//        daiLi1.fun2();
    }
}
