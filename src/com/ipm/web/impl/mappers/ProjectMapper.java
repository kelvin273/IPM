package com.ipm.web.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipm.web.dto.Project;

public class ProjectMapper implements RowMapper<Project> {
	public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
		Project prod = new Project();
		prod.setId(rs.getInt("id"));
		prod.setName(rs.getString("name"));
		prod.setUsername(rs.getString("username"));
		return prod;
	}

}

