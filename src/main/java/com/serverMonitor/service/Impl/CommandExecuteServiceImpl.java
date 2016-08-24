package com.serverMonitor.service.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		Map<String,String> rate =new HashMap<>();
		if(item.equals("cpu")){			
			Vector<String> stdout = CommandExcute.execute(session,CPU_COMMAND);
			rate.put("cpu",stdout.get(0));
		}
		if(item.equals("memory")){
			Vector<String> stdout = CommandExcute.execute(session,MEM_COMMAND);
			rate.put("mem",stdout.get(0));
		}
		if(item.equals("all")){
			Vector<String> cpu = CommandExcute.execute(session,CPU_COMMAND);
			rate.put("cpu",cpu.get(0));
			Vector<String> mem = CommandExcute.execute(session,MEM_COMMAND);
			rate.put("mem",mem.get(0));
		}
		ib.setInfo(rate);
		ib.setStatus(Status.success);
		return ib;
	}

}