//package com.pactera.madp.cis.gateway.filter;
//
//import com.pactera.madp.cis.gateway.config.InitGatewayData;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * 终端参数策略filter
// */
//@Component
//public class TerminalParameterStrategyFilter implements GlobalFilter, Ordered {
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		System.out.println("第二个filter");
//		//TODO 未开发
//		return chain.filter(exchange);
//	}
//
//	@Override
//	public int getOrder() {
//		return 2;
//	}
//}
