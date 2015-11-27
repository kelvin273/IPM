package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Plan;
import com.ipm.web.dto.PlanRQRS;

public interface PlanManager {

    public List<Plan> getPlans(String username, int projectId);
    
    public PlanRQRS createPlan(Plan plan);

	public void removePlan(Plan plan);
}
