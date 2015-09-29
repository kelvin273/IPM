package com.ipm.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ipm.web.dto.Resource;
import com.ipm.web.dto.Skill;
import com.ipm.web.dto.Task;
import com.ipm.web.interfaces.TaskDao;

public class JdbcTaskDao implements TaskDao {

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
	public List<Task> getTasks(String username, int projectId) {
		List<Task> tasks = jdbcTemplate.query(
				"select t.id, t.name, t.effort, t.exclusive, t.projectId, u.username from tasks t, projects p, users u where t.projectId = ? and t.projectID=p.id and u.username = p.username and u.username = ?",
				new TaskMapper(), projectId, username);
		return tasks;
	}

	@Override
	public void createTask(final Task task) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO tasks(name, effort, exclusive,projectId) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, task.getName());
				ps.setFloat(2, task.getEffort());
				ps.setBoolean(3, task.isExclusive());
				ps.setLong(4, task.getProjectId());
				return ps;
			}
		}, holder);

		Long taskId = holder.getKey().longValue();
		
		for (Skill skill : task.getRequiredSkills()) {
			jdbcTemplate.update("INSERT INTO skillTasks(skillId, taskId) values(?,?)", skill.getId(),
					taskId);
		}
		
		for (Resource resource : task.getResources()) {
			jdbcTemplate.update("INSERT INTO resourceTasks(resourceId, taskId) values(?,?)", resource.getId(),
					taskId);
		}

	}
	
	@Override
	public void removeTask(Task task) {
		jdbcTemplate
				.update("DELETE FROM tasks WHERE id=? and projectId IN (SELECT p.id from projects p, users u where u.username=p.username and p.id = ? and u.username=? )",
						task.getId(), task.getProjectId(),
						task.getUsername());
	}

	private static class TaskMapper implements RowMapper<Task> {
		public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
			Task s = new Task();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setProjectId(rs.getInt("projectId"));
			s.setUsername(rs.getString("username"));
			s.setEffort(rs.getFloat("effort"));
			s.setExclusive(rs.getBoolean("exclusive"));
			return s;
		}
	}

}
