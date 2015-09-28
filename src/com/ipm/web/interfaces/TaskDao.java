package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Task;

public interface TaskDao {

	public List<Task> getTasks(String username, int projectId);

	public void createTask(Task task);
}
