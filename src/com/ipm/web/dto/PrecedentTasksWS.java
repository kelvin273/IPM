package com.adsf.ipm.ws.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class PrecedentTasksWS {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrecedentTasksWS [precedentTask=");
		builder.append(precedentTask);
		builder.append("]");
		return builder.toString();
	}

	private List<PrecedentTaskWS> precedentTask;

	@XmlElement(name="precedentTask")
	public List<PrecedentTaskWS> getPrecedentTasksWS() {
		return precedentTask;
	}

	public void setPrecedentTasksWS(List<PrecedentTaskWS> precedentTasks) {
		this.precedentTask = precedentTasks;
	}

}