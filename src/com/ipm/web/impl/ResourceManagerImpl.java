package com.ipm.web.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipm.web.dto.Resource;
import com.ipm.web.dto.Task;
import com.ipm.web.interfaces.ResourceDao;
import com.ipm.web.interfaces.ResourceManager;

public class ResourceManagerImpl implements ResourceManager {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private ResourceDao resourceDao;

	@Override
	public List<Resource> getResources(String username, int projectId) {
		return resourceDao.getResources(username,projectId);
	}

	@Override
	public void createResource(Resource resource) {
		resourceDao.createResource(resource);

	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public void removeResource(Resource resource) {
		this.resourceDao.removeResource(resource);
		
	}

	@Override
	public Resource getResource(String username, int projectId, int resourceId) {
		return this.resourceDao.getResource( username,  projectId,  resourceId);
	}
}
