package com.serverMonitor.service.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverMonitor.model.InfoBean;
import com.serverMonitor.model.Status;
import com.serverMonitor.service.CommandExecuteService;
import com.serverMonitor.util.CommandExcute;


@Service("commandExecute")
public class CommandExecuteServiceImpl implements CommandExecuteService{
	public static final int DEFAULT_SSH_PORT = 22;
	
	private final static String CPU_COMMAND =
			" grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage \"%\"}'";
	
	private final static String MEM_COMMAND =
			"free | grep Mem | awk '{usage= $3/$2 * 100.0} END {print usage \"%\"} '";
	
	
	@Autowired
	private ServerConnectServiceImpl serverConnectService;
	
	
	
	public InfoBean getStatusInfo(String item , Session session) throws JSchException{
		InfoBean ib = new InfoBean();
		Vector<String> stdout =new Vector<>();
		if(item.equals("cpu")){			
			stdout = CommandExcute.execute(session,CPU_COMMAND);
		}
		if(item.equals("memory")){
			stdout = CommandExcute.execute(session,MEM_COMMAND);
		}
		ib.setInfo(stdout.get(0));
		ib.setStatus(Status.success);
		return ib;
	}

}