package com.seata.dao;

import com.seata.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wuyue on 2020/10/13.
 */
@Mapper
public interface OrderDao {

    // 新建订单
    void create(Order order);

    // 修改订单状态 0 -> 1
    void update(@Param("userId") Long userId,@Param("status") Integer status);


}
