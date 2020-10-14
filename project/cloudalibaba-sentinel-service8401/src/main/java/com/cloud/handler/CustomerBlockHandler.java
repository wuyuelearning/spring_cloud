package com.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.entities.CommonResult;
import org.springframework.stereotype.Component;

/**
 * Created by wuyue on 2020/10/9.
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(22222, "handlerException...");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(22222, "handlerException2...");
    }

}
