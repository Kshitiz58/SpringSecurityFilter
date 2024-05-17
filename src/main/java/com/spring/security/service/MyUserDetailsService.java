package com.spring.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.model.MyUser;
import com.spring.security.repository.MyUserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private MyUserRepository myUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<MyUser> user = myUserRepo.findByUsername(username);
		
		if(user.isPresent()) {
			var userObj = user.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.roles(getRole(userObj))
					.build();
		}else {
			throw new UsernameNotFoundException("User Not Found: "+username);
		}
	}
	private String[] getRole (MyUser user) {
		if (user.getRole() == null || user.getRole().isEmpty()) {
			return new String[] {"USER"};
		}
		return user.getRole().split(",");
	}

}
