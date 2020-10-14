package filter;

import cn.hutool.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * Created by wuyue on 2020/9/10.
 */
@Component
@Slf4j
public class MyLogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("MyLogFilter:" + new Date());

        String n = exchange.getRequest().getQueryParams().getFirst("uname");
        if (n == null) {
            log.info("uname is  null");
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.valueOf(HttpStatus.HTTP_NOT_ACCEPTABLE));
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
