//package com.pactera.madp.cis.gateway.service;
//
//import com.pactera.madp.cis.model.BaseApi;
//import com.pactera.madp.cis.model.DataApi;
//
///**
// * 网关服务service
// */
//public interface GatewayService {
//
//	/**
//	 * 判断是否有权限
//	 * @param terminalId 终端ID
//	 * @param interfaceId 接口ID
//	 * @return boolean
//	 */
//	boolean terminalInterfacePermission(String terminalId, String interfaceId);
//
//	/**
//	 * 判断策略
//	 * @param terminalId 终端ID
//	 * @param custid 客户号
//	 * @return
//	 */
//	boolean inTerminalParameterStrategy(String terminalId, String custid);
//
//	/**
//	 * 判断是否在注册接口列表中
//	 * @param interfaceId 接口ID
//	 * @return 存在则返回true
//	 */
//	boolean inRegisterInterfaceList(String interfaceId);
//
//	/**
//	 * 判断是否在禁用接口列表中
//	 * @param interfaceId 接口ID
//	 * @return 存在则返回true
//	 */
//	DataApi inDisableInterfaceList(String interfaceId);
//
//	/**
//	 * 判断是否在临时禁用接口列表中
//	 * @param interfaceId 接口ID
//	 * @return 存在则返回true
//	 */
//	DataApi temporaryDisableInterfaceList(String interfaceId);
//
//}
