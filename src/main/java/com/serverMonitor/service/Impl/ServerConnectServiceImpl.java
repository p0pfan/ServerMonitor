package com.serverMonitor.service.Impl;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverMonitor.service.ServerConnectService;
import com.serverMonitor.util.MyUserInfo;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;

@Service("serverConnect")
public class ServerConnectServiceImpl implements ServerConnectService {
    public static final int DEFAULT_SSH_PORT = 22;

	public Session getServerConnection(final String ipAddress, 
			final String username, final String password) throws JSchException{
		JSch jsch=new JSch();
		Session session=null;
		try{
			session= jsch.getSession(username, ipAddress, DEFAULT_SSH_PORT);
			session.setPassword(password);
			session.setUserInfo(new MyUserInfo());
			session.connect();
			
		} catch (Exception e) {
			session=null;
			
		}
    	System.out.println("connecting");
		
		return session;
	}
	
}
