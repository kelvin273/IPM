package com.ipm.web.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipm.web.dto.Project;
import com.ipm.web.interfaces.ProjectDao;
import com.ipm.web.interfaces.ProjectManager;

public class ProjectManagerImpl implements ProjectManager {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private ProjectDao projectDao;

	@Override
	public List<Project> getProjects(String username) {
		return projectDao.getProjects(username);
	}

	@Override
	public void createProject(Project project) {
		// TODO Auto-generated method stub

	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

}
