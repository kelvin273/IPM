package com.ipm.web.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ipm.web.dto.Skill;

public class ResourceForm implements Serializable {

	@NotEmpty
	@NotNull @Min(1)
	private double salary;
	@NotEmpty
	@NotNull @Min(1)
	private float maxDedication;
	@Size(min = 5, max = 30)
	@NotEmpty
	private String name;
	
	private List<Skill> skills;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
