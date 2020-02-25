package cn.dyaoming.outman.cloud.gateway.common;

import java.sql.Timestamp;

public class AuthFailLog{

	private Integer logId;
	private String interfaceType;
	private Integer serviceInterfaceType;
	private String serviceInterfaceIdentifier;
	private Integer custId;
	private Integer terminalId;
	private String requestIp;
	private String requestMac;
	private String requestInfo;
	private Timestamp requestTime;
	private String authFailReason;


	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}


	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}


	public Integer getServiceInterfaceType() {
		return serviceInterfaceType;
	}

	public void setServiceInterfaceType(Integer serviceInterfaceType) {
		this.serviceInterfaceType = serviceInterfaceType;
	}


	public String getServiceInterfaceIdentifier() {
		return serviceInterfaceIdentifier;
	}

	public void setServiceInterfaceIdentifier(String serviceInterfaceIdentifier) {
		this.serviceInterfaceIdentifier = serviceInterfaceIdentifier;
	}


	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}


	public Integer getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}


	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}


	public String getRequestMac() {
		return requestMac;
	}

	public void setRequestMac(String requestMac) {
		this.requestMac = requestMac;
	}


	public String getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(String requestInfo) {
		this.requestInfo = requestInfo;
	}


	public Timestamp getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}


	public String getAuthFailReason() {
		return authFailReason;
	}

	public void setAuthFailReason(String authFailReason) {
		this.authFailReason = authFailReason;
	}

}
