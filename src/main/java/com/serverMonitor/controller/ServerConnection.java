package com.serverMonitor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverMonitor.model.ResponseBean;
import com.serverMonitor.model.Status;
import com.serverMonitor.service.Impl.ServerConnectServiceImpl;

@Controller
public class ServerConnection {
	@Autowired
	private ServerConnectServiceImpl serverConnect;
	
	
	@RequestMapping(value="/connect", method = RequestMethod.GET)
	public String connect(HttpSession session){
		if (session.getAttribute("jschsession") != null)
			return "redirect:status/monitor";
		return "connect";
	}
	
	
	@RequestMapping(value = "/connect", method = RequestMethod.POST )
	public @ResponseBody ResponseBean login(@RequestParam("serverIp") String serverIp,
			@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		ResponseBean rb = new ResponseBean();
		Session jschsession =null;
		try {
			jschsession = serverConnect.getServerConnection
					(serverIp, username,password);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			rb.setMessage("error to connect Server");
		}
		if(jschsession!=null){
			session.setAttribute("jschsession", jschsession);
			session.getServletContext().setAttribute("serverIP", serverIp);
			rb.setStatus(Status.success);
		}
		return rb;
			
	}
		
	
	
	

}
