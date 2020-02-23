//package com.pactera.madp.cis.gateway.service.impl;
//
//import com.pactera.madp.cis.error.AppServiceException;
//import com.pactera.madp.cis.gateway.config.InitGatewayData;
//import com.pactera.madp.cis.gateway.server.GatewayFeignServer;
//import com.pactera.madp.cis.gateway.service.GatewayService;
//import com.pactera.madp.cis.model.*;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class GatewayServiceImpl implements GatewayService {
//
//	@Autowired
//	private GatewayFeignServer gatewayFeignServer;
//
//	@Override
//	public boolean terminalInterfacePermission(String terminalId, String interfaceId) {
//		boolean flag = false;
//		if (StringUtils.isNotEmpty(terminalId) && StringUtils.isNotEmpty(interfaceId)) {
//			DataApi dataApi = gatewayFeignServer.getTerminalAuth(terminalId, interfaceId);
//			flag = (boolean) dataApi.getData();
//		} else {
//			throw new AppServiceException("终端编号或者接口编号为空，terminalId=\"" + terminalId +"\",interfaceId=\"" + interfaceId + "\"");
//		}
//		return flag;
//	}
//
//	@Override
//	public boolean inTerminalParameterStrategy(String terminalId, String custid) {
//		//TODO 未开发暂时通过验证
//		return true;
//	}
//
//	@Override
//	public boolean inRegisterInterfaceList(String interfaceId) {
//		boolean flag = false;
//		if(StringUtils.isNotEmpty(interfaceId)){
//			for(RegisterInterfaceInfo registerInterfaceInfo : InitGatewayData.registInterList){
//				System.out.println("registerInterfaceInfo.getInterfaceId()="+registerInterfaceInfo.getInterfaceId()+",InitGatewayData.registInterList="+InitGatewayData.registInterList);
//				if (registerInterfaceInfo.getInterfaceId().equals(interfaceId)){
//					flag = true;
//				}
//			}
//		}
//		return flag;
//	}
//
//	@Override
//	public DataApi inDisableInterfaceList(String interfaceId) {
//		DataApi dataApi = new DataApi();
//		dataApi.setFlag(false);//默认不在列表中
//		if(StringUtils.isNotEmpty(interfaceId)) {
//			for (DisableInterfaceInfo disableInterfaceInfo : InitGatewayData.disableInterList) {
//				if(interfaceId.equals(disableInterfaceInfo.getInterfaceId())){
//					dataApi.setFlag(true);
//					dataApi.setData(disableInterfaceInfo);
//					break;
//				}
//			}
//		}
//		return dataApi;
//	}
//
//	@Override
//	public DataApi temporaryDisableInterfaceList(String interfaceId) {
//		DataApi dataApi = new DataApi();
//		dataApi.setFlag(false);//默认不在列表中
//		for(SuspendInterfaceInfo suspendInterfaceInfo : InitGatewayData.suspendInterList){
//			if (interfaceId.equals(suspendInterfaceInfo.getInterfaceId())){
//				dataApi.setFlag(true);
//				dataApi.setData(suspendInterfaceInfo);
//				break;
//			}
//		}
//		return dataApi;
//	}
//}
