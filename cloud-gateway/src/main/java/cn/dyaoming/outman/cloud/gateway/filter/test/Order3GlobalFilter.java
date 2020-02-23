package cn.dyaoming.outman.cloud.gateway.filter.test;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 参数处理过滤器
 */
//@Slf4j
//@Component
public class Order3GlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uri = exchange.getRequest().getURI().getPath();
        System.out.println("第" + getOrder() + "个filter,地址:" + uri);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 3;
    }

}