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

import com.example.demo.entity.FACMeeting;
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
	@PostMapping("/meetings/addMeetingAgenda")
	public String submitLateAgendaItem(@RequestParam int meetingId,@RequestParam String matters[]){
		service.submitAgendaItems(meetingId, matters);		
		return "Success";
	}
	@GetMapping(path = "/meetings/getMattersByFacId/{id}")
	public List<Matters> getMattersByFacId(@PathVariable("id") Integer meetingId) {
		return service.getByfacmeeting_id(meetingId);
	}
	@GetMapping(path = "/meetings/getMattersByFacId/{id}/{minute}")
	public List<Matters> getMattersByFacId(@PathVariable("id") Integer meetingId,@PathVariable("minute") Boolean minute) {
		return service.getMinuteByFacMeetingId(meetingId,minute);
	}
	@GetMapping(path = "/meetings/getAgendaByFacId/{id}/{agenda}")
	public List<Matters> getAgendaByFacId(@PathVariable("id") Integer meetingId,@PathVariable("agenda") Boolean agenda) {
		return service.getAgendaByFacMeetingId(meetingId,agenda);
	}
	@PutMapping("/meetings/addAgendaDecission")
	public String submitAgendaDecision(@RequestParam int Id[],@RequestParam String deci[],@RequestParam String deci_by[]){
		service.submitAgendaDecision(Id,deci,deci_by);
		return "Success";
	}
}
