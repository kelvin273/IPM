package com.ipm.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("projects");
			model.addObject("projects", projectManager.getProjects(username));
		}

		return model;

	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

}
