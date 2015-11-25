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
import com.ipm.web.dto.Task;
import com.ipm.web.impl.mappers.PrecedentTaskMapper;
import com.ipm.web.impl.mappers.ResourceMapper;
import com.ipm.web.impl.mappers.SkillMapper;
import com.ipm.web.impl.mappers.TaskMapper;
import com.ipm.web.interfaces.dao.TaskDao;

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
	public List<Task> getTasks(String username, long projectId) {
		List<Task> tasks = jdbcTemplate
				.query("select t.id, t.name, t.effort, t.exclusive, t.projectId, u.username from tasks t, projects p, users u where t.projectId = ? and t.projectID=p.id and u.username = p.username and u.username = ?",
						new TaskMapper(), projectId, username);
		return tasks;
	}

	@Override
	public List<Task> getPrecedentTasks(long taskId) {
		return jdbcTemplate
				.query("select t.id, t.name, t.effort, t.exclusive from tasks t, dependentTasks dt where t.id=dt.dependentTaskId and dt.taskId=?",
						new PrecedentTaskMapper(), taskId);

	}

	@Override
	public void createTask(final Task task) {
		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement(
								"INSERT INTO tasks(name, effort, exclusive,projectId) values(?,?,?,?)",
								Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, task.getName());
				ps.setFloat(2, task.getEffort());
				ps.setBoolean(3, task.isExclusive());
				ps.setLong(4, task.getProjectId());
				return ps;
			}
		}, holder);

		Long taskId = holder.getKey().longValue();

		task.setId(taskId);

		createTaskSkills(task, taskId);

		createTaskResources(task, taskId);

		createPrecedentTasks(task, taskId);

	}

	private void createPrecedentTasks(final Task task, Long taskId) {
		for (Task taskAux : task.getPrecedentTasks()) {
			jdbcTemplate
					.update("INSERT INTO dependentTasks(taskId, dependentTaskId) values(?,?)",
							taskId, taskAux.getId());
		}
	}

	private void createTaskResources(final Task task, Long taskId) {
		for (Resource resource : task.getResources()) {
			jdbcTemplate
					.update("INSERT INTO resourceTasks(resourceId, taskId) values(?,?)",
							resource.getId(), taskId);
		}
	}

	private void createTaskSkills(final Task task, Long taskId) {
		for (Skill skill : task.getRequiredSkills()) {
			jdbcTemplate.update(
					"INSERT INTO skillTasks(skillId, taskId) values(?,?)",
					skill.getId(), taskId);
		}
	}

	@Override
	public void removeTask(Task task) {
		jdbcTemplate
				.update("DELETE FROM tasks WHERE id=? and projectId IN (SELECT p.id from projects p, users u where u.username=p.username and p.id = ? and u.username=? )",
						task.getId(), task.getProjectId(), task.getUsername());
	}

	@Override
	public void updateTask(Task task) {
		jdbcTemplate
				.update("UPDATE tasks set name=?, effort=?, exclusive=? WHERE id=? and projectId IN (SELECT p.id from projects p, users u where u.username=p.username and p.id = ? and u.username=? )",
						task.getName(), task.getEffort(), task.isExclusive(),
						task.getId(), task.getProjectId(), task.getUsername());

		jdbcTemplate.update("DELETE from dependentTasks where taskId=? ",
				task.getId());
		createPrecedentTasks(task, task.getId());

		jdbcTemplate.update("DELETE from resourceTasks where taskId=? ",
				task.getId());
		createTaskResources(task, task.getId());

		jdbcTemplate.update("DELETE from skillTasks where taskId=? ",
				task.getId());
		createTaskSkills(task, task.getId());

	}

	@Override
	public void getTask(Task t) {
		List<Task> tasks = jdbcTemplate
				.query("select t.id, t.name, t.effort, t.exclusive, t.projectId, u.username from tasks t, projects p, users u where t.projectId = ? and t.projectID=p.id and u.username = p.username and u.username = ? and t.id = ?",
						new TaskMapper(), t.getProjectId(), t.getUsername(),
						t.getId());
		Task task = tasks.get(0);
		t.setEffort(task.getEffort());
		t.setExclusive(task.isExclusive());
		t.setName(task.getName());

	}

	@Override
	public List<Skill> getRequiredSkills(long taskId) {
		return jdbcTemplate
				.query("select s.id, s.name, s.projectID, s.projectID, u.username from skills s, skilltasks st, users u, projects p where st.skillId=s.id and st.taskId=? and u.username = p.username and p.id = s.projectID",
						new SkillMapper(), taskId);
	}

	@Override
	public List<Resource> getResources(long taskId) {
		return jdbcTemplate
				.query("select r.id, r.name, r.projectId, r.salary, u.username, r.maxDedication from resources r, projects p, users u, resourceTasks rs where rs.taskId=? and rs.resourceId=r.id and r.projectID=p.id and u.username = p.username ",
						new ResourceMapper(), taskId);
	}

}
