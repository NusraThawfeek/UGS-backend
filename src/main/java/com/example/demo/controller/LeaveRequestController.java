package com.example.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.service.FileDownloadService;
import com.example.demo.service.LeaveRequestService;

@RestController
@CrossOrigin(origins = "*")
public class LeaveRequestController {

	@Autowired
	private LeaveRequestService service;

	@PostMapping("/request/leaverequest")
	public LeaveRequest submitLeaveRequest(@RequestParam int sid, @RequestParam String leaveType, @RequestParam String purpose, @RequestParam Date from, 
			@RequestParam Date to, @RequestParam int localDays, @RequestParam int totalDays, @RequestParam int semesterDays, @RequestParam String reason,
			@RequestParam MultipartFile annex) throws MessagingException {

		return service.submitLeaveRequest(sid, leaveType, purpose, from, to, localDays, totalDays, semesterDays, reason, annex);
	}

	@GetMapping("/pastrequest/leaverequestbyrid/{rid}")
	public LeaveRequest pastRequest(@PathVariable int rid) {
		return service.getRequest(rid);
	}

	@GetMapping("/pastrequest/getallleaverequest")
	public List<LeaveRequest> getAllrequest() {
		return service.getAllRequest();
	}

	@GetMapping("/pastrequest/leaverequest/{sid}")
	public List<LeaveRequest> pastLeaveRequest(@PathVariable int sid) {
		return service.pastLeaveRequest(sid);
	}

	@PutMapping("/updateleaverequest")
	public LeaveRequest updateRequest(@RequestBody LeaveRequest request) throws UnsupportedEncodingException, MessagingException {
		return service.updateRequest(request);
	}
	
	private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
