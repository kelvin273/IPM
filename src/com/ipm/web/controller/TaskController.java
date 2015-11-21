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
import com.ipm.web.dto.Task;
import com.ipm.web.form.TaskForm;
import com.ipm.web.interfaces.ResourceManager;
import com.ipm.web.interfaces.SkillManager;
import com.ipm.web.interfaces.TaskManager;

@Controller
public class TaskController extends WebMvcConfigurerAdapter {

	private TaskManager taskManager;
	private SkillManager skillManager;
	private ResourceManager resourceManager;

	@RequestMapping(value = "/tasks/tasks", method = RequestMethod.POST)
	public ModelAndView tasksPagePOST(HttpServletRequest request,
			@RequestParam("projectId") String projectId,
			@RequestParam("projectName") String projectName) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("tasks/tasks");
			model.addObject("tasks",
					taskManager.getTasks(username, Integer.valueOf(projectId)));
		}
		request.getSession().setAttribute("projectId", projectId);
		request.getSession().setAttribute("projectName", projectName);
		return model;

	}

	@RequestMapping(value = "/tasks/tasks", method = RequestMethod.GET)
	public ModelAndView tasksPageGET(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			model.setViewName("tasks/tasks");
			String finalProjectId = (String) request.getSession().getAttribute(
					"projectId");
			model.addObject(
					"tasks",
					taskManager.getTasks(username,
							Integer.valueOf(finalProjectId)));
		}
		return model;

	}

	@RequestMapping(value = "/tasks/newTask", method = RequestMethod.POST)
	public ModelAndView createTask(HttpServletRequest request,
			@Valid @ModelAttribute("task") TaskForm task, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		model.setViewName("tasks/newTask");
		int projectId = Integer.valueOf((String) request.getSession()
				.getAttribute("projectId"));
		model.addObject("task", task);
		model.addObject("skills", skillManager.getSkills(username, projectId));
		model.addObject("resources",
				resourceManager.getResources(username, projectId));
		model.addObject("precedentTasks",
				taskManager.getTasks(username, projectId));
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Task taskToBeCreated = new Task();
			taskToBeCreated.setName(task.getName());
			taskToBeCreated.setUsername(username);
			taskToBeCreated.setProjectId(Integer.valueOf((String) request
					.getSession().getAttribute("projectId")));
			taskToBeCreated.setExclusive(task.isExclusive());
			taskToBeCreated.setEffort(task.getEffort());
			List<Skill> skillList = new ArrayList<Skill>();
			for (String id : task.getRequiredSkills()) {
				Skill skill = new Skill();
				skill.setId(Long.valueOf(id));
				skillList.add(skill);
			}
			taskToBeCreated.setRequiredSkills(skillList);

			List<Resource> resourceList = new ArrayList<Resource>();
			for (String id : task.getResources()) {
				Resource r = new Resource();
				r.setId(Long.valueOf(id));
				resourceList.add(r);
			}
			taskToBeCreated.setResources(resourceList);

			List<Task> taskList = new ArrayList<Task>();
			for (String id : task.getPrecedentTasks()) {
				Task t = new Task();
				t.setId(Long.valueOf(id));
				taskList.add(t);
			}
			taskToBeCreated.setId(task.getId());
			taskToBeCreated.setPrecedentTasks(taskList);
			if (0 == taskToBeCreated.getId()) {
				taskManager.createTask(taskToBeCreated);
			} else {
				taskManager.updateTask(taskToBeCreated);
			}
			if (!result.hasErrors()) {
				model.addObject("success", true);
			}
			task.setId(taskToBeCreated.getId());
		}
		return model;
	}

	@RequestMapping(value = "/tasks/newTask", method = RequestMethod.GET)
	public ModelAndView newTask(HttpServletRequest request, ModelMap model) {
		ModelAndView modelAux = new ModelAndView();
		TaskForm task = new TaskForm();
		task.setEffort(1.0f);
		model.addAttribute("task", task);
		modelAux.setViewName("tasks/newTask");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		int projectId = Integer.valueOf((String) request.getSession()
				.getAttribute("projectId"));
		model.addAttribute("skills",
				skillManager.getSkills(username, projectId));
		model.addAttribute("resources",
				resourceManager.getResources(username, projectId));
		model.addAttribute("precedentTasks",
				taskManager.getTasks(username, projectId));
		return modelAux;
	}

	@RequestMapping(value = "/tasks/removeTask", method = RequestMethod.POST)
	public ModelAndView removeSkill(HttpServletRequest request,
			@RequestParam("taskId") String taskId) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			Task s = new Task();
			s.setId(Long.valueOf(taskId));
			s.setUsername(username);
			s.setProjectId(Integer.valueOf((String) request.getSession()
					.getAttribute("projectId")));
			taskManager.removeTask(s);
			model.setViewName("tasks/tasks");
			model.addObject("tasks", taskManager.getTasks(
					username,
					Integer.valueOf((String) request.getSession().getAttribute(
							"projectId"))));
			model.addObject("success", true);
		}
		return model;
	}

	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

	public void setSkillManager(SkillManager skillManager) {
		this.skillManager = skillManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

}
