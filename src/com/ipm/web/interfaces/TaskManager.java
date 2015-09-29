package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Task;

public interface TaskManager {

    public List<Task> getTasks(String username, int projectId);
    
    public void createTask(Task task);

	public void removeTask(Task task);
}
