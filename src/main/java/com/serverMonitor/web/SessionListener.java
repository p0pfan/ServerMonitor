package com.serverMonitor.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		System.out.println("------------------session destroyed");
		ServletContext ctx=hse.getSession().getServletContext();
		ctx.setAttribute("cpuUsage", null);
		ctx.setAttribute("memUsage", null);
		ctx.setAttribute("serverIP", null);
	}

}
