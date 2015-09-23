package com.ipm.web.dto;

import javax.xml.bind.annotation.XmlAttribute;


public class SkillWS {
	
	
	private String id;
	
	/**
	 * Getter.
	 * 
	 * @return the id
	 */
	@XmlAttribute
	public String getId() {
		return id;
	}

	/**
	 * Setter.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}