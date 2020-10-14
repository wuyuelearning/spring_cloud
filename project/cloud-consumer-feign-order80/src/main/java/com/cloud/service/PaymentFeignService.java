package com.cloud.service;

import com.cloud.entities.CommonResult;
import com.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by wuyue on 2020/9/2.
 */
@Component
// 到eureka上找CLOUD-PAYMENT-SERVICE微服务器
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}") // 这需要与8001和8002路径一直：/payment/get/{id}
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
