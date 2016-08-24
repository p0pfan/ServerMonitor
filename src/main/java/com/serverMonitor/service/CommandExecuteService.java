package com.serverMonitor.service;


import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverMonitor.model.InfoBean;

public interface CommandExecuteService {
	public InfoBean getStatusInfo(String type, Session jschSession) throws JSchException;

}
