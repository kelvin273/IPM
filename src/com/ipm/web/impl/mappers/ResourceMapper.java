package com.ipm.web.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipm.web.dto.Resource;

public class ResourceMapper implements RowMapper<Resource> {
	public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
		Resource s = new Resource();
		s.setId(rs.getInt("id"));
		s.setName(rs.getString("name"));
		s.setProjectId(rs.getInt("projectId"));
		s.setUsername(rs.getString("username"));
		s.setCost(rs.getFloat("salary"));
		s.setMaxDedication(rs.getFloat("maxDedication"));
		return s;
	}
}
