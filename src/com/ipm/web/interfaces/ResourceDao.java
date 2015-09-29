package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Resource;

public interface ResourceDao {

	public List<Resource> getResources(String username, int projectId);

	public void createResource(Resource resource);

	public void removeResource(Resource resource);
}
