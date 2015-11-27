package com.ipm.web.impl.manager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipm.web.dto.Plan;
import com.ipm.web.dto.PlanRQRS;
import com.ipm.web.interfaces.PlanManager;
import com.ipm.web.interfaces.WSManager;
import com.ipm.web.interfaces.dao.PlanDao;

public class PlanManagerImpl implements PlanManager {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private PlanDao resourceDao;

	private WSManager ws;

	@Override
	public List<Plan> getPlans(String username, int projectId) {
		return resourceDao.getPlans(username, projectId);
	}

	@Override
	public PlanRQRS createPlan(Plan plan) {
		// Calls the WS
		return ws.getPlan(plan);

	}

	public void setPlanDao(PlanDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public void removePlan(Plan resource) {
		this.resourceDao.removePlan(resource);

	}

	public void setWs(WSManager ws) {
		this.ws = ws;
	}
	

}
