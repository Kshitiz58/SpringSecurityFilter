package com.spring.security.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(registry -> {
			registry.requestMatchers("/home").permitAll();
			registry.requestMatchers("/admin/**").hasRole("ADMIN");
			registry.requestMatchers("/user/**").hasRole("USER");
			registry.anyRequest().authenticated();
		})
//				.formLogin(formLogin -> formLogin.permitAll())
//				Abstracted Method
				.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
				.build();

	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("sulav").password(passwordEncoder().encode("123")).roles("USER")
				.build();

		UserDetails admin = User.builder().username("ram").password(passwordEncoder().encode("789")).roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
