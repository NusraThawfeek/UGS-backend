package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FACMember;
import com.example.demo.entity.User;



public interface FACMemberRepository extends JpaRepository<FACMember, Long> {
	FACMember getByuserId(Long userId);

	List<FACMember> findAll();
}
