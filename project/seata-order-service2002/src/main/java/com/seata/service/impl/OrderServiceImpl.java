package com.seata.service.impl;

import com.seata.dao.OrderDao;
import com.seata.domain.Order;
import com.seata.service.AccountService;
import com.seata.service.OrderService;
import com.seata.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wuyue on 2020/10/13.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;
    // 创建订单 - 减库存 - 减账户余额 - 修改订单状态
    @Override
    public void create(Order order) {
        log.info("--- 新建订单-----");
        orderDao.create(order);

        log.info("--调用库存 ，扣减库存---");
        storageService.decrease(order.getProductId(),order.getCount());

        log.info("---- 减账余额---");
        accountService.decrease(order.getUserId(),order.getMoney());

        log.info("----修改订单状态  0->1 ----");
        // 将userId对应的、status为0的用户的状态改为1 ，SQL里面有写
        orderDao.update(order.getUserId(),0);

        log.info("---- 下单结束----");
    }

}
