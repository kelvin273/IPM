package com.ipm.web.controller;

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

import com.ipm.web.dto.Project;
import com.ipm.web.form.ProjectForm;
import com.ipm.web.interfaces.ProjectManager;

@Controller
public class ProjectController extends WebMvcConfigurerAdapter {

	private ProjectManager projectManager;

	@RequestMapping(value = "/projects/projects", method = RequestMethod.GET)
	public ModelAndView projectsPage() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("projects/projects");
			model.addObject("projects", projectManager.getProjects(username));
		}

		return model;

	}

	@RequestMapping(value = "/projects/newProject", method = RequestMethod.POST)
	public ModelAndView createProject(
			@Valid @ModelAttribute("project") ProjectForm project,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
			model.setViewName("projects/newProject");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Project p = new Project();
			p.setName(project.getName());
			p.setUsername(username);
			p.setId(project.getId());
			if (0 == p.getId()) {
				projectManager.createProject(p);
			} else {
				projectManager.updateProject(p);
			}
			project.setId(p.getId());
			if (!result.hasErrors()) {
				model.addObject("success", true);
			}
		}
		return model;
	}

	@RequestMapping(value = "/projects/newProject", method = RequestMethod.GET)
	public ModelAndView newProject(ModelMap model) {
		ModelAndView modelAux = new ModelAndView();
		ProjectForm project = new ProjectForm();
		model.addAttribute("project", project);
		modelAux.setViewName("projects/newProject");
		return modelAux;
	}
	
	@RequestMapping(value = "/projects/updateProject", method = RequestMethod.POST)
	public ModelAndView updateProject(HttpServletRequest request,
			@RequestParam("projectId") String projectId) {
		ModelAndView modelAux = new ModelAndView();
		Project p = projectManager.getProject(projectId);
		ProjectForm project = new ProjectForm();
		project.setId(p.getId());
		project.setName(p.getName());
		modelAux.addObject("project", project);
		modelAux.setViewName("projects/newProject");
		return modelAux;
	}

	@RequestMapping(value = "/projects/removeProject", method = RequestMethod.POST)
	public ModelAndView removeProject(HttpServletRequest request,
			@RequestParam("projectId") String projectId) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Project p = new Project();
			p.setId(Long.valueOf(projectId));
			p.setUsername(username);
			projectManager.removeProject(p);
			model.setViewName("projects/projects");
			model.addObject("projects", projectManager.getProjects(username));
			model.addObject("success", true);
		}
		return model;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

}
