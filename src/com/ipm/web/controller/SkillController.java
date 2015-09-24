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

import com.ipm.web.dto.Skill;
import com.ipm.web.form.SkillForm;
import com.ipm.web.interfaces.SkillManager;

@Controller
public class SkillController extends WebMvcConfigurerAdapter {

	private SkillManager skillManager;

	@RequestMapping(value = "/skills/skills", method = RequestMethod.POST)
	public ModelAndView skillsPage(HttpServletRequest request, @RequestParam("projectId") String projectId) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("skills/skills");
			model.addObject("skills", skillManager.getSkills(username,Integer.valueOf(projectId)));
		}
		request.getSession().setAttribute("projectId", projectId);
		return model;

	}

	@RequestMapping(value = "/skills/newSkill", method = RequestMethod.POST)
	public ModelAndView createSkill(HttpServletRequest request, @Valid @ModelAttribute("skill") SkillForm skill, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("skills/newSkill");
			return model;
        }
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Skill s = new Skill();
			s.setName(skill.getName());
			s.setUsername(username);
			s.setProjectId(Integer.valueOf((String) request.getSession().getAttribute("projectId")));
			skillManager.createSkill(s);
			model.setViewName("skills/skills");
			model.addObject("skills", skillManager.getSkills(username,Integer.valueOf((String) request.getSession().getAttribute("projectId"))));
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
