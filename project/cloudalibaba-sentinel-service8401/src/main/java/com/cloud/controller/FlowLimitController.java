package com.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuyue on 2020/9/23.
 */
@RestController
@Slf4j
public class FlowLimitController {


    @GetMapping("/test/ttt")
    public String test() {
        return "test......";


    }

    @GetMapping("/test")
    public String test2() {
        log.info(Thread.currentThread().getName() + "   ....test2");
        return "test2......";
    }

    @GetMapping("/hotKeyTest")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHotKey")
    public String hotKey(@RequestParam(value = "p1", required = false) Integer p1, @RequestParam(value = "p2", required = false) Integer p2) {
        log.info(Thread.currentThread().getName() + "   ....hotkey");
        return "hotKey......";
    }

    public String dealHotKey(Integer p1, Integer p2, BlockException blockException) {
        return "hotkey 2";
    }


}
