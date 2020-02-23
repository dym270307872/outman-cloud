//package com.pactera.madp.cis.gateway.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.pactera.madp.cis.base.common.CommonStaticVariable;
//import com.pactera.madp.cis.error.AppServiceException;
//import com.pactera.madp.cis.gateway.service.GatewayService;
//import com.pactera.madp.cis.model.BaseApi;
//import com.pactera.madp.cis.model.DataApi;
//import com.pactera.madp.cis.model.DisableInterfaceInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * 禁用接口列表filter
// */
//@Slf4j
//@Component
//public class DisableInterfaceListFilter implements GlobalFilter, Ordered {
//
//	@Autowired
//	private GatewayService getewayService;
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		try {
//			System.out.println("第四个filter");
//			String interfaceId = exchange.getRequest().getQueryParams().getFirst("interfaceId");
//			if (StringUtils.isNotEmpty(interfaceId)) {
//				DataApi dataApi = getewayService.inDisableInterfaceList(interfaceId);
//				if (dataApi.isFlag()) {
//					DisableInterfaceInfo disableInterfaceInfo = (DisableInterfaceInfo) dataApi.getData();
//					throw new AppServiceException("该接口已禁用，interfaceId=\"" + interfaceId + "\",禁用原因:" + disableInterfaceInfo.getAuditReason());
//				} else {
//					return chain.filter(exchange);
//				}
//			} else {
//				throw new AppServiceException("接口编号为空，interfaceId=\"" + interfaceId + "\"");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			ServerHttpResponse response = exchange.getResponse();
//			//设置headers
//			HttpHeaders httpHeaders = response.getHeaders();
//			httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
//			httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
//			//设置body
//			BaseApi baseApi = new BaseApi(false, CommonStaticVariable.ERROR_CODE, e.getMessage());
//			DataBuffer bodyDataBuffer = response.bufferFactory().wrap(JSON.toJSONString(baseApi).getBytes());
//			return response.writeWith(Mono.just(bodyDataBuffer));
//		}
//	}
//
//	@Override
//	public int getOrder() {
//		return 4;
//	}
//}
