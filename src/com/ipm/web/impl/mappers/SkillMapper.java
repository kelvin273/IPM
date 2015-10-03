package com.ipm.web.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipm.web.dto.Skill;

public class SkillMapper implements RowMapper<Skill> {
	public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
		Skill s = new Skill();
		s.setId(rs.getInt("id"));
		s.setName(rs.getString("name"));
		s.setProjectId(rs.getInt("projectId"));
		s.setUsername(rs.getString("username"));
		return s;
	}
}
