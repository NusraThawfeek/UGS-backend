package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.Roles;
//import com.example.demo.entity.AssistentRegistrar;
//import com.example.demo.entity.Roles;
import com.example.demo.entity.User;
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
	AssistentRegistrar findByRoles(Optional<Roles> role);
	
}
//	AssistentRegistrar findByRoles(Optional<Roles> role);

