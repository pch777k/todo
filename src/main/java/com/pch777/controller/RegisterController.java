package com.pch777.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pch777.entity.User;
import com.pch777.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegisterController {

	private UserService userService;

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "registerForm";
	}

	@PostMapping("/register")
	public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "registerForm";
		}

		if (userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist", true);
			return "registerForm";
		}

		userService.createUser(user);
		
		model.addAttribute("userName", user.getName());
		return "registerSuccess";
	}

}
