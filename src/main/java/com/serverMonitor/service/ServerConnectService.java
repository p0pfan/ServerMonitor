package com.serverMonitor.service;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public interface ServerConnectService {
	public Session getServerConnection(final String ipAddress, 
			final String username, final String password) throws JSchException;
}
