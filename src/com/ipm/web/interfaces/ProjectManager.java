package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Project;

public interface ProjectManager {

    public List<Project> getProjects();
    
    public void createProject(Project project);
}
