package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Resource;

public interface ResourceManager {

    public List<Resource> getResources(String username, long projectId);
    
    public void createResource(Resource resource);

	public void removeResource(Resource resource);
	
	public void updateResource(Resource resource);

	public void getResource(Resource r);
}
