package com.ipm.web.impl;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.adsf.ipm.ws.PlanService;
import com.adsf.ipm.ws.dto.PlanWS;
import com.adsf.ipm.ws.dto.PrecedentTaskWS;
import com.adsf.ipm.ws.dto.PrecedentTasksWS;
import com.adsf.ipm.ws.dto.ResourceWS;
import com.adsf.ipm.ws.dto.ResourcesWS;
import com.adsf.ipm.ws.dto.TaskWS;
import com.adsf.ipm.ws.dto.TasksWS;
import com.ipm.web.dto.Plan;
import com.ipm.web.dto.Resource;
import com.ipm.web.dto.Task;
import com.ipm.web.interfaces.WSManager;

public class WSManagerImpl implements WSManager {

	@Override
	public PlanWS getPlan(Plan plan) {
		PlanWS planWS = new PlanWS();
		try {
			URL url = new URL("http://ipm-gc.rhcloud.com/ws?wsdl");
			// URL url = new URL("http://127.0.0.1:8888/ts?wsdl");
			QName qname = new QName("http://impl.ws.ipm.adsf.com/",
					"PlanServiceImplService");
			Service planService = Service.create(url, qname);
			PlanService service = planService.getPort(PlanService.class);
			ResourcesWS resources = mapResources(plan);
			TasksWS tasks = mapTasks(plan);
			planWS = service.getPlan(resources, tasks);

		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}
		return planWS;
	}

	private TasksWS mapTasks(Plan plan) {
		TasksWS tasks = new TasksWS();
		tasks.setTasks(new ArrayList<TaskWS>());
		for (Task task : plan.getTasks()) {
			addTask(tasks, task);
		}
		return tasks;
	}

	private ResourcesWS mapResources(Plan plan) {
		ResourcesWS resources = new ResourcesWS();
		resources.setResource(new ArrayList<ResourceWS>());
		for (Resource resource : plan.getResources()) {
			addResource(resources, resource.getName(),
					resource.getMaxDedication(), resource.getCost());
		}
		return resources;
	}

	protected void addResource(ResourcesWS resources, String id,
			float maxDedication, float salary) {
		ResourceWS r1 = new ResourceWS();
		r1.setId(id);
		r1.setMaxDedication(maxDedication);
		r1.setSalary(salary);
		resources.getResource().add(r1);
	}

	protected void addTask(TasksWS tasks, Task task) {
		TaskWS t1 = new TaskWS();
		t1.setEffort(task.getEffort());
		t1.setId(task.getName());
		t1.setExclusive(task.isExclusive());
		t1.setPrecedentTasks(new PrecedentTasksWS());
		t1.getPrecedentTasksWS().setPrecedentTasksWS(
				new ArrayList<PrecedentTaskWS>());
		if (null != task.getPrecedentTasks()) {
			for (Task t : task.getPrecedentTasks()) {
				PrecedentTaskWS pt = new PrecedentTaskWS();
				pt.setId(t.getName());
				t1.getPrecedentTasksWS().getPrecedentTasksWS().add(pt);
			}
		}
		tasks.getTasks().add(t1);
	}

}
