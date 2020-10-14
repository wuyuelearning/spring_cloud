package com.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * Created by wuyue on 2020/9/3.
 */
@Service
//@DefaultProperties(defaultFallback = "infoTimeOutHandler")
public class PayService {

    public String info(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "  -- " + id;
    }

    @HystrixCommand(/*fallbackMethod = "infoTimeOutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")}*/)
    public String infoTimeOut(Integer id) {

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "线程池:  " + Thread.currentThread().getName() + " time out : " + id + "time : ";
    }

    public String infoTimeOutHandler(Integer id) {
        return "线程池:  infoTimeOutHandler " + Thread.currentThread().getName() + " time out : " + id + "time : ";
    }


    @HystrixCommand(fallbackMethod = "paymentBreakerHandler",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enable", value = "true"),// 是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")})// 失败率达到多少次后跳闸
    public String paymentBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("#######id<0");
        }
        return "线程池:  infoTimeOutHandler " + Thread.currentThread().getName() + " time out : " + id + "time : ";
    }

    public String paymentBreakerHandler() {
        return "id <0";
    }


}
