package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Attend;
import com.example.demo.repository.AttendRepository;
import com.example.demo.service.AttendService;



@RestController
public class AttendController {

	@Autowired
	private AttendService service;
	public AttendRepository attendancerepo;
	
	public AttendController(AttendService service,AttendRepository attendancerepo) {
		this.service=service;
		this.attendancerepo=attendancerepo;
	}
	/*
	 * @PostMapping("/postattend") public Attend postAttend(@RequestParam int
	 * facMemberId, @RequestParam int facMeetingId,
	 * 
	 * @RequestParam boolean attendence, @RequestParam String apology) {
	 * 
	 * return service.postAttend(facMemberId, facMeetingId, attendence, apology); }
	 */

	@PostMapping(path = "/attendance/create")
	public ResponseEntity<?> create(@RequestBody List<Attend> attendance) {
		return service.create(attendance);
	}

	@GetMapping(path = "/attendance/getByMeetingId/{meetingId}")
	public List<Attend> getByMeetingId(@PathVariable("meetingId") Integer meetingId) {
		return this.service.getByFacMeetingId(meetingId);
	}

	@GetMapping(path = "/attendance/getByMeetingDate/{date}")
	public List<Attend> getByMeetingDate(@PathVariable("date") Date date) {

		return this.service.getByFacMeetingDate(date);
	}
}
