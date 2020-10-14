package com.cloud.controller;


import com.cloud.entities.CommonResult;
import com.cloud.entities.Payment;
import com.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wuyue on 2020/8/21.
 */

@RequestMapping(value = "/payment")
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("create: result: " + result);
        if (result > 0) {
            return new CommonResult<>(200, "成功 server port: " + serverPort, result);
        } else {
            return new CommonResult<>(500, "失败 server port: " + serverPort, null);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("getPaymentById: result: " + payment);
        if (payment != null) {
            return new CommonResult<>(200, "成功 server port: " + serverPort, payment);
        } else {
            return new CommonResult<>(505, "失败 server port: " + serverPort, null);
        }

    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            log.info("discovery services :" + s);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance s : instances) {

            log.info("discovery instances :" + s.getServiceId() + "\t"
                    + s.getHost() + "\t" + s.getPort() + "\t" + s.getUri());

        }
        return discoveryClient;
    }

    @GetMapping("/getLB")
    public String getLB() {
        return serverPort;
    }

}

