package com.ipm.web.interfaces.dao;

import java.util.List;

import com.ipm.web.dto.Resource;
import com.ipm.web.dto.Skill;
import com.ipm.web.dto.Task;

public interface TaskDao {

	public List<Task> getTasks(String username, long projectId);

	public void createTask(Task task);

	public void removeTask(Task task);
	
	public void updateTask(Task task);

	public List<Task> getPrecedentTasks(long taskId);
	
	public List<Skill> getRequiredSkills(long taskId);
	
	public List<Resource> getResources(long taskId);

	public void getTask(Task t);

	
}
