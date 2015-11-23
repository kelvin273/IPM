package com.ipm.web.interfaces.dao;

import java.util.List;

import com.ipm.web.dto.Project;

public interface ProjectDao {

	public List<Project> getProjects(String username);

	public void createProject(Project project);

	public void updateProject(Project project);

	public void removeProject(Project project);

	public Project getProject(String projectId);
}
