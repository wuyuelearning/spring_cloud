package com.cloud.controller;

import com.cloud.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wuyue on 2020/9/3.
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    PayService payService;

    @Value(value = "${server.port}")
    private String port;

    @GetMapping("/info/{id}")
    public String info(@PathVariable(value = "id") Integer id) {
        String d = payService.info(id);
        log.info("*****" + d);
        return d;
    }

    @GetMapping("/infoTimeOut/{id}")
    public String infoTimeOut(@PathVariable(value = "id") Integer id) {
        String d = payService.infoTimeOut(id);
        log.info("*****" + d);
        return d;
    }


    @GetMapping("/breaker/{id}")
    public String breaker(@PathVariable(value = "id") Integer id) {
        String d = payService.paymentBreaker(id);
        log.info("*****" + d);
        return d;
    }


}
