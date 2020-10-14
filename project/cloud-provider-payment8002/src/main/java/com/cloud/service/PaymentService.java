package com.cloud.service;

import com.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wuyue on 2020/8/21.
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
