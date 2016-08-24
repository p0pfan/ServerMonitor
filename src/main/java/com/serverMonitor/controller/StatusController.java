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
import com.serverMonitor.model.InfoBean;
import com.serverMonitor.service.CommandExecuteService;



@Controller
@RequestMapping("/status")
public class StatusController {
	
	@Autowired
	private CommandExecuteService commandExecute;
	
	@RequestMapping("/monitor")
	public String getSatus(HttpSession session){
		Session jschsession=(Session) session.getAttribute("jschsession");
		if(jschsession==null){
			return "redirect:/connect";
		}
		
		return "monitor";
	}
	
	@RequestMapping(value = "/info" , method = RequestMethod.GET)
	public @ResponseBody InfoBean getInfo(@RequestParam("item") String item, HttpSession httpSession){
		InfoBean ib = new InfoBean();
		if (httpSession.getAttribute("jschsession") == null){
			ib.setMessage("Server is not connected!");
			return ib;
		}
		Session jschSession = (Session) httpSession.getAttribute("jschsession");
		try {
			ib = commandExecute.getStatusInfo(item,jschSession);
		} catch (JSchException e) {
			ib.setMessage(item+" info con not be got!");
			e.printStackTrace();
			return ib;
		}
		return ib;
	}

}
