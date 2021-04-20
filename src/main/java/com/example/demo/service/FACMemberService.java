package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FACMember;
import com.example.demo.repository.FACMemberRepository;
import com.example.demo.payload.MessageResponse;

@Service
public class FACMemberService {

	@Autowired
	private FACMemberRepository repo;

	public FACMemberService(FACMemberRepository repo) {
		this.repo = repo;
	}

	public FACMember postFACMember(FACMember facMember) {
		return repo.save(facMember);
	}

	public FACMember getFACMember(long FACId) {
		return repo.findById(FACId).orElse(null);
	}


	public List<FACMember> findAll() {
	
		return repo.findAll();
	}

	public ResponseEntity<?> create(FACMember facmember) {
		this.repo.save(facmember);
		return ResponseEntity.status(200).body(new MessageResponse("Created Successfully"));
	}

	public List<FACMember> getAll() {
		return repo.findAll();
	}


}
