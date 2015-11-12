package com.ipm.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ipm.web.form.ContactForm;

@Controller
public class ContactController extends WebMvcConfigurerAdapter {

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact(ModelMap model) {
		ModelAndView modelAux = new ModelAndView();
		ContactForm contact = new ContactForm();
		model.addAttribute("contact", contact);
		modelAux.setViewName("/contact");
		return modelAux;
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public ModelAndView createProject(
			@Valid @ModelAttribute("contact") ContactForm contact,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("/contact");
			return model;
		}
		model.setViewName("/contact");
		return model;
	}

}
