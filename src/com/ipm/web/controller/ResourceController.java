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

import com.ipm.web.dto.Resource;
import com.ipm.web.dto.Skill;
import com.ipm.web.form.ResourceForm;
import com.ipm.web.interfaces.ResourceManager;
import com.ipm.web.interfaces.SkillManager;

@Controller
public class ResourceController extends WebMvcConfigurerAdapter {

	private ResourceManager resourceManager;

	private SkillManager skillManager;

	@RequestMapping(value = "/resources/resources", method = RequestMethod.POST)
	public ModelAndView resourcesPagePOST(HttpServletRequest request, @RequestParam("projectId") String projectId, @RequestParam("projectName") String projectName) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("resources/resources");
			model.addObject("resources", resourceManager.getResources(username, Integer.valueOf(projectId)));
		}
		request.getSession().setAttribute("projectId", projectId);
		request.getSession().setAttribute("projectName", projectName);
		return model;

	}

	@RequestMapping(value = "/resources/resources", method = RequestMethod.GET)
	public ModelAndView resourcesPageGET(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("resources/resources");
			String finalProjectId = (String) request.getSession().getAttribute("projectId");
			model.addObject("resources", resourceManager.getResources(username, Integer.valueOf(finalProjectId)));	}
		return model;

	}

	@RequestMapping(value = "/resources/newResource", method = RequestMethod.POST)
	public ModelAndView createResource(HttpServletRequest request,
			@Valid @ModelAttribute("resource") ResourceForm resource, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("resources/newResource");
			return model;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Resource s = new Resource();
			s.setName(resource.getName());
			s.setUsername(username);
			s.setProjectId(Integer.valueOf((String) request.getSession().getAttribute("projectId")));

			List<Skill> skillList = new ArrayList<Skill>();
			for (String id : resource.getSkills()) {
				Skill skill = new Skill();
				skill.setId(Long.valueOf(id));
				skillList.add(skill);
			}
			s.setSkills(skillList);
			resourceManager.createResource(s);
			model.setViewName("resources/resources");
			int projectId = Integer.valueOf((String) request.getSession().getAttribute("projectId"));
			model.addObject("resources", resourceManager.getResources(username, projectId));
		}
		return model;
	}

	@RequestMapping(value = "/resources/newResource", method = RequestMethod.GET)
	public ModelAndView newResource(HttpServletRequest request, ModelMap model) {
		ModelAndView modelAux = new ModelAndView();
		ResourceForm resource = new ResourceForm();
		model.addAttribute("resource", resource);
		modelAux.setViewName("resources/newResource");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		int projectId = Integer.valueOf((String) request.getSession().getAttribute("projectId"));
		model.addAttribute("skills", skillManager.getSkills(username, projectId));
		return modelAux;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void setSkillManager(SkillManager skillManager) {
		this.skillManager = skillManager;
	}

}
