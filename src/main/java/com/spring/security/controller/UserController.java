package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.security.model.MyUser;
import com.spring.security.repository.CustomUserRepository;

@Controller
public class UserController {
	
	@Autowired 
	private CustomUserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String getHomepage() {
		return "index";
	}
	@GetMapping("/admin/home")
	public String getAdminpage() {
		return "admin";
	}
	@GetMapping("/user/home")
	public String getUserpage() {
		return "user";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute MyUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "redirect:/";
	}
}
