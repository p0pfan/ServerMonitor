package com.serverMonitor.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverMonitor.model.ServerUsage;
import com.serverMonitor.service.CommandExecuteService;
import com.serverMonitor.service.Impl.CommandExecuteServiceImpl;
import com.serverMonitor.service.Impl.ServerConnectServiceImpl;



@Controller
@RequestMapping("/status")
public class StatusController {
	private final static int SUCCESS=1;
	
	private final static int FAIL=0;
	
	private final static String CPU_COMMAND=
			" grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage \"%\"}'";
	
	private final static String MEM_COMMAND=
			"free | grep Mem | awk '{usage= $3/$2 * 100.0} END {print usage \"%\"} '";
	
	private static final String FALSE = "false";
;
	@Autowired
	private CommandExecuteService commandExecute;

	@Autowired
	private ServerConnectServiceImpl serverConnect;
	

	@RequestMapping(value="/connect", method = RequestMethod.GET)
	public String connect(HttpSession session){
		if (session.getAttribute("session") != null)
			return "redirect:status/monitor";
		return "connect";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestParam("serverIp") String serverIp,
			@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		
		System.out.println(serverIp+"-----"+ username);
		Map<String, Object> ret=new HashMap<String, Object>();
		
		try {
			Session jschsession=(Session) session.getAttribute("jschsession");
			if(jschsession!=null){
				System.out.println("disconnect jschsession");
				commandExecute.shutDownChannel();
				jschsession.disconnect();
			}
			jschsession= serverConnect.getServerConnection
					(serverIp, username,password);
			if(jschsession!=null){
				session.setAttribute("jschsession", jschsession);
				session.getServletContext().setAttribute("serverIP", serverIp);
				ret.put("status", SUCCESS);
			}else{
				ret.put("status", FAIL);
				ret.put("serverIP", serverIp);
				
			}
		} catch (JSchException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	@RequestMapping("/monitor")
	public ModelAndView getSatus(HttpSession session){
		ModelAndView mav = new ModelAndView();

		
		Session jschsession=(Session) session.getAttribute("jschsession");
		if(jschsession==null){
			mav.setViewName("connect");
			mav.addObject("serverIP", 
					session.getServletContext().getAttribute("serverIP"));
			return mav;
		}
		
		Vector<String> cpu_status=commandExecute.execute(jschsession,CPU_COMMAND);
		Vector<String> mem_status=commandExecute.execute(jschsession,MEM_COMMAND);
		
		mav.addObject("cpu_status", cpu_status);
		mav.addObject("mem_status", mem_status);
		mav.setViewName("monitor");
		session.getServletContext().setAttribute("cpuUsage", cpu_status);
		session.getServletContext().setAttribute("memUsage", mem_status);
		
		return mav;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public Map<String,String> getStatus(HttpSession session){
		Map<String,String> map=new HashMap<String,String>();
		Session jschsession=(Session) session.getAttribute("jschsession");
		if(jschsession!=null){

			Vector<String> cpu_status=commandExecute.execute(jschsession,CPU_COMMAND);
			Vector<String> mem_status=commandExecute.execute(jschsession,MEM_COMMAND);
			session.getServletContext().setAttribute("cpuUsage", cpu_status);
			session.getServletContext().setAttribute("memUsage", mem_status);
			
			map.put("cpu_status", cpu_status.get(0));
			map.put("mem_status", mem_status.get(0));
			
		}else{
			map.put("status", FALSE);
		}
		return map; 
	}
	@RequestMapping("/disconnect")
	public String logout(HttpSession session) {
		if (session!=null){
			
			commandExecute.shutDownChannel();
			((Session)session.getAttribute("jschsession")).disconnect();
			session.getServletContext().setAttribute("cpuUsage", null);
			session.getServletContext().setAttribute("memUsage", null);
			session.getServletContext().setAttribute("serverIP", null);
			session.invalidate();
		}
		return "redirect:/status/connect";
	}
}
