package com.ipm.web.impl.dao;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

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
	public void createProject(Project project) {
		jdbcTemplate.update("INSERT INTO projects(name, username) values(?,?)",
				project.getName(), project.getUsername());
	}

	@Override
	public void updateProject(Project project) {
		jdbcTemplate.update("UPDATE projects set name=? WHERE id=? and username=?",
				project.getName(), project.getId(), project.getUsername());
		
	}
	
	@Override
	public void removeProject(Project project) {
		jdbcTemplate.update("DELETE from projects WHERE id=? and username=?",
				project.getId(), project.getUsername());
	}

}
