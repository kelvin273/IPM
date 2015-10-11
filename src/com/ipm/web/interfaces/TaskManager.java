package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Task;

public interface TaskManager {

    public List<Task> getTasks(String username, int projectId);
    
    public Task getTask(String username, int projectId, int taskId);
    
    public void createTask(Task task);

	public void removeTask(Task task);
}
