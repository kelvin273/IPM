package com.ipm.web.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipm.web.dto.Task;
import com.ipm.web.interfaces.TaskDao;
import com.ipm.web.interfaces.TaskManager;

public class TaskManagerImpl implements TaskManager {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private TaskDao taskDao;

	@Override
	public List<Task> getTasks(String username, int projectId) {
		return taskDao.getTasks(username, projectId);
	}

	@Override
	public void createTask(Task task) {
		taskDao.createTask(task);

	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public void removeTask(Task task) {
		taskDao.removeTask(task);
	}

	@Override
	public Task getTask(String username, int projectId, int taskId) {
		Task task = taskDao.getTask(username, projectId, taskId);
		List<Task> precedentTasks = taskDao.getPrecedentTasks(taskId);
		task.setPrecedentTasks(precedentTasks);
		return task;
	}

}
