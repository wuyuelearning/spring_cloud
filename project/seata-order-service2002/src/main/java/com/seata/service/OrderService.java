package com.seata.service;

import com.seata.domain.CommonResult;
import com.seata.domain.Order;

/**
 * Created by wuyue on 2020/10/13.
 */
public interface OrderService {
    CommonResult create  (Order order);
}
