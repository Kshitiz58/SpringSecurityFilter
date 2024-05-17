package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.security.model.MyUser;
import com.spring.security.repository.MyUserRepository;

@Controller
public class PageController {
	
	@Autowired 
	private MyUserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/home")
	public String getHomepage() {
		return "home";
	}
	@GetMapping("/admin/home")
	public String getAdminpage() {
		return "admin";
	}
	@GetMapping("/user/home")
	public String getUserpage() {
		return "user";
	}
	@GetMapping("/login")
	public String getLoginpage() {
		return "login";
	}
	@GetMapping("/signup")
	public String getSignuppage() {
		return "signup";
	}
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute MyUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "redirect:/login";
	}
}
