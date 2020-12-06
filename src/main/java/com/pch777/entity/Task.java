package com.pch777.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tasks")
public class Task extends AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String description;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date deadline;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "priority")
	private Priority priority;
	
	@Column(name="completed")
	boolean completed; 
	
	@ManyToOne
	@JoinColumn(name="user_email")
	@JsonBackReference
	private User user;

	public Task(@NotEmpty String description, Date deadline, Priority priority, boolean completed, User user) {
		this.description = description;
		this.deadline = deadline;
		this.priority = priority;
		this.completed = completed;
		this.user = user;
	}
	
}	