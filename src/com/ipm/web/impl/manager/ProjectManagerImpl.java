package com.ipm.web.impl.manager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipm.web.dto.Project;
import com.ipm.web.interfaces.ProjectManager;
import com.ipm.web.interfaces.dao.ProjectDao;

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
		projectDao.createProject(project);

	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public void updateProject(Project project) {
		projectDao.updateProject(project);
	}
	
	@Override
	public void removeProject(Project project) {
		projectDao.removeProject(project);
	}

}
