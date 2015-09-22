package com.ipm.web.impl;

import java.util.ArrayList;
import java.util.List;

import com.ipm.web.dto.Project;
import com.ipm.web.interfaces.ProjectManager;

public class ProjectManagerImpl implements ProjectManager{

	@Override
	public List<Project> getProjects() {
		List<Project> list = new ArrayList<Project>();
		Project a1 = new Project();
		a1.setId(1);
		a1.setName("P1");
		
		Project a2 = new Project();
		a2.setId(2);
		a2.setName("P2");
		list.add(a1);
		list.add(a2);
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public void createProject(Project project) {
		// TODO Auto-generated method stub
		
	}

}
