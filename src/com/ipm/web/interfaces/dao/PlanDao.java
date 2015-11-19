package com.ipm.web.interfaces.dao;

import java.util.List;

import com.ipm.web.dto.Plan;

public interface PlanDao {

	 public List<Plan> getPlans(String username, int projectId);
	    
	    public void createPlan(Plan plan);

		public void removePlan(Plan plan);
}
