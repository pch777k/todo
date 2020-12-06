package com.pch777.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pch777.entity.Role;
import com.pch777.entity.User;
import com.pch777.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

private UserRepository userRepository;
private PasswordEncoder passwordEncoder;
	
	public void createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = new Role("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
	}
	
	public User findOne(String email) {
		return userRepository.findById(email).orElseThrow(() -> new IllegalArgumentException("Invalid email: " + email));
	}
	
	public boolean isUserPresent(String email) {
		if(userRepository.findById(email).isPresent()) {
			return true;
		}
		return false;
	}
		
}
