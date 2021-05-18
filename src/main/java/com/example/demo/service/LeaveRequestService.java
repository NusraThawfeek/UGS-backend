package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.Student;
import com.example.demo.exception.FileStorageException;
import com.example.demo.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {

	@Autowired
	private LeaveRequestRepository repository;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DecisionMailService mailService;

	public LeaveRequest submitLeaveRequest(int sid, String leaveType, String purpose, Date from, Date to, int localDays,
			int totalDays, int semesterDays, String reason, MultipartFile annex) throws MessagingException {

		String fileName = StringUtils.cleanPath(annex.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
			String datetime = ft.format(dNow);

			File convertFile = new File("E:\\Spring Boot\\ugs\\src\\main\\resources\\static\\Upload\\annex\\" + "FAC-"
					+ datetime + annex.getOriginalFilename());

			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(annex.getBytes());
			fout.close();

			String filePath = "E:\\Spring Boot\\ugs\\src\\main\\resources\\static\\Upload\\annex\\" + "FAC-" + datetime
					+ annex.getOriginalFilename();

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date enteredDate = new Date();
			
			int overseasDays = totalDays - localDays;
			int vacationDays = totalDays - semesterDays;

			LeaveRequest req = new LeaveRequest(filePath, "", enteredDate, false, leaveType, purpose, from, to,
					localDays, overseasDays, totalDays,
					semesterDays, vacationDays, reason, studentService.getStudent(sid), "Leave");

			Student std=studentService.getStudent(sid);
    		
    		mailService.newRequest(std.getAcademicAdvisor(),"AcademicAdvisor", std);
			return repository.save(req);
		

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public LeaveRequest getRequest(int rid) {
		return repository.findById(rid).orElse(null);
	}

	public List<LeaveRequest> getAllRequest() {
		return repository.findAll();
	}

	public List<LeaveRequest> pastLeaveRequest(int sid) {

		return repository.findByStd(studentService.getStudent(sid));
	}

	public LeaveRequest updateRequest(LeaveRequest request) throws UnsupportedEncodingException, MessagingException {

		LeaveRequest existingRequest = getRequest(request.getRid());

		existingRequest.setStatus(request.getStatus());
		existingRequest.setDecision(request.getDecision());
		
		Student student = existingRequest.getStd();
		
		if (existingRequest.getLeaveType().equals("Long Duration")) {
			student.setLongTermBal(student.getLongTermBal() - existingRequest.getTotalDays());
		}

		else if (existingRequest.getLeaveType().equals("Short Duration")) {
			student.setShortTermBal(student.getShortTermBal() - existingRequest.getTotalDays());
			student.setLongTermBal(student.getLongTermBal() - existingRequest.getTotalDays());
		}

		repository.save(existingRequest);
		
		mailService.decsisionEmail(existingRequest, "http://localhost:3000");

		return existingRequest;
	}
	
	public List<LeaveRequest> getAllLeaveReqForDecision(){
		List<LeaveRequest> allReq = repository.findAll();
		
		List<LeaveRequest> decReq = new ArrayList<>();
		
		for(int i = 0; i < allReq.size(); i++) {
			if((allReq.get(i).getIsSendToFacBoard() == true) && allReq.get(i).getDecision().equals("")) {
				decReq.add(allReq.get(i));
			}
		}
		
		return decReq;
	}
}
