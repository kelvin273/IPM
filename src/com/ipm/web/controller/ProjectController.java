package com.ipm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipm.web.interfaces.ProjectManager;

@Controller
public class ProjectController {

	private ProjectManager projectManager;
	
	@RequestMapping(value = "/project**", method = RequestMethod.GET)
	public ModelAndView projectsPage() {

	  ModelAndView model = new ModelAndView();
	  model.setViewName("projects");
	  model.addObject("projects", projectManager.getProjects());
	  return model;

	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	
	
}
