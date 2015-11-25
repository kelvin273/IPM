package com.ipm.web.impl.manager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipm.web.dto.Resource;
import com.ipm.web.interfaces.ResourceManager;
import com.ipm.web.interfaces.dao.ResourceDao;

public class ResourceManagerImpl implements ResourceManager {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private ResourceDao resourceDao;

	@Override
	public List<Resource> getResources(String username, long projectId) {
		return resourceDao.getResources(username,projectId);
	}

	@Override
	public void createResource(Resource resource) {
		resourceDao.createResource(resource);

	}
	
	@Override
	public void updateResource(Resource resource) {
		resourceDao.updateResource(resource);

	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public void removeResource(Resource resource) {
		this.resourceDao.removeResource(resource);
		
	}

	@Override
	public void getResource(Resource r) {
		this.resourceDao.getResource(r);
	}

}
