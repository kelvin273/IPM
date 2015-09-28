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

import com.ipm.web.dto.Task;
import com.ipm.web.form.TaskForm;
import com.ipm.web.interfaces.TaskManager;

@Controller
public class TaskController extends WebMvcConfigurerAdapter {

	private TaskManager taskManager;

	@RequestMapping(value = "/tasks/tasks", method = RequestMethod.POST)
	public ModelAndView tasksPagePOST(HttpServletRequest request, @RequestParam("projectId") String projectId, @RequestParam("projectName") String projectName) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("tasks/tasks");
			model.addObject("tasks", taskManager.getTasks(username, Integer.valueOf(projectId)));
		}
		request.getSession().setAttribute("projectId", projectId);
		request.getSession().setAttribute("projectName", projectName);
		return model;

	}

	@RequestMapping(value = "/tasks/tasks", method = RequestMethod.GET)
	public ModelAndView tasksPageGET(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("tasks/tasks");
			String finalProjectId = (String) request.getSession().getAttribute("projectId");
			model.addObject("tasks", taskManager.getTasks(username, Integer.valueOf(finalProjectId)));
		}
		return model;

	}

	@RequestMapping(value = "/tasks/newTask", method = RequestMethod.POST)
	public ModelAndView createTask(HttpServletRequest request, @Valid @ModelAttribute("task") TaskForm task,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("tasks/newTask");
			return model;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Task s = new Task();
			s.setName(task.getName());
			s.setUsername(username);
			s.setProjectId(Integer.valueOf((String) request.getSession().getAttribute("projectId")));
			taskManager.createTask(s);
			model.setViewName("tasks/tasks");
			model.addObject("tasks", taskManager.getTasks(username,
					Integer.valueOf((String) request.getSession().getAttribute("projectId"))));
		}
		return model;
	}

	@RequestMapping(value = "/tasks/newTask", method = RequestMethod.GET)
	public ModelAndView newTask(ModelMap model) {
		ModelAndView modelAux = new ModelAndView();
		TaskForm task = new TaskForm();
		model.addAttribute("task", task);
		modelAux.setViewName("tasks/newTask");
		return modelAux;
	}

	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

}