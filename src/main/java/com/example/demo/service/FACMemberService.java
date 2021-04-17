package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FACMember;
import com.example.demo.repository.FACMemberRepository;

@Service
public class FACMemberService {

	@Autowired
	private FACMemberRepository repo;
	
	public FACMember postFACMember(FACMember facMember) {
		return repo.save(facMember);
	}
	
	public FACMember getFACMember(long FACId) {
		return repo.findById(FACId).orElse(null);
	}

	public List<FACMember> findAll() {
	
		return repo.findAll();
	}
}
