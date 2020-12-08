package com.pch777.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pch777.entity.Task;
import com.pch777.service.TaskService;
import com.pch777.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TaskController {

	private TaskService taskService;
	private UserService userService;

	@PostMapping("/addTask/{partUrl}")
	public String addTask(@Valid Task task, @PathVariable String partUrl, BindingResult bindingResult,
			Principal principal) {
		if (bindingResult.hasErrors()) {
			return "redirect:/main";
		}
		String email = principal.getName();
		taskService.addTask(task, userService.findOne(email));
		return "redirect:/main?" + partUrl;
	}

	@GetMapping("/findOne")
	@ResponseBody
	public Task findOne(long id) {
		Task task = taskService.findById(id);
		return task;
	}
	
	@PostMapping("/changeCompletedField/{id}/{partUrl}")
	public String changeCompletedField(@PathVariable("id") Long id, @PathVariable String partUrl, Principal principal) {
		Task task = taskService.findById(id);
		task.setCompleted(!task.isCompleted());
		String email = principal.getName();
		taskService.updateTask(task, userService.findOne(email));
		return "redirect:/main?" + partUrl;
	}

	@PostMapping("/updateTask/{partUrl}")
	public String updateTask(Task task, @PathVariable String partUrl, Principal principal) {	
		String email = principal.getName();	
		taskService.updateTask(task, userService.findOne(email));
		return "redirect:/main?" + partUrl;
	}

	@RequestMapping("/delete/{id}/{partUrl}")
	public String deleteTask(@PathVariable Long id, @PathVariable String partUrl) {
		taskService.deleteTask(id);
		return "redirect:/main?" + partUrl;
	}

}
