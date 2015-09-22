package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Project;

public interface ProjectDao {

	public List<Project> getProjects(String username);

	public void createProject(String username, Project project);
}
