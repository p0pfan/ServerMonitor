package com.serverMonitor.model;



public class ServerUsage {
	private String serverIP;
	private double cpuUsage;
	private double memUsage;
	
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	
	public double getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public double getMemUsage() {
		return memUsage;
	}
	public void setMemUsage(double memUsage) {
		this.memUsage = memUsage;
	}
	
}
