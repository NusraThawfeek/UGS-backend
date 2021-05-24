package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.SubComittee;
import com.example.demo.repository.SubCommiteeRepository;
import com.example.demo.payload.MessageResponse;

@Service
public class SubCommiteeService {

	@Autowired
	private SubCommiteeRepository repository;

	@Autowired
	private FACMemberService facMemberService;

//	public SubComittee postSubCommitee(String purpose, String discripition1, int leaderId, int facMemberId[]) {
//		Date appointedDate = new Date();
//		
//		List<FACMember> facMember = new ArrayList<>();
//		
//		for(int i = 0; i < facMemberId.length; i++) {
//			facMember.add(facMemberService.getFACMember(facMemberId[i]));
//		}
//		SubComittee subComittee = new SubComittee(purpose, discripition1, appointedDate, facMemberService.getFACMember(leaderId), facMember);
//		
//		return repository.save(subComittee);
//	}
	public SubCommiteeService(SubCommiteeRepository repository) {
		this.repository = repository;
	}

	public ResponseEntity<?> create(SubComittee subcomittee) {
		repository.save(subcomittee);
		return ResponseEntity.status(200).body(new MessageResponse("created"));
	}

	public List<SubComittee> getAll() {
		return repository.findAll();
	}

	public ResponseEntity<?> uploadFile(Long id, SubComittee fileName) {
		SubComittee subcomitee = repository.findById(id).orElseThrow();
		subcomitee.setSubcomitteefileName(fileName.getSubcomitteefileName());
		SubComittee res = repository.save(subcomitee);
		return ResponseEntity.ok(res);
	}

	public ResponseEntity<?> updateStatus(Long id, SubComittee subcomittee) {
		SubComittee sm = repository.findById(id).orElseThrow();
		sm.setSubcomitteeStatus(subcomittee.getSubcomitteeStatus());
		SubComittee res = repository.save(sm);
		return ResponseEntity.ok(res);
	}
	
}
