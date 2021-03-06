package com.ipm.web.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
	public List<Skill> getSkills(String username, long projectId) {
		List<Skill> skills = jdbcTemplate
				.query("select s.id, s.name, s.projectID, u.username from skills s, projects p, users u where s.projectId = ? and s.projectID=p.id and u.username = p.username and u.username = ?",
						new SkillMapper(), projectId, username);
		return skills;
	}

	@Override
	public void createSkill(final Skill skill) {
		
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement(
								"INSERT INTO skills(name, projectId) values(?,?)",
								Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, skill.getName());
				ps.setFloat(2, skill.getProjectId());
				return ps;
			}
		}, holder);

		Long id = holder.getKey().longValue();
		
		skill.setId(id);
		
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

	@Override
	public void getSkill(Skill skill) {
		Skill skillAux =  jdbcTemplate
				.query("select s.id, s.name, s.projectID, u.username from skills s, projects p, users u where s.projectId = ? and s.projectID=p.id and u.username = p.username and u.username = ?",
						new SkillMapper(), skill.getProjectId(), skill.getUsername()).get(0);
		skill.setName(skillAux.getName());
	}


}
