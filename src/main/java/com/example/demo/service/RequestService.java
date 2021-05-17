package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FACMeeting;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.Request;
import com.example.demo.entity.Student;
import com.example.demo.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repository;

	@Autowired
	private StudentService service;
	
	@Autowired
	private FACMeetingService facservice;

	public Request getRequest(int rid) {
		return repository.findById(rid).orElse(null);
	}

	public List<Request> getRequestBySid(int sid) {
		return repository.findByStd(service.getStudent(sid));
	}
	
	public List<Request> getStudentReqs() {

		return repository.findAll();
	}
	
	public List<Request> getPastReq(FACMember uid) {
		// TODO Auto-generated method stub
		return repository.findByuid(uid);
	}
	
	public List<Request> getNewReqAcademicAdvisor(FACMember uid) {
		// TODO Auto-generated method stub
		return repository.findByuidnew(uid);
	}
	public List<Request> getNewReqHOD(FACMember uid) {
		// TODO Auto-generated method stub
		return repository.findnewRequestForHod(uid);
	}

	public List<Request> getNewReqDean(FACMember uid) {
		
		return repository.findnewRequestForDean(uid);
	}
	
	public List<Request> getNewReqDUGS(FACMember uid) {
		
		return repository.findnewRequestForDUGS(uid);
	}
	
	public List<Request> getReqByStudentId(Student sid) {
		// TODO Auto-generated method stub
		return repository.findByStd(sid);
	}

	public List<Request> getReqByindexNo(String indexNo) {
		// TODO Auto-generated method stub
		return repository.findByindexNo(indexNo);
	}
	
	public List<Request> getRequestByFacid(int fid) {
		return repository.findByFacMeeting1(facservice.getMeeting(fid));
	}

	public Request updateAddToAgenda(Request r) {
		Request req=getRequest(r.getRid());
		FACMeeting fm1=facservice.findUpcomingMeeting();    
		req.setIsSendToFacBoard(r.getIsSendToFacBoard());
		req.setFacMeeting1(fm1);							
		return repository.save(req);
	}

	public List<Request> getNewReqAR() {
		// TODO Auto-generated method stub
		return repository.findNewRequestForAR();
	}
	
	public List<Request> getPastReqAR() {
		// TODO Auto-generated method stub
		return repository.findPastRequestForAR();
	}
}
