package com.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.model.MyUser;
import com.spring.security.repository.CustomUserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private CustomUserRepository myUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUser user = myUserRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));
		
		return User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(getRoles(user))
				.build();
	}

	private String[] getRoles (MyUser user) {
		if (user.getRole() == null || user.getRole().isEmpty()) {
			return new String[] {"USER"};
		}
		return user.getRole().split(",");
	}

}
