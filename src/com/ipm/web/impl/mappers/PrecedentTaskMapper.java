package com.ipm.web.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipm.web.dto.Task;

public class PrecedentTaskMapper implements RowMapper<Task> {
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task s = new Task();
		s.setId(rs.getInt("id"));
		s.setName(rs.getString("name"));
		s.setEffort(rs.getFloat("effort"));
		s.setExclusive(rs.getBoolean("exclusive"));
		return s;
	}
}

