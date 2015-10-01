package com.ipm.web.form;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TaskForm implements Serializable {

	@Size(min = 5, max = 30)
	@NotEmpty
	private String name;
	@Min(value = 1)
	private float effort;
	private boolean exclusive;
	private String[] requiredSkills;
	private String[] precedentTasks;
	private String[] resources;

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

	public String[] getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String[] requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public String[] getPrecedentTasks() {
		return precedentTasks;
	}

	public void setPrecedentTasks(String[] precedentTasks) {
		this.precedentTasks = precedentTasks;
	}

	public String[] getResources() {
		return resources;
	}

	public void setResources(String[] resources) {
		this.resources = resources;
	}

}
