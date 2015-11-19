package com.ipm.web.interfaces.dao;

import java.util.List;

import com.ipm.web.dto.Task;

public interface TaskDao {

	public List<Task> getTasks(String username, int projectId);

	public void createTask(Task task);

	public void removeTask(Task task);
	
	public void updateTask(Task task);

	public Task getTask(String username, int projectId, int taskId);
	
	public List<Task> getPrecedentTasks(int taskId);
}
