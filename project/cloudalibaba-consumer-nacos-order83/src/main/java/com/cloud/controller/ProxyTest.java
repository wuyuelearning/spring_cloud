package com.cloud.controller;

/**
 * Created by wuyue on 2020/9/18.
 */
public class ProxyTest {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new BeiDaiLi());


        factory.setBeforeAdvice(new BeforeAdvice() {
            @Override
            public void before() {
                System.out.println("before fun ");
            }
        });

        factory.setAfterAdvice(new AfterAdvice() {
            @Override
            public void after() {
                System.out.println("after fun");
            }
        });

        IProxy daiLi = (IProxy) factory.createProxy();
        daiLi.fun();
    }
}
