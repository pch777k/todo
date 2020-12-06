package com.pch777.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pch777.entity.Task;
import com.pch777.entity.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUser(User user);
	List<Task> findByUserAndCompletedIsTrueAndDescriptionLikeAndDeadlineBetween( User user, 
																				 String description, 
																				 Date deadlineStart,
																				 Date deadlineEnd);
	List<Task> findByUserAndCompletedIsFalseAndDescriptionLikeAndDeadlineBetween(User user, 
																				 String description, 
																				 Date deadlineStart,
																				 Date deadlineEnd);
	List<Task> findByUserAndCompletedIsFalseAndDescriptionLike(User user, String description);
	List<Task> findByDescriptionLike(String description);
	List<Task> findByUserAndDescriptionLike(User user, String description);
	
	Page<Task> findAllByUser(User user, Pageable pageable);
	
	Page<Task> findAllByUserAndDeadlineAndDescriptionLike(User user, Date deadline, String description, Pageable pageable);
	Page<Task> findAllByUserAndDescriptionLikeAndDeadlineBetween(User user, 
																 String description, 
																 Date deadlineStart,
																 Date deadlineEnd, 
																 Pageable pageable);
	
	Page<Task> findAllByUserAndCompletedIsTrueAndDescriptionLikeAndDeadlineBetween(User user, 
																				   String description, 
																				   Date deadlineStart,
																				   Date deadlineEnd, 
																				   Pageable pageable);
	
	Page<Task> findAllByUserAndCompletedIsFalseAndDescriptionLikeAndDeadlineBetween(User user, 
																					String description, 
																					Date deadlineStart,
																					Date deadlineEnd, 
																					Pageable pageable);
	

	
}
