package com.ipm.web.impl.manager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.adsf.ipm.ws.dto.PlanWS;
import com.ipm.web.dto.Plan;
import com.ipm.web.interfaces.PlanManager;
import com.ipm.web.interfaces.WSManager;
import com.ipm.web.interfaces.dao.PlanDao;

public class PlanManagerImpl implements PlanManager {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private PlanDao resourceDao;
	
	private WSManager ws = new WSManagerImpl();

	@Override
	public List<Plan> getPlans(String username, int projectId) {
		return resourceDao.getPlans(username,projectId);
	}

	@Override
	public PlanWS createPlan(Plan plan) {
//		resourceDao.createPlan(plan);
		// Call the WS
		return ws.getPlan(plan);

	}

	public void setPlanDao(PlanDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public void removePlan(Plan resource) {
		this.resourceDao.removePlan(resource);
		
	}
}
