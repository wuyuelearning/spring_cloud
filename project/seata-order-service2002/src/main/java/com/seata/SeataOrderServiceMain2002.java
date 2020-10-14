package com.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by wuyue on 2020/10/13.
 */
// 先启动nacos（F:\soft\nacos\nacos-server-1.1.4\nacos\bin\startup.cmd）
// 再启动seata（‪F:\soft\seata\seata-server-0.9.0\seata\bin）
// 排除数据源自动创建，使用DataSourceProxyConfig
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@EnableDiscoveryClient
public class SeataOrderServiceMain2002 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderServiceMain2002.class,args);
    }
}
