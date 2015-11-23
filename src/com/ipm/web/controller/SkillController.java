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
import com.ipm.web.dto.Skill;
import com.ipm.web.form.ProjectForm;
import com.ipm.web.form.SkillForm;
import com.ipm.web.interfaces.SkillManager;

@Controller
public class SkillController extends WebMvcConfigurerAdapter {

	private SkillManager skillManager;

	@RequestMapping(value = "/skills/skills", method = RequestMethod.POST)
	public ModelAndView skillsPagePOST(HttpServletRequest request,
			@RequestParam("projectId") String projectId,
			@RequestParam("projectName") String projectName) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("skills/skills");
			model.addObject("skills", skillManager.getSkills(username,
					Integer.valueOf(projectId)));
		}
		request.getSession().setAttribute("projectId", projectId);
		request.getSession().setAttribute("projectName", projectName);
		return model;

	}

	@RequestMapping(value = "/skills/skills", method = RequestMethod.GET)
	public ModelAndView skillsPageGET(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("skills/skills");
			String finalProjectId = (String) request.getSession().getAttribute(
					"projectId");
			model.addObject(
					"skills",
					skillManager.getSkills(username,
							Integer.valueOf(finalProjectId)));
		}
		return model;

	}

	@RequestMapping(value = "/skills/newSkill", method = RequestMethod.POST)
	public ModelAndView createSkill(HttpServletRequest request,
			@Valid @ModelAttribute("skill") SkillForm skill,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("skills/newSkill");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Skill s = new Skill();
			s.setName(skill.getName());
			s.setUsername(username);
			s.setProjectId(Integer.valueOf((String) request.getSession()
					.getAttribute("projectId")));
			s.setId(skill.getId());
			if (0 == s.getId()) {
				skillManager.createSkill(s);
			} else {
				skillManager.updateSkill(s);
			}
			skill.setId(s.getId());
			model.setViewName("skills/newSkill");
			if (!result.hasErrors()) {
				model.addObject("success", true);
			}
		}
		return model;
	}

	@RequestMapping(value = "/skills/updateSkill", method = RequestMethod.POST)
	public ModelAndView updateProject(HttpServletRequest request,
			@RequestParam("skillId") String skillId) {
		ModelAndView modelAux = new ModelAndView();
		modelAux.setViewName("skills/newSkill");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Skill s = new Skill();
			s.setId(Long.valueOf(skillId));
			s.setProjectId(Integer.valueOf((String) request.getSession()
					.getAttribute("projectId")));
			s.setUsername(username);
			skillManager.getSkill(s);
			SkillForm skill = new SkillForm();
			skill.setId(s.getId());
			skill.setName(s.getName());
			modelAux.addObject("skill", skill);
		}
		return modelAux;

	}

	@RequestMapping(value = "/skills/removeSkill", method = RequestMethod.POST)
	public ModelAndView removeSkill(HttpServletRequest request,
			@RequestParam("skillId") String skillId) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Skill s = new Skill();
			s.setId(Long.valueOf(skillId));
			s.setUsername(username);
			s.setProjectId(Integer.valueOf((String) request.getSession()
					.getAttribute("projectId")));
			skillManager.removeSkill(s);
			model.setViewName("skills/skills");
			model.addObject("skills", skillManager.getSkills(
					username,
					Integer.valueOf((String) request.getSession().getAttribute(
							"projectId"))));

		}
		return model;
	}

	@RequestMapping(value = "/skills/newSkill", method = RequestMethod.GET)
	public ModelAndView newSkill(ModelMap model) {
		ModelAndView modelAux = new ModelAndView();
		SkillForm skill = new SkillForm();
		model.addAttribute("skill", skill);
		modelAux.setViewName("skills/newSkill");
		return modelAux;
	}

	public void setSkillManager(SkillManager skillManager) {
		this.skillManager = skillManager;
	}

}
