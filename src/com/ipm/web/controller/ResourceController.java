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
	public ModelAndView resourcesPagePOST(HttpServletRequest request,
			@RequestParam("projectId") String projectId,
			@RequestParam("projectName") String projectName) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("resources/resources");
			model.addObject(
					"resources",
					resourceManager.getResources(username,
							Integer.valueOf(projectId)));
		}
		request.getSession().setAttribute("projectId", projectId);
		request.getSession().setAttribute("projectName", projectName);
		return model;

	}

	@RequestMapping(value = "/resources/resources", method = RequestMethod.GET)
	public ModelAndView resourcesPageGET(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("resources/resources");
			String finalProjectId = (String) request.getSession().getAttribute(
					"projectId");
			model.addObject(
					"resources",
					resourceManager.getResources(username,
							Integer.valueOf(finalProjectId)));
		}
		return model;

	}

	@RequestMapping(value = "/resources/newResource", method = RequestMethod.POST)
	public ModelAndView createResource(HttpServletRequest request,
			@Valid @ModelAttribute("resource") ResourceForm resource,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		int projectId = Integer.valueOf((String) request.getSession()
				.getAttribute("projectId"));
		model.addObject("skills", skillManager.getSkills(username, projectId));
		model.setViewName("resources/newResource");
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Resource resourceToBeCreated = new Resource();
			resourceToBeCreated.setName(resource.getName());
			resourceToBeCreated.setUsername(username);
			resourceToBeCreated.setProjectId(Integer.valueOf((String) request.getSession()
					.getAttribute("projectId")));
			resourceToBeCreated.setMaxDedication(resource.getMaxDedication());
			resourceToBeCreated.setCost(resource.getSalary());
			List<Skill> skillList = new ArrayList<Skill>();
			for (String id : resource.getSkills()) {
				Skill skill = new Skill();
				skill.setId(Long.valueOf(id));
				skillList.add(skill);
			}
			resourceToBeCreated.setSkills(skillList);
			resourceToBeCreated.setId(resource.getId());
			if(0==resourceToBeCreated.getId()){
			resourceManager.createResource(resourceToBeCreated);
			}else{
				resourceManager.updateResource(resourceToBeCreated);
			}
			resource.setId(resourceToBeCreated.getId());
			if (!result.hasErrors()) {
				model.addObject("success", true);
			}
		}
		return model;
	}

	@RequestMapping(value = "/resources/removeResource", method = RequestMethod.POST)
	public ModelAndView removeSkill(HttpServletRequest request,
			@RequestParam("resourceId") String resourceId) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Resource r = new Resource();
			r.setId(Long.valueOf(resourceId));
			r.setUsername(username);
			r.setProjectId(Integer.valueOf((String) request.getSession()
					.getAttribute("projectId")));
			resourceManager.removeResource(r);
			model.setViewName("resources/resources");
			model.addObject("resources", resourceManager.getResources(
					username,
					Integer.valueOf((String) request.getSession().getAttribute(
							"projectId"))));
		}
		return model;
	}

	@RequestMapping(value = "/resources/newResource", method = RequestMethod.GET)
	public ModelAndView newResource(HttpServletRequest request, ModelMap model) {
		ModelAndView modelAux = new ModelAndView();
		ResourceForm resource = new ResourceForm();
		model.addAttribute("resource", resource);
		modelAux.setViewName("resources/newResource");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		int projectId = Integer.valueOf((String) request.getSession()
				.getAttribute("projectId"));
		model.addAttribute("skills",
				skillManager.getSkills(username, projectId));
		return modelAux;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void setSkillManager(SkillManager skillManager) {
		this.skillManager = skillManager;
	}

}
