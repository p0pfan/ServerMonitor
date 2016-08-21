package com.serverMonitor.model;

public class ResponseBean {
	private Status status;
	private String message;
	
	
	public ResponseBean() {
		status = Status.failed;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
