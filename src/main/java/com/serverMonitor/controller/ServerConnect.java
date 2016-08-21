package com.serverMonitor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serverMonitor.model.ResponseBean;
import com.serverMonitor.model.User;

@Controller
public class ServerConnect {
	
	@RequestMapping(value = "/connect" , method = RequestMethod.POST)
	public @ResponseBody ResponseBean serverConnect(@RequestBody User user, HttpSession session){
		
		return null;
		
	}
	

}
