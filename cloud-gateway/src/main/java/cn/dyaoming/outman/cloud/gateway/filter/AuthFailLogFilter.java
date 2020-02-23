package cn.dyaoming.outman.cloud.gateway.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.Timestamp;


/**
 * <p>业务接口日志拦截器</p>
 */
//@Component
//@Slf4j
public class AuthFailLogFilter implements GatewayFilter, Ordered {

//	@Autowired
//	private AuthFailLogServer authFailLogServer;

//
//		ServiceInterfaceLog serviceInterfaceLog = new ServiceInterfaceLog();
//
//		serviceInterfaceLog.setInterfaceType("0");
//		serviceInterfaceLog.setServiceInterfaceType(Integer.valueOf(interfaceId));
//		serviceInterfaceLog.setServiceInterfaceIdentifier("");
//		serviceInterfaceLog.setTerminalId(Integer.valueOf(terminalId));
//		serviceInterfaceLog.setRequestIp(ip);
//		serviceInterfaceLog.setRequestMac(mac);
//		serviceInterfaceLog.setRequestInfo(param);
//		serviceInterfaceLog.setResponseInfo(JSON.toJSONString(responseMsg));
//		serviceInterfaceLog.setRequestTime(new Timestamp(sendTime));
//		serviceInterfaceLog.setResponseTime(new Timestamp(responseTime));
//		serviceInterfaceLog.setDuration(responseTime - sendTime);
//
//		serviceInterfaceLogServer.logstore(serviceInterfaceLog);
//		//把返回值输出到客户端
//		ServletOutputStream out = response.getOutputStream();
//		out.write(content);
//		out.flush();
//	}
//
//	public String getRemoteAddress(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//			ip = request.getRemoteAddr();
//		}
//		return ip;
//	}


	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//		log.debug("进入了ServiceLogFilter");
		String interfaceId = exchange.getRequest().getQueryParams().getFirst("interfaceId");
		String param = exchange.getRequest().getQueryParams().getFirst("param");
		String terminalId = exchange.getRequest().getQueryParams().getFirst("terminalId");
		Long sendTime = System.currentTimeMillis();
		Mono<Void> response = chain.filter(exchange);

////		response.
//		AuthFailLog authFailLog = new AuthFailLog();
////		authFailLog.setCustId();
//		log.debug("记录授权失败日志");
//		authFailLogServer.logstore(authFailLog);
		return response;
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
