package com.cloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by wuyue on 2020/8/21.
 */

@RequestMapping(value = "/payment")
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/consul")
    public String paymentConsul() {
        return "consul:" + serverPort + "\t" + UUID.randomUUID().toString();
    }


}

