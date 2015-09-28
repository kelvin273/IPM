package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Resource;

public interface ResourceManager {

    public List<Resource> getResources(String username, int projectId);
    
    public void createResource(Resource skill);
}