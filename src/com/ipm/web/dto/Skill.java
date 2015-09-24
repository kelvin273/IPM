package com.ipm.web.dto;

import java.io.Serializable;

public class Skill implements Serializable {

	private long id;
	private String name;
	private String username;
	private long projectId;
	
	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectID) {
		this.projectId = projectID;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
