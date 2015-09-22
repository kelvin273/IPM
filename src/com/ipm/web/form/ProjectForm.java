package com.ipm.web.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class ProjectForm implements Serializable{
	
	@Size(min=5, max=30)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
