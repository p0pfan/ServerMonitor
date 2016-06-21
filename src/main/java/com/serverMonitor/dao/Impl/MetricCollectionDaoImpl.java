package com.serverMonitor.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.serverMonitor.dao.MetricCollectionDao;
import com.serverMonitor.model.ServerUsage;

@Repository("metricCollection")
public class MetricCollectionDaoImpl implements MetricCollectionDao{
	
	private static final String TABLE = "serverdata";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveData(ServerUsage usage) {
		final String sql = "insert into " + TABLE + 
					 "(serverIP, cpuUsage,memUsage) "
					 + "values(?,?,?)";
		

		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, usage.getServerIP());
				ps.setDouble(2, usage.getCpuUsage());
				ps.setDouble(3, usage.getMemUsage());
				return ps;
			}
		});
	}
}
