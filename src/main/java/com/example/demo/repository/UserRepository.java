package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.Roles;
import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
<<<<<<< HEAD
	AssistentRegistrar findByRoles(Optional<Roles> role);
=======
	//User getById(Long userId);
>>>>>>> 8ff7ad82f2521e5cef821f951247516812963dfb
}
