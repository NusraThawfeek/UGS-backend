package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
	
}
