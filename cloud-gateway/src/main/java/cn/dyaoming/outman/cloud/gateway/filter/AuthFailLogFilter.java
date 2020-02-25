package cn.dyaoming.outman.cloud.gateway.filter;


import cn.dyaoming.outman.cloud.gateway.common.AppAccessException;
import cn.dyaoming.outman.cloud.gateway.common.AuthFailLog;
import cn.dyaoming.outman.cloud.gateway.server.AuthFailLogServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


/**
 * <p>业务接口日志拦截器</p>
 */
//@Component
@Slf4j
public class AuthFailLogFilter implements GlobalFilter, Ordered {

	@Autowired
	private AuthFailLogServer authFailLogServer;



	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.error("AuthFailLogFilter运行");
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 30000;
	}
}
