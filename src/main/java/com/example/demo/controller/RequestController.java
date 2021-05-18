package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Commented;
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
	
	@GetMapping("/NewReqAR")
	public List<Request> getNewAR() {
		
		return service.getNewReqAR();
	}
	
	@GetMapping("/PastReqAR")
	public List<Request> getPastAR() {
		
		return service.getPastReqAR();
	}
	
	
	@GetMapping("/StudentReqByIndexNo/{indexNo}")
	public List<Request> getReqByindexNo(@PathVariable String indexNo) {
		
		return service.getReqByindexNo(indexNo);
	}
	
	@PutMapping("/AddRequestToAgenda")
	public Request addToAgenda(@ModelAttribute Request r) {	
		return service.updateAddToAgenda(r);
	}
	
	@GetMapping("/getrequestbyfacid/{facid}")
	public List<Request> getRequestByFacid(@PathVariable int facid){
		return service.getRequestByFacid(facid);
	}
	
	@PutMapping("/updateSendToFACBoard")
	public Request updateSendToFACBoard(@RequestBody Request request) {
		return service.updateSendToFACBoard(request);
	}
}
