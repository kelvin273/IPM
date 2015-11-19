package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Resource;

public interface ResourceManager {

    public List<Resource> getResources(String username, int projectId);
    
    public Resource getResource(String username, int projectId, int resourceId);
    
    public void createResource(Resource resource);

	public void removeResource(Resource resource);
	
	public void updateResource(Resource resource);
}
