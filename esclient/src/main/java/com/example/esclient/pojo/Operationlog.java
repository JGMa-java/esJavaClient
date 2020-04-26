package com.example.esclient.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
  * @创建者： zhongh
  * @功能描述：操作日志表 
  * @时间：2019-07-02
  */
public class Operationlog {
	
	//columns START
	private Long id;   //id
	private String userAccount;   //操作人账号
	private String clientIp;   //客户端ip
	private String userName;   //操作人姓名
	private Integer operType;   //日志类型：1.系统web日志，2.其他
	private Long funId;   //菜单ID
	private String operUrl;   //操作的url
	private String operModule;   //opermodule
	private String operEvent;   //操作事件
	private String reqParam;   //请求参数信息
	private String reqType;   //请求方式：POST或者GET
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date operTime;	//操作时间
	private String remark1;   //remark1
	private String remark2;   //remark2
	private String remark3;   //remark3
	private String operResult;   //操作结果
	//columns END

	public Operationlog() {
	}

	public void setId(Long value) {
		this.id = value;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public Long getFunId() {
		return funId;
	}

	public void setFunId(Long funId) {
		this.funId = funId;
	}

	public String getOperUrl() {
		return operUrl;
	}

	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}

	public String getOperModule() {
		return operModule;
	}

	public void setOperModule(String operModule) {
		this.operModule = operModule;
	}

	public String getOperEvent() {
		return operEvent;
	}

	public void setOperEvent(String operEvent) {
		this.operEvent = operEvent;
	}

	public String getReqParam() {
		return reqParam;
	}

	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public java.util.Date getOperTime() {
		return operTime;
	}

	public void setOperTime(java.util.Date operTime) {
		this.operTime = operTime;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	public Long getId() {
		return id;
	}
	
}

