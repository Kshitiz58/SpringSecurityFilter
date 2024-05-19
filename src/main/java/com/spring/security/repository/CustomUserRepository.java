package com.spring.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.model.MyUser;


public interface CustomUserRepository extends JpaRepository<MyUser, Long>{

	Optional<MyUser> findByUsername(String username);
}
