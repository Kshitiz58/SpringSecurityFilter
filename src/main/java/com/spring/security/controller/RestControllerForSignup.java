package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.model.MyUser;
import com.spring.security.repository.CustomUserRepository;

@RestController
public class RestControllerForSignup {

	@Autowired
	private CustomUserRepository myUserRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register/user")
	public MyUser CreateUser(@RequestBody MyUser user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return myUserRepo.save(user);
	}
}
