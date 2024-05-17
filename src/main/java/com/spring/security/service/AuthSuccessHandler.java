package com.spring.security.service;

import java.io.IOException;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.Authentication authentication) throws ServletException, IOException {
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority
				.getAuthority()
				.equals("ROLE_ADMIN"));
		if (isAdmin) {
			setDefaultTargetUrl("/admin/home");
		} else {
			setDefaultTargetUrl("/user/home");
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
