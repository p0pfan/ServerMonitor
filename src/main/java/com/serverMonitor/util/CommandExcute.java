package com.serverMonitor.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverMonitor.model.InfoBean;

public class CommandExcute {
	
	public  static Vector<String>  execute(Session session,final String command) {
		
		Channel channel = null;
		Vector<String> stdout=new Vector<String>();
		try{
			channel=session.openChannel("exec");

			((ChannelExec) channel).setCommand(command);

			BufferedReader input = new BufferedReader(new InputStreamReader(channel
                .getInputStream()));

			channel.connect();

			System.out.println("The remote command is: " + command);

			// Get the output of remote command.
			String line;
			while ((line = input.readLine()) != null) {
				stdout.add(line);
			}
			input.close();
			
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stdout;
	} 

}
