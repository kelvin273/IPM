package com.ipm.web.dto;

import com.adsf.ipm.ws.dto.PlanWS;
import com.adsf.ipm.ws.dto.ResourcesWS;
import com.adsf.ipm.ws.dto.TasksWS;

public class PlanRQRS {

	private ResourcesWS resourcesRQ;
	private TasksWS tasksRQ;
	private PlanWS planRS;

	public ResourcesWS getResourcesRQ() {
		return resourcesRQ;
	}

	public void setResourcesRQ(ResourcesWS resourcesRQ) {
		this.resourcesRQ = resourcesRQ;
	}

	public TasksWS getTasksRQ() {
		return tasksRQ;
	}

	public void setTasksRQ(TasksWS tasksRQ) {
		this.tasksRQ = tasksRQ;
	}

	public PlanWS getPlanRS() {
		return planRS;
	}

	public void setPlanRS(PlanWS planRS) {
		this.planRS = planRS;
	}

}
