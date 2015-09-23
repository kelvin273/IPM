package com.adsf.ipm.ws.dto;

import javax.xml.bind.annotation.XmlAttribute;


public class PrecedentTaskWS {

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrecedentTaskWS [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
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