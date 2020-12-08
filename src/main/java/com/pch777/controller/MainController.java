package com.pch777.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pch777.entity.Task;
import com.pch777.entity.User;
import com.pch777.service.TaskService;
import com.pch777.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MainController {

	private TaskService taskService;
	private UserService userService;
	
	@GetMapping("/main")
	public String showAll(Model model, 
						  Principal principal, 
						  @RequestParam(defaultValue = "") String description,
						  @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date deadlineStart,
						  @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date deadlineEnd,
						  @RequestParam(defaultValue = "0") int status,
						  @RequestParam(defaultValue = "1") int page,
						  @RequestParam(defaultValue = "10") int pageSize,
						  @RequestParam(defaultValue = "description") String sortBy,
						  @RequestParam(defaultValue = "cards") String display)  {
		
		
		String email = principal.getName();
		User user = userService.findOne(email);

		Date today = new Date();
		
		if(deadlineStart == null) {
			deadlineStart = today;
		}
		
		if(deadlineEnd == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(today); 
			calendar.add(Calendar.DATE, 14);
			deadlineEnd = calendar.getTime();
		}
		
		Sort sortDir = Sort.by(sortBy);
		if(sortBy.contains("Desc")) {
			sortBy = sortBy.substring(0, sortBy.length()-4);
			sortDir = Sort.by(sortBy).descending();
			sortBy += "Desc";
		}
		
		Pageable pageable = PageRequest.of(page-1, pageSize, sortDir);
		
		Page<Task> tasks = taskService.findAllByUserAndDescriptionLikeAndDeadlineBetween(user, 
																						 description, 
																						 deadlineStart, 
																						 deadlineEnd, 
																						 pageable);
		model.addAttribute("totalItmes", tasks.getTotalElements());
		
			if(status == 1) {
				tasks = taskService.findAllByUserAndCompletedIsTrueAndDescriptionLikeAndDeadlineBetween(user, 
																										description,
																										deadlineStart, 
																										deadlineEnd, 
																										pageable);
			}
			if(status == 2) {
				tasks = taskService.findAllByUserAndCompletedIsFalseAndDescriptionLikeAndDeadlineBetween(user, 
																									     description,
																										 deadlineStart, 
																										 deadlineEnd, 
																										 pageable);
			}
		
		model.addAttribute("totalActive", taskService.findByUserAndCompletedIsFalseAndDescriptionLikeAndDeadlineBetween(user, 
																														description,
																														deadlineStart,
																														deadlineEnd).size());
		model.addAttribute("totalCompleted", taskService.findByUserAndCompletedIsTrueAndDescriptionLikeAndDeadlineBetween(user, 
																														  description,
																														  deadlineStart,
																														  deadlineEnd).size());

		model.addAttribute("dEnd", deadlineEnd);
		model.addAttribute("dStart", deadlineStart);
		model.addAttribute("createdUserDate", user.getCreatedAt());
		model.addAttribute("userName", user.getName());
		model.addAttribute("title", description);	
		model.addAttribute("sortingBy", sortBy);
		model.addAttribute("tasks", tasks);
		model.addAttribute("task", new Task());	
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", page);
		model.addAttribute("currentSize", pageable.getPageSize());
		model.addAttribute("currentStatus", status);
		model.addAttribute("currentDisplay", display);
		
		return "main";
	}
	
}
