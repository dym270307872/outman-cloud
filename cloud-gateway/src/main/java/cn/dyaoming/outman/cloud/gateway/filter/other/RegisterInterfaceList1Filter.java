package cn.dyaoming.outman.cloud.gateway.filter.other;

import cn.dyaoming.outman.cloud.gateway.common.AppAccessException;
import cn.dyaoming.outman.cloud.gateway.common.AuthFailLog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 注册接口列表filter
 */
@Slf4j
@Component
public class RegisterInterfaceList1Filter implements GlobalFilter, Ordered {



	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) throws AppAccessException{

		MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
		log.debug("Register"+params.getFirst("interfaceCode"));
		AuthFailLog authFailLog = new AuthFailLog();
		authFailLog.setTerminalId(Integer.valueOf(params.getFirst("terminalId")));
		if(params.getFirst("custId")!=null) {
			authFailLog.setCustId(Integer.valueOf(params.getFirst("custId")));
		}
		authFailLog.setServiceInterfaceIdentifier(params.getFirst("interfaceCode"));

		throw AppAccessException.fail(authFailLog,"当前用户不存在");
	}

	@Override
	public int getOrder() {
		return 30100;
	}
}
