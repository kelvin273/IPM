/**
 * 
 */
package com.ipm.web.dto;

import java.util.List;

public class Task {

	private long id;
	private long projectId;
	private String name;
	private String username;
	private float effort;
	private boolean exclusive;
	private List<Skill> requiredSkills;
	private List<Task> precedentTasks;
	private List<Resource> resources;

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

	public float getEffort() {
		return effort;
	}

	public void setEffort(float effort) {
		this.effort = effort;
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

	public List<Skill> getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(List<Skill> requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public List<Task> getPrecedentTasks() {
		return precedentTasks;
	}

	public void setPrecedentTasks(List<Task> precedentTasks) {
		this.precedentTasks = precedentTasks;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
