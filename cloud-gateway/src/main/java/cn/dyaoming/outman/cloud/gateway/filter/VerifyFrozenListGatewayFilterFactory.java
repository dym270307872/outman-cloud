package cn.dyaoming.outman.cloud.gateway.filter;

import cn.dyaoming.outman.cloud.gateway.common.BaseGatewayFilterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class VerifyFrozenListGatewayFilterFactory extends BaseGatewayFilterFactory {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 30500;
    }
}
