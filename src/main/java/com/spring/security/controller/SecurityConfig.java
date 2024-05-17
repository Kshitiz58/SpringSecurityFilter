package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.security.service.AuthSuccessHandler;
import com.spring.security.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserDetailsService myUserDetailService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.
				csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(registry -> {
					registry.requestMatchers("/css/**", "/error/**").permitAll();
					registry.requestMatchers("/home", "/register/**").permitAll();
					registry.requestMatchers("/admin/**").hasRole("ADMIN");
					registry.requestMatchers("/user/**").hasRole("USER");
					registry.anyRequest().authenticated();
				})

//				Abstracted Method
				.formLogin(httpSecurityFormLoginConfigurer -> {
					httpSecurityFormLoginConfigurer
					.loginPage("/login")
					.successHandler(new AuthSuccessHandler())
					.permitAll();
				})

				.build();

	}

	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailService;
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider
		.setUserDetailsService(myUserDetailService);
		provider
		.setPasswordEncoder(passwordEncoder());
		return provider;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
