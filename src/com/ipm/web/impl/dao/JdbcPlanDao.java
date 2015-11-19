package com.ipm.web.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ipm.web.dto.Plan;
import com.ipm.web.interfaces.dao.PlanDao;

public class JdbcPlanDao implements PlanDao {

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
	public List<Plan> getPlans(String username, int projectId) {
//		List<Plan> resources = jdbcTemplate.query(
//				"select r.id, r.name, r.projectId, r.salary, u.username, r.maxDedication from resources r, projects p, users u where r.projectId = ? and r.projectID=p.id and u.username = p.username and u.username = ?",
//				new PlanMapper(), projectId, username);
		List<Plan> resources = new ArrayList<Plan>();
		return resources;
	}

	@Override
	public void createPlan(final Plan plan) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO plans(name, salary, maxDedication,projectId) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, plan.getName());
				ps.setLong(4, plan.getProjectId());
				return ps;
			}
		}, holder);

		Long resourceId = holder.getKey().longValue();

//		for (Skill skill : plan.getSkills()) {
//			jdbcTemplate.update("INSERT INTO skillPlans(resourceId, skillId) values(?,?)", resourceId,
//					skill.getId());
//		}

	}
	
	@Override
	public void removePlan(Plan resource) {
		jdbcTemplate
				.update("DELETE FROM resources WHERE id=? and projectId IN (SELECT p.id from projects p, users u where u.username=p.username and p.id = ? and u.username=? )",
						resource.getId(), resource.getProjectId(),
						resource.getUsername());
	}

	private static class PlanMapper implements RowMapper<Plan> {
		public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {
			Plan s = new Plan();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setProjectId(rs.getInt("projectId"));
			s.setUsername(rs.getString("username"));
			return s;
		}
	}

}
