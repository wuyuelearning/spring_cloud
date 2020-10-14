package config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.activity.ActivityCompletedException;

/**
 * Created by wuyue on 2020/9/9.
 */
@Configuration
public class GateWayConfig extends ActivityCompletedException {

    @Bean
    public RouteLocator locator1(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("only", r -> r.path("/guonei").uri("http://www.baidu.com")).build();
        return routes.build();

    }

}
