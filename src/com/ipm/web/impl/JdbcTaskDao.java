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
				"select r.id, r.name, r.projectId, r.salary, u.username, r.maxDedication from tasks r, projects p, users u where r.projectId = ? and r.projectID=p.id and u.username = p.username and u.username = ?",
				new TaskMapper(), projectId, username);
		return tasks;
	}

	@Override
	public void createTask(final Task task) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO tasks(name, salary, maxDedication,projectId) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, task.getName());
				return ps;
			}
		}, holder);

		Long taskId = holder.getKey().longValue();


	}

	private static class TaskMapper implements RowMapper<Task> {
		public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
			Task s = new Task();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setProjectId(rs.getInt("projectId"));
			s.setUsername(rs.getString("username"));
			return s;
		}
	}

}
