package com.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by wuyue on 2020/9/17.
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;


    @Value("${service-url.nacos-user-service}")
    private String serverUrl;


    @GetMapping("/order/nacos/{id}")
    public String orderInfo(@PathVariable("id") Integer id) {
        log.info("111111->" + serverUrl);
        return restTemplate.getForObject(serverUrl + "/payment/nacos/" + id, String.class);
    }

}
