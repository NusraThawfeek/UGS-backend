package com.example.demo.controller;

import java.util.List;
<<<<<<< HEAD

=======
>>>>>>> 8ff7ad82f2521e5cef821f951247516812963dfb
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.FACMember;
import com.example.demo.service.FACMemberService;



@RestController
@CrossOrigin(origins = "*")
public class FACMemberController {

	@Autowired
	private FACMemberService service;

	public FACMemberController(FACMemberService service) {
		this.service = service;
	}

	@PostMapping("/regfacmember")
	public FACMember postFACMember(@RequestBody FACMember facMember) {
		return service.postFACMember(facMember);
	}

	@GetMapping("facmember/{facId}")
	public FACMember FACMember(@PathVariable int facId) {
		return service.getFACMember(facId);
	}
<<<<<<< HEAD
	
	@GetMapping("/facmember")
	public  List<FACMember> getFACmembers() {
	
		return service.findAll();
	}
=======

	@PostMapping(path = "/facmember/create")
	public ResponseEntity<?> create(FACMember facmember) {
		return this.service.create(facmember);
	}

	@GetMapping(path = "/facmembers")
	public List<FACMember> getAll() {
		return service.getAll();
	}

>>>>>>> 8ff7ad82f2521e5cef821f951247516812963dfb
}
