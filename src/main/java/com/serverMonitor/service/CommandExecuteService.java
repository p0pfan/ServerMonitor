package com.serverMonitor.service;

import java.util.Vector;

import com.jcraft.jsch.Session;

public interface CommandExecuteService {
	public void shutDownChannel();
	public  Vector<String>  execute(
			Session session,final String command);

}
