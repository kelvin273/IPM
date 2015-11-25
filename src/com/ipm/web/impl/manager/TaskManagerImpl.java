package com.ipm.web.impl.manager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipm.web.dto.Task;
import com.ipm.web.interfaces.TaskManager;
import com.ipm.web.interfaces.dao.TaskDao;

public class TaskManagerImpl implements TaskManager {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private TaskDao taskDao;

	@Override
	public List<Task> getTasks(String username, long projectId) {
		return taskDao.getTasks(username, projectId);
	}

	@Override
	public void createTask(Task task) {
		taskDao.createTask(task);

	}

	@Override
	public void updateTask(Task task) {
		taskDao.updateTask(task);

	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public void removeTask(Task task) {
		taskDao.removeTask(task);
	}

	@Override
	public void getTask(Task t) {
		taskDao.getTask(t);
		t.setPrecedentTasks(taskDao.getPrecedentTasks(t.getId()));
		t.setRequiredSkills(taskDao.getRequiredSkills(t.getId()));
		t.setResources(taskDao.getResources(t.getId()));
	}

}
