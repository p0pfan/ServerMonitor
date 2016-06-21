package com.serverMonitor.service.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverMonitor.service.CommandExecuteService;


@Service("commandExecute")
public class CommandExecuteServiceImpl implements CommandExecuteService{
	public static final int DEFAULT_SSH_PORT = 22;
	public  Session session;
	public Channel channel;
	
	public void shutDownChannel() {
		this.channel.disconnect();
	}
    

	public  Vector<String>  execute(Session session,final String command) {
		int returnCode=0;
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

        // Get the return code only after the channel is closed.
			if (channel.isClosed()) {
				returnCode = channel.getExitStatus();
				System.out.println("the return Code is "+returnCode);
			}

		} catch (JSchException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stdout;
	} 

}