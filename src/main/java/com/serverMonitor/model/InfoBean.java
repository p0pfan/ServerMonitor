package com.serverMonitor.model;

import java.util.HashMap;
import java.util.Map;

public class InfoBean {
	private Status status;
	private Map<String,String> info;
	private String message;
	
	
	public InfoBean(){
		this.status=Status.failed;
		this.info = new HashMap<String,String>();
		
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	

	public Map<String, String> getInfo() {
		return info;
	}

	public void setInfo(Map<String, String> info) {
		this.info = info;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
