package com.serverMonitor.dao;


import com.serverMonitor.model.ServerUsage;

public interface MetricCollectionDao {
	public void saveData(ServerUsage usage);
}
