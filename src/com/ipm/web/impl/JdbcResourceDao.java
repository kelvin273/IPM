package com.ipm.web.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ipm.web.dto.Resource;
import com.ipm.web.interfaces.ResourceDao;

public class JdbcResourceDao implements ResourceDao {

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
	public List<Resource> getResources(String username, int projectId) {
		List<Resource> resources = jdbcTemplate
				.query("select r.id, r.name, r.projectId, r.salary, u.username, r.maxDedication from resources r, projects p, users u where r.projectId = ? and r.projectID=p.id and u.username = p.username and u.username = ?",
						new ResourceMapper(), projectId, username);
		return resources;
	}

	@Override
	public void createResource(Resource resource) {
		jdbcTemplate.update("INSERT INTO resources(name, salary, maxDedication,projectId) values(?,?,?,?)",
				resource.getName(), resource.getProjectId());
	}

	private static class ResourceMapper implements RowMapper<Resource> {
		public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
			Resource s = new Resource();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setProjectId(rs.getInt("projectId"));
			s.setUsername(rs.getString("username"));
			s.setSalary(rs.getFloat("salary"));
			s.setMaxDedication(rs.getFloat("maxDedication"));
			return s;
		}
	}

}
