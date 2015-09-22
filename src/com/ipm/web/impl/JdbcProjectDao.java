package com.ipm.web.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.ipm.web.dto.Project;
import com.ipm.web.interfaces.ProjectDao;

public class JdbcProjectDao extends SimpleJdbcDaoSupport implements ProjectDao{

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
	@Override
	public List<Project> getProjects(String username) {
		logger.info("Getting products!");
        List<Project> products = getSimpleJdbcTemplate().query(
                "select id, name, username from projects", 
                new ProjectMapper());
        return products;
	}

	@Override
	public void createProject(String username, Project project) {
		getSimpleJdbcTemplate().update("INSERT INTO projects(name, username) values(?,?)", project.getName(), username);
	}
	
	private static class ProjectMapper implements ParameterizedRowMapper<Project> {

        public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Project prod = new Project();
            prod.setId(rs.getInt("id"));
            prod.setName(rs.getString("name"));
            prod.setUsername(rs.getString("username"));
            return prod;
        }

    }

}
