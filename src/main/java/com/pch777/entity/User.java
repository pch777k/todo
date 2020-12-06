package com.pch777.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends AuditModel {

	@Id
	@Email(message = "Please enter a valid e-mail address")
	@NotEmpty(message = "Email must not be empty")
	@Column(unique = true)
	private String email;
	@NotEmpty(message = "Name must not be empty")
	private String name;
	@Size(min = 3, message = "Password should be at least 3 characters")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Task> tasks;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "users_roles",
			   joinColumns = { @JoinColumn(name = "user_email", referencedColumnName = "email") },
			   inverseJoinColumns = { @JoinColumn(name = "role_name", referencedColumnName = "name") })
	private List<Role> roles;
	
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
}
