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

import com.ipm.web.dto.Project;
import com.ipm.web.impl.mappers.ProjectMapper;
import com.ipm.web.interfaces.dao.ProjectDao;

public class JdbcProjectDao implements ProjectDao {

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
	public List<Project> getProjects(String username) {
		List<Project> products = jdbcTemplate.query(
				"select id, name, username from projects", new ProjectMapper());
		return products;
	}

	@Override
	public void createProject(final Project project) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO projects(name, username) values(?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, project.getName());
				ps.setString(2, project.getUsername());
				return ps;
			}
		}, holder);
		Long projectId = holder.getKey().longValue();
		project.setId(projectId);
	}

	@Override
	public void updateProject(Project project) {
		jdbcTemplate.update(
				"UPDATE projects set name=? WHERE id=? and username=?",
				project.getName(), project.getId(), project.getUsername());

	}

	@Override
	public void removeProject(Project project) {
		jdbcTemplate.update("DELETE from projects WHERE id=? and username=?",
				project.getId(), project.getUsername());
	}

	@Override
	public Project getProject(String projectId) {
		return jdbcTemplate.query(
				"select id, name, username from projects where id=?",
				new String[]{projectId}, new ProjectMapper()).get(0);
	}

}
