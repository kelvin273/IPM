package com.ipm.web.interfaces;

import java.util.List;

import com.adsf.ipm.ws.dto.PlanWS;
import com.ipm.web.dto.Plan;

public interface PlanManager {

    public List<Plan> getPlans(String username, int projectId);
    
    public PlanWS createPlan(Plan plan);

	public void removePlan(Plan plan);
}
