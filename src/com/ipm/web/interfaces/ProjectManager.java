package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Project;

public interface ProjectManager {

    public List<Project> getProjects(String username);
    
    public void createProject(Project project);
    public void updateProject(Project project);
    public void removeProject(Project project);
}
