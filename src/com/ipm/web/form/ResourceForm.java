package com.ipm.web.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ipm.web.dto.Skill;

public class ResourceForm implements Serializable {

	private long id;
	@NotNull
	@Min(value = 1)
	private float salary;
	@NotNull
	@Min(value = 1)
	private float maxDedication = 1.0f;
	@Size(min = 5, max = 30)
	@NotEmpty
	private String name;

	private String[] skills;

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getMaxDedication() {
		return maxDedication;
	}

	public void setMaxDedication(float maxDedication) {
		this.maxDedication = maxDedication;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
