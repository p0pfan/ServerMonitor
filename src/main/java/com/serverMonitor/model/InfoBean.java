package com.serverMonitor.model;

public class InfoBean {
	private Status status;
	private String info;
	private String message;
	
	public InfoBean(){
		this.status=Status.failed;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
