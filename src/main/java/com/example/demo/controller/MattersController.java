package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Matters;
import com.example.demo.service.MattersService;

@CrossOrigin(origins = "*")
@RestController
public class MattersController {
	@Autowired
	private MattersService service;
	
	@PostMapping("/meetings/addMeetingMatters")
	public String submitLateModuleChangeRequest(@RequestParam int meetingId,@RequestParam String matters[],
			@RequestParam String deci[],@RequestParam String deci_by[]){
		service.submitMeetingMatters(meetingId, matters, deci, deci_by);		
		return "Success";
	}
	@GetMapping(path = "/meetings/getMattersByFacId/{id}")
	public List<Matters> getByFacId(@PathVariable("id") Integer meetingId) {
		return service.getByFacMeetingId(meetingId);
	}
}
