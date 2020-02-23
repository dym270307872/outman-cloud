package cn.dyaoming.outman.cloud.gateway.filter.test;


import cn.dyaoming.outman.cloud.gateway.common.BaseGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数处理过滤器
 */
//@Slf4j
@Component
public class Order2GatewayFilterFactory extends BaseGatewayFilterFactory {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uri = exchange.getRequest().getURI().getPath();
        System.out.println("第" + getOrder() + "个filter,地址:" + uri);
        return chain.filter(exchange);
    }


}