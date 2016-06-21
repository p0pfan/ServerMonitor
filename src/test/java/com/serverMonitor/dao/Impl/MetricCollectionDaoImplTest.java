package com.serverMonitor.dao.Impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.serverMonitor.dao.MetricCollectionDao;
import com.serverMonitor.model.ServerUsage;



public class MetricCollectionDaoImplTest {
	
	@Test
	public void test() {
		ApplicationContext ac =new  ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		MetricCollectionDao acd=(MetricCollectionDao) ac.getBean("metricCollection");
		System.out.println(acd);
		ServerUsage usage=new ServerUsage();
		usage.setServerIP("172.20.204.18");
		usage.setMemUsage(18.5);
		usage.setCpuUsage(0.15);
		acd.saveData(usage);
	}

}
