//package com.serverMonitor.util;
//
//import java.util.Date;
//import java.util.Vector;
//
//import javax.servlet.ServletContext;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.web.context.ContextLoader;
//
//import com.serverMonitor.dao.MetricCollectionDao;
//import com.serverMonitor.dao.Impl.MetricCollectionDaoImpl;
//import com.serverMonitor.model.ServerUsage;
//
//
//
//
//public class QuartzJob extends QuartzJobBean
//{
//	
//    @Override
//    protected void executeInternal(JobExecutionContext context)
//            throws JobExecutionException {
//    	ServerUsage serverUsage=new ServerUsage();
//    	
//    	ServletContext  wac = ContextLoader.getCurrentWebApplicationContext().getServletContext(); 
//    	MetricCollectionDao metricCollection=(MetricCollectionDao) 
//    			ContextLoader.getCurrentWebApplicationContext().getBean("metricCollection");
//    	
//    	Vector<String> cpuUsage=(Vector<String>) wac.getAttribute("cpuUsage");
//    	Vector<String> memUsage=(Vector<String>) wac.getAttribute("memUsage");
//    	String serverIP=(String)wac.getAttribute("serverIP");
//    	System.out.println(wac.getAttribute("serverIP"));
//		System.out.println(wac.getAttribute("cpuUsage"));
//		System.out.println(wac.getAttribute("memUsage"));
//    	if(cpuUsage!=null || memUsage!=null ||serverIP!=null){
//    		
//    		String cpu=cpuUsage.get(0);
//    		String mem=memUsage.get(0);
//    		serverUsage.setServerIP(serverIP);
//    		serverUsage.setCpuUsage(Double.parseDouble(cpu.substring(0, cpu.indexOf('%'))));
//    		serverUsage.setMemUsage(Double.parseDouble(mem.substring(0, mem.indexOf('%'))));
//    		metricCollection.saveData(serverUsage);
//    	}
//    }
//}