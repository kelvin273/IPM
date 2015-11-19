package com.ipm.web.impl.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ipm.web.dto.Skill;
import com.ipm.web.impl.mappers.SkillMapper;
import com.ipm.web.interfaces.dao.SkillDao;

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

	@Override
	public void updateSkill(Skill skill) {
		jdbcTemplate.update("UPDATE skills set name=? where projectId=? and id=?",
				skill.getName(), skill.getProjectId(), skill.getId());
		
	}


}
