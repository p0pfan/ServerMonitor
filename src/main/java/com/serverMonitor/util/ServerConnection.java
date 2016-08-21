package com.serverMonitor.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverMonitor.model.User;

public class ServerConnection {
	
	public static final int DEFAULT_SSH_PORT = 22;
	public static Logger logger = Logger.getLogger(ServerConnection. class );

	public static Session getServerConnection(User user) throws Exception{
		JSch jsch = new JSch();
		String username = user.getUserName();
		String ipAddress = user.getServerIp();
		String password = user.getPassword();
		Session session = null;
		try{
			session= jsch.getSession(username, ipAddress, DEFAULT_SSH_PORT);
			session.setPassword(password);
			session.setUserInfo(new MyUserInfo());
			session.connect();
			logger.info("server connected!");
			return session;
		} catch (JSchException e) {
			session=null;
			logger.info("error to connect server",e);
			throw new Exception();
			
		}
		
	}
	public static void main(String[] args) {
		User user =new User();
		user.setUserName("root");
		user.setPassword("qq494335589");
		user.setServerIp("23.89.13.184");
		Vector<String> stdout=new Vector<String>();

		try {
			Session sesion = getServerConnection(user);
			Channel channel = sesion.openChannel("exec");
			((ChannelExec) channel).setCommand("ip addr");
		
			BufferedReader input = new BufferedReader(new InputStreamReader(channel
                .getInputStream()));

			channel.connect();


			// Get the output of remote command.
			String line;
			while ((line = input.readLine()) != null) {
				stdout.add(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}