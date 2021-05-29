package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Priliminary;
import com.example.demo.service.PriliminaryService;

@CrossOrigin(origins = "*")
@RestController
public class PriliminaryController {
	@Autowired
	private PriliminaryService service;
	
	@PostMapping(path ="/meetings/addPriliminaries")
	public String submitLateModuleChangeRequest(@RequestParam int meetingId,@RequestParam String prilim[]){
		service.submitPriliminaries(meetingId, prilim);		
		return "Success";
	}
	@GetMapping(path = "/meetings/getPriliminaryByFacId/{id}")
	public List<Priliminary> getMattersByFacId(@PathVariable("id") Integer meetingId) {
		return service.getPrilimByfacmeeting_id(meetingId);
	}

}
