package com.example.demo.controller;


import java.util.Date;
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
import com.example.demo.entity.FACMeeting;
import com.example.demo.service.FACMeetingService;

@CrossOrigin(origins = "*")
@RestController
public class FACMeetingController {

	@Autowired
	private FACMeetingService service;

	public FACMeetingController(FACMeetingService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/meetings/{id}")
	public FACMeeting getMeetingById(@PathVariable("id") Integer id) {
		return service.getByMeetingId(id);

	}
	@PostMapping(path = "/meetings")
	public ResponseEntity<?> create(@RequestBody FACMeeting facMeeting) {
		return service.create(facMeeting);
	}

	@GetMapping(path = "/meetings/get")
	public List<FACMeeting> getAll() {
		return service.getAll();
	}

	@PutMapping(path = "/meetings/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Integer id, @RequestBody FACMeeting facMeeting) {
		return service.Update(id, facMeeting);
	}

	@PostMapping(path = "/meetings/mail/{id}")
	public ResponseEntity<?> sendmail(@PathVariable("id") Integer id, @RequestParam String[] mail) {
		return service.sendmail(id, mail);
	}
	
	@GetMapping(path = "/meetings/upcoming")
	public FACMeeting findUpcomingMeeting() {
		return service.findUpcomingMeeting();
	}
	
	@GetMapping(path = "/meetings/pastMeeting")
	public List<FACMeeting> findPastMeeting() {
		return service.findPastMeeting();
	}
}
