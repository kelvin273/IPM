package com.ipm.web.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ipm.web.dto.Skill;
import com.ipm.web.interfaces.SkillDao;

public class JdbcSkillDao implements SkillDao {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void init() {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Skill> getSkills(String username, int projectId) {
		List<Skill> skills = jdbcTemplate
				.query("select s.id, s.name, s.projectID, u.username from skills s, projects p, users u where s.projectId = ? and s.projectID=p.id and u.username = p.username and u.username = ?",
						new SkillMapper(), projectId, username);
		return skills;
	}

	@Override
	public void createSkill(Skill skill) {
		jdbcTemplate.update("INSERT INTO skills(name, projectId) values(?,?)",
				skill.getName(), skill.getProjectId());
	}

	@Override
	public void removeSkill(Skill skill) {
		jdbcTemplate
				.update("DELETE FROM skills WHERE id=? and projectId IN (SELECT p.id from projects p, users u where u.username=p.username and p.id = ? and u.username=? )",
						skill.getId(), skill.getProjectId(),
						skill.getUsername());
	}

	private static class SkillMapper implements RowMapper<Skill> {
		public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
			Skill s = new Skill();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setProjectId(rs.getInt("projectId"));
			s.setUsername(rs.getString("username"));
			return s;
		}
	}

}
