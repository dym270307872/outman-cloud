//package com.pactera.madp.cis.gateway.config;
//
//import com.pactera.madp.cis.gateway.server.GatewayFeignServer;
//import com.pactera.madp.cis.model.DisableInterfaceInfo;
//import com.pactera.madp.cis.model.RegisterInterfaceInfo;
//import com.pactera.madp.cis.model.SuspendInterfaceInfo;
//
//import java.util.ArrayList;
//import java.util.List;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//
///**
// * 初始化网关数据
// */
//@Slf4j
//@Component
//public class InitGatewayData implements ApplicationRunner {
//
//	/** gatewayFeignServer */
//	@Autowired
//	private GatewayFeignServer gatewayFeignServer;
//	/** 注册接口列表查询 */
//	public  static List<RegisterInterfaceInfo> registInterList;
//	/** 禁用接口列表 */
//	public  static List<DisableInterfaceInfo> disableInterList;
//	/** 临时禁用接口列表 */
//	public  static List<SuspendInterfaceInfo> suspendInterList;
//
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		log.info("-------------------------开始初始化网关数据-------------------------");
//		registInterList = gatewayFeignServer.getRegistInterList().getData();
//		//非空判断，防止查询数据为空时出现空指针
//		if (registInterList == null) {
//			registInterList = new ArrayList<>();
//		}
//		disableInterList = gatewayFeignServer.getDisableInterList().getData();
//		if (disableInterList == null) {
//			disableInterList = new ArrayList<>();
//		}
//		suspendInterList = gatewayFeignServer.getSuspendInterList().getData();
//		if (suspendInterList == null) {
//			suspendInterList = new ArrayList<>();
//		}
//		log.info("注册接口列表 = " + registInterList.toString());
//		log.info("禁用接口列表 = " + disableInterList.toString());
//		log.info("临时禁用接口列表 = " + suspendInterList.toString());
//		log.info("-------------------------初始化网关数据完成-------------------------");
//	}
//}
