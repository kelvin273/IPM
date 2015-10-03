package com.ipm.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ipm.web.dto.Plan;
import com.ipm.web.dto.Resource;
import com.ipm.web.dto.Skill;
import com.ipm.web.dto.Task;
import com.ipm.web.form.PlanForm;
import com.ipm.web.interfaces.PlanManager;
import com.ipm.web.interfaces.ResourceManager;
import com.ipm.web.interfaces.TaskManager;

@Controller
public class PlanController extends WebMvcConfigurerAdapter {

	private PlanManager planManager;

	private ResourceManager resourceManager;

	private TaskManager taskManager;

	@RequestMapping(value = "/plans/plans", method = RequestMethod.POST)
	public ModelAndView plansPagePOST(HttpServletRequest request, @RequestParam("projectId") String projectId,
			@RequestParam("projectName") String projectName) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("plans/plans");
			model.addObject("plans", planManager.getPlans(username, Integer.valueOf(projectId)));
		}
		request.getSession().setAttribute("projectId", projectId);
		request.getSession().setAttribute("projectName", projectName);
		return model;

	}

	@RequestMapping(value = "/plans/plans", method = RequestMethod.GET)
	public ModelAndView plansPageGET(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("plans/plans");
			String finalProjectId = (String) request.getSession().getAttribute("projectId");
			model.addObject("plans", planManager.getPlans(username, Integer.valueOf(finalProjectId)));
		}
		return model;

	}

	@RequestMapping(value = "/plans/newPlan", method = RequestMethod.POST)
	public ModelAndView createPlan(HttpServletRequest request, @Valid @ModelAttribute("plan") PlanForm plan,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		int projectId = Integer.valueOf((String) request.getSession().getAttribute("projectId"));
		model.addObject("resources", resourceManager.getResources(username, projectId));
		model.addObject("tasks", taskManager.getTasks(username, projectId));
		if (result.hasErrors()) {
			model.setViewName("plans/newPlan");
			return model;
		}
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Plan p = new Plan();
			p.setName(plan.getName());
			p.setUsername(username);
			p.setProjectId(Integer.valueOf((String) request.getSession().getAttribute("projectId")));

			List<Task> taskList = new ArrayList<Task>();
			for (String id : plan.getTasks()) {
				Task task = new Task();
				task.setId(Long.valueOf(id));
				taskList.add(task);
			}
			p.setTasks(taskList);
			
			List<Resource> resourceList = new ArrayList<Resource>();
			for (String id : plan.getResources()) {
				Resource resource = new Resource();
				resource.setId(Long.valueOf(id));
				resourceList.add(resource);
			}
			p.setResources(resourceList);
			
			model.setViewName("plans/result");
			model.addObject("plan", planManager.createPlan(p));
			 
		}
		return model;
	}

	@RequestMapping(value = "/plans/removePlan", method = RequestMethod.POST)
	public ModelAndView removeSkill(HttpServletRequest request, @RequestParam("planId") String planId) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Plan r = new Plan();
			r.setId(Long.valueOf(planId));
			r.setUsername(username);
			r.setProjectId(Integer.valueOf((String) request.getSession().getAttribute("projectId")));
			planManager.removePlan(r);
			model.setViewName("plans/plans");
			model.addObject("plans", planManager.getPlans(username,
					Integer.valueOf((String) request.getSession().getAttribute("projectId"))));
		}
		return model;
	}

	@RequestMapping(value = "/plans/newPlan", method = RequestMethod.GET)
	public ModelAndView newPlan(HttpServletRequest request, ModelMap model) {
		ModelAndView modelAux = new ModelAndView();
		PlanForm plan = new PlanForm();
		model.addAttribute("plan", plan);
		modelAux.setViewName("plans/newPlan");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		int projectId = Integer.valueOf((String) request.getSession().getAttribute("projectId"));
		model.addAttribute("resources", resourceManager.getResources(username, projectId));
		model.addAttribute("tasks", taskManager.getTasks(username, projectId));
		return modelAux;
	}

	public void setPlanManager(PlanManager planManager) {
		this.planManager = planManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

}
