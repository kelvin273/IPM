package com.ipm.web.interfaces;

import com.ipm.web.dto.Plan;
import com.ipm.web.dto.PlanRQRS;

public interface WSManager {

    public PlanRQRS getPlan(Plan plan);
    
}
