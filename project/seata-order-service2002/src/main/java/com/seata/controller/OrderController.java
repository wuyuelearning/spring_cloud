package com.seata.controller;

import com.seata.domain.CommonResult;
import com.seata.domain.Order;
import com.seata.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by wuyue on 2020/10/13.
 */
@Slf4j
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order) {
         orderService.create(order);
         return new CommonResult(200,"success");
    }
}
