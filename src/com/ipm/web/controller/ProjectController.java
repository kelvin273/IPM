package com.ipm.web.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ipm.web.dto.Project;
import com.ipm.web.form.ProjectForm;
import com.ipm.web.interfaces.ProjectManager;

@Controller
public class ProjectController extends WebMvcConfigurerAdapter {

	private ProjectManager projectManager;

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public ModelAndView projectsPage() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("projects");
			model.addObject("projects", projectManager.getProjects(username));
		}

		return model;

	}

	@RequestMapping(value = "/newProject", method = RequestMethod.POST)
	public ModelAndView createProject(@Valid @ModelAttribute("project") ProjectForm project, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("newProject");
			return model;
        }
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Project p = new Project();
			p.setName(project.getName());
			projectManager.createProject(username, p);
			model.setViewName("projects");
			model.addObject("projects", projectManager.getProjects(username));
		}
		return model;
	}

	@RequestMapping(value = "/newProject", method = RequestMethod.GET)
    public ModelAndView newProject(ModelMap model) {
		ModelAndView modelAux = new ModelAndView();
        ProjectForm project = new ProjectForm();
        model.addAttribute("project", project);
        modelAux.setViewName("newProject");
        return modelAux;
    }
 

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

}
