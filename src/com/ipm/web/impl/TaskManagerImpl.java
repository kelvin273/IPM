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
		return taskDao.getTasks(username,projectId);
	}

	@Override
	public void createTask(Task task) {
		taskDao.createTask(task);

	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

}