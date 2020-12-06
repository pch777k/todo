package com.pch777.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {

	@Id
	@NotEmpty
	private String name;
	
	@ManyToMany(mappedBy="roles")
	private List<User> users;
		
	public Role(String name) {
		this.name = name;
	}
	
}
