package com.ipm.web.interfaces.dao;

import java.util.List;

import com.ipm.web.dto.Resource;

public interface ResourceDao {

	public List<Resource> getResources(String username, int projectId);

	public void createResource(Resource resource);

	public void removeResource(Resource resource);
	
	public void updateResource(Resource resource);

	public Resource getResource(String username, int projectId, int resourceId);
}
