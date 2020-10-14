package com.seata.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wuyue on 2020/10/13.
 */
@Configuration
@MapperScan("com.seata.dao")
public class MyBatisConfig {
}
