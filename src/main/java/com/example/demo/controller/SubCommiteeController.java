package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SubComittee;
import com.example.demo.service.SubCommiteeService;


@CrossOrigin(origins = "*")
@RestController
public class SubCommiteeController {

	@Autowired
	private SubCommiteeService service;
	
//	@PostMapping("/apppointsubcommitee")
//	public SubComittee postSubCommitee(String purpose, String discripition1, int leaderId, int facMemberId[]) {
//		return service.postSubCommitee(purpose, discripition1, leaderId, facMemberId);
//	}
	public SubCommiteeController(SubCommiteeService service) {
		this.service = service;
	}

	@PostMapping(path = "/subcomittee")
	public ResponseEntity<?> create(@RequestBody SubComittee subcomittee) {
		return service.create(subcomittee);
	}

	@GetMapping(path = "/subcomittee/get")
	public List<SubComittee> getAll() {
		return service.getAll();
	}

	@PostMapping(path = "/subcomittee/fileupload/{id}")
	public ResponseEntity<?> uploadFile(@PathVariable(value = "id") Long id, @RequestBody SubComittee subcomittee) {
		return service.uploadFile(id, subcomittee);
	}

	@PutMapping(path = "/subcomittee/statusupdate/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable(value = "id") Long id, @RequestBody SubComittee subcomittee) {
		return service.updateStatus(id, subcomittee);
	}
}
