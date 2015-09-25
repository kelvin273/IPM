package com.ipm.web.dto;

import java.util.List;

public class Resource {

	private long id;
	private String name;
	private double salary;
	private float maxDedication;
	private List<Skill> skills;
	private String username;
	private long projectId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public float getMaxDedication() {
		return maxDedication;
	}

	public void setMaxDedication(float maxDedication) {
		this.maxDedication = maxDedication;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

}
