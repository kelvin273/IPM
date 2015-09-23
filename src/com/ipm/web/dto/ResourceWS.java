package com.adsf.ipm.ws.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ResourceWS {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResourceWS [id=");
		builder.append(id);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", maxDedication=");
		builder.append(maxDedication);
		builder.append(", skills=");
		builder.append(skills);
		builder.append("]");
		return builder.toString();
	}

	private String id;
	private double salary;
	private float maxDedication;
	private SkillsWS skills;

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

	/**
	 * Getter.
	 * 
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Setter.
	 * 
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * Getter.
	 * 
	 * @return the maxDedication
	 */
	public float getMaxDedication() {
		return maxDedication;
	}

	/**
	 * Setter.
	 * 
	 * @param maxDedication
	 *            the maxDedication to set
	 */
	public void setMaxDedication(float maxDedication) {
		this.maxDedication = maxDedication;
	}

	/**
	 * Getter.
	 * 
	 * @return the skills
	 */
	@XmlElement(name="skills")
	public SkillsWS getSkillsWS() {
		return skills;
	}

	/**
	 * Setter.
	 * 
	 * @param skills
	 *            the skills to set
	 */
	public void setSkillsWS(SkillsWS skills) {
		this.skills = skills;
	}

}
