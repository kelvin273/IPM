package com.adsf.ipm.ws.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ResourcesWS {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResourcesWS [resource=");
		builder.append(resource);
		builder.append("]");
		return builder.toString();
	}

	private List<ResourceWS> resource;

	@XmlElement(required=true)
	public List<ResourceWS> getResource() {
		return resource;
	}

	public void setResource(List<ResourceWS> resource) {
		this.resource = resource;
	}

}