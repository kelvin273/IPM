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

import com.ipm.web.dto.Resource;
import com.ipm.web.dto.Skill;
import com.ipm.web.impl.mappers.ResourceMapper;
import com.ipm.web.impl.mappers.SkillMapper;
import com.ipm.web.interfaces.dao.ResourceDao;

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
	public List<Resource> getResources(String username, long projectId) {
		List<Resource> resources = jdbcTemplate.query(
				"select r.id, r.name, r.projectId, r.salary, u.username, r.maxDedication from resources r, projects p, users u where r.projectId = ? and r.projectID=p.id and u.username = p.username and u.username = ?",
				new ResourceMapper(), projectId, username);
		return resources;
	}

	@Override
	public void createResource(final Resource resource) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO resources(name, salary, maxDedication,projectId) values(?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, resource.getName());
				ps.setFloat(2, resource.getCost());
				ps.setFloat(3, resource.getMaxDedication());
				ps.setLong(4, resource.getProjectId());
				return ps;
			}
		}, holder);

		Long resourceId = holder.getKey().longValue();
		resource.setId(resourceId);
		saveResourceSkills(resource, resourceId);

	}

	private void saveResourceSkills(final Resource resource, Long resourceId) {
		for (Skill skill : resource.getSkills()) {
			jdbcTemplate.update("INSERT INTO skillResources(resourceId, skillId) values(?,?)", resourceId,
					skill.getId());
		}
	}

	@Override
	public void removeResource(Resource resource) {
		jdbcTemplate.update(
				"DELETE FROM resources WHERE id=? and projectId IN (SELECT p.id from projects p, users u where u.username=p.username and p.id = ? and u.username=? )",
				resource.getId(), resource.getProjectId(), resource.getUsername());
	}

	@Override
	public void getResource(Resource resource) {
		List<Resource> resources = jdbcTemplate.query(
				"select r.id, r.name, r.projectId, r.salary, u.username, r.maxDedication from resources r, projects p, users u where r.projectId = ? and r.projectID=p.id and u.username = p.username and u.username = ? and r.id = ?",
				new ResourceMapper(), resource.getProjectId(), resource.getUsername(), resource.getId());
		resource.setName(resources.get(0).getName());
		resource.setCost(resources.get(0).getCost());
		resource.setMaxDedication(resources.get(0).getMaxDedication());
		//gets the associated skills
		List<Skill> skills = jdbcTemplate
				.query("select s.id, s.name, s.projectID, u.username from skills s, projects p, users u, skillResources sr where sr.skillId = s.id and sr.resourceId= ? and s.projectId = ? and s.projectID=p.id and u.username = p.username and u.username = ?",
						new SkillMapper(), resource.getId(), resource.getProjectId(), resource.getUsername());
		resource.setSkills(skills);
		
	}

	@Override
	public void updateResource(Resource resource) {
		jdbcTemplate.update(
				"UPDATE resources set name=?, salary=?, maxDedication=? WHERE id=? and projectId IN (SELECT p.id from projects p, users u where u.username=p.username and p.id = ? and u.username=? )",
				resource.getName(), resource.getCost(), resource.getMaxDedication(), resource.getId(), resource.getProjectId(), resource.getUsername());
		
		jdbcTemplate.update("DELETE FROM skillResources where resourceId=?", resource.getId());
		saveResourceSkills(resource, resource.getId());
		
	}

}
