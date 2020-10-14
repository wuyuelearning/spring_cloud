package com.cloud.controller;


import com.cloud.entities.CommonResult;
import com.cloud.entities.Payment;
import com.cloud.lb.MyLoadBalancer;
import com.cloud.lb.MyLoadBalancerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * Created by wuyue on 2020/8/22.
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {
    ;

    //    private static final String PAYMENT_URL="http://localhost:8001";// 单服务器的方式，端口号固定
    // 多服务器的方式，需要注册中心实现负载均衡，端口号不固定
    // 根据Eureka上注册服务名称（不是域名）
    // 不同端口8001,8002的服务（功能的相同），注册在注册中心使用使用想用的名称，所以注册中心自行负载均衡，
    // 返回不同端口的服务器地址给服务调用者
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MyLoadBalancer myLoadBalancer;


    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/payment/get2/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(404, "失败");
        }

    }

    @GetMapping("/lb")
    public String getOrderLb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0)
            return null;
        ServiceInstance serviceInstance = myLoadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/getLB", String.class);
    }


}
