package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

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
}
