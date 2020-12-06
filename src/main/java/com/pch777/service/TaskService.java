package com.pch777.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pch777.entity.Task;
import com.pch777.entity.User;
import com.pch777.repository.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
	
	private TaskRepository taskRepository;

	public void addTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}
	
	public void updateTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}
	
	public void deleteTask(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + id));
		taskRepository.delete(task);
	}
	
	public Task findById(Long id) {
		return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + id));
	}
	
	
	public List<Task> findByUserAndCompletedIsTrueAndDescriptionLikeAndDeadlineBetween(User user, 
																					   String description,
																					   Date deadlineStart, 
																					   Date deadlineEnd) {
		return taskRepository.findByUserAndCompletedIsTrueAndDescriptionLikeAndDeadlineBetween(user, 
																							   "%" + description + "%",
																							   deadlineStart, 
																							   deadlineEnd);
	}
	
	public List<Task> findByUserAndCompletedIsFalseAndDescriptionLikeAndDeadlineBetween(User user, 
																						String description,
																					    Date deadlineStart,
																					    Date deadlineEnd) {
		return taskRepository.findByUserAndCompletedIsFalseAndDescriptionLikeAndDeadlineBetween(user, 
																								"%" + description + "%",
																							    deadlineStart,
																							    deadlineEnd);
	}
	
	public Page<Task> findAllByUserAndDescriptionLikeAndDeadlineBetween(User user, 
																		String description, 
																		Date deadlineStart,
																		Date deadlineEnd, 
																		Pageable pageable) {
		return taskRepository.findAllByUserAndDescriptionLikeAndDeadlineBetween(user, 
																				"%" + description + "%", 
																				deadlineStart, 
																				deadlineEnd, 
																				pageable);
	}
	
	public Page<Task> findAllByUserAndCompletedIsTrueAndDescriptionLikeAndDeadlineBetween(User user, 																						   
																						  String description, 
																						  Date deadlineStart,
																						  Date deadlineEnd, 
																						  Pageable pageable) {
		return taskRepository.findAllByUserAndCompletedIsTrueAndDescriptionLikeAndDeadlineBetween(user,
																							      "%" + description + "%", 
																								  deadlineStart, 
																								  deadlineEnd, 
																								  pageable);
	}
	
	public Page<Task> findAllByUserAndCompletedIsFalseAndDescriptionLikeAndDeadlineBetween(User user, 
																						   String description, 
																						   Date deadlineStart,
																						   Date deadlineEnd, 
																						   Pageable pageable) {
		return taskRepository.findAllByUserAndCompletedIsFalseAndDescriptionLikeAndDeadlineBetween(user, 
																							       "%" + description + "%", 
																								   deadlineStart, 
																								   deadlineEnd, 
																								   pageable);
	}

	

	
	
}
