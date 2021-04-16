package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.FACMember;
import com.example.demo.entity.Request;
import com.example.demo.service.FileDownloadService;
import com.example.demo.service.RequestService;

@RestController
@CrossOrigin(origins = "*")
public class RequestController {

	@Autowired
	private RequestService service;
	
	@Autowired
	private FileDownloadService fileDownloadService;
	
	@GetMapping("/getrequestbyrid/{rid}")
	public Request getRequest(@PathVariable int rid) {
		return service.getRequest(rid);
	}
	
	@GetMapping("/getrequestbysid/{sid}")
	public List<Request> getRequestBySid(@PathVariable int sid){
		return service.getRequestBySid(sid);
	}
	
	@GetMapping("/request/download_annex/{rid}")
	public void AnnexDownload(HttpServletResponse response, @PathVariable int rid) throws IOException{
		fileDownloadService.AnnexDownload(response, rid);
	}
	
	
	@GetMapping("/StudentReq")
	public  List<Request> getStudentReqs() {
	
		return service.getStudentReqs();
	}
	

	@GetMapping("/PastReq/{uid}")
	public List<Request> getPastReq(@PathVariable FACMember uid) {
		
		return service.getPastReq(uid);
	}
	
	
	@GetMapping("/NewReqAcademicAdvisor/{uid}")
	public List<Request> getNewReqAcademicAdvisor(@PathVariable FACMember uid) {
		
		return service.getNewReqAcademicAdvisor(uid);
	}
	
	@GetMapping("/NewReqHOD/{uid}")
	public List<Request> getNewHOD(@PathVariable FACMember uid) {
		
		return service.getNewReqHOD(uid);
	}
	
	@GetMapping("/NewReqDean/{uid}")
	public List<Request> getNewDean(@PathVariable FACMember uid) {
		
		return service.getNewReqDean(uid);
	}
	
	@GetMapping("/NewReqDUGS/{uid}")
	public List<Request> getNewDUGS(@PathVariable FACMember uid) {
		
		return service.getNewReqDUGS(uid);
	}
	
	@GetMapping("/StudentReqByIndexNo/{indexNo}")
	public List<Request> getReqByindexNo(@PathVariable String indexNo) {
		
		return service.getReqByindexNo(indexNo);
	}
}
