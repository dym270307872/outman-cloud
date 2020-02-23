//package com.pactera.madp.cis.gateway.server;
//
//import com.pactera.madp.cis.model.*;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * madp-cis-policy Feign
// */
//@Component
//@FeignClient(value = "madp-cis-policy", path = "/api/gateWayPolicyService")
//public interface GatewayFeignServer {
//
//	/**
//	 * <p>终端接口权限查询</p>
//	 */
//	@RequestMapping(value = "/getTerminalAuth", method = RequestMethod.POST)
//	DataApi getTerminalAuth(@RequestParam("terminalId") String terminalId, @RequestParam("interfaceId") String interfaceId);
//
//	/**
//	 * <p>终端参数策略查询</p>
//	 */
//	@RequestMapping(value = "/getTerminalParam", method = RequestMethod.POST)
//	BeanApi<TerminalParamInfo> getTerminalParam(@RequestParam("terminalId") String treminalId, @RequestParam("custId") String custId);
//
//	/**
//	 * <p>注册接口列表查询</p>
//	 */
//	@RequestMapping(value = "/getRegistInterList", method = RequestMethod.POST)
//	ListBeanApi<RegisterInterfaceInfo> getRegistInterList();
//
//	/**
//	 * <p>禁用接口列表查询</p>
//	 */
//	@RequestMapping(value = "/getDisableInterList", method = RequestMethod.POST)
//	ListBeanApi<DisableInterfaceInfo> getDisableInterList();
//
//	/**
//	 * <p>临时禁用接口列表查询</p>
//	 */
//	@RequestMapping(value = "/getSuspendInterList", method = RequestMethod.POST)
//	ListBeanApi<SuspendInterfaceInfo> getSuspendInterList();
//
//}
