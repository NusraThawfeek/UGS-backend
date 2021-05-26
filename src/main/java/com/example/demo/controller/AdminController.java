package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.dto.request.ChangePasswordRequest;
import com.example.demo.dto.request.StudentBatchRequest;
import com.example.demo.dto.request.StudentSingleRegister;
import com.example.demo.dto.response.AcademicAdvisorListResponse;
import com.example.demo.dto.response.ArInformationResponse;
import com.example.demo.dto.response.FacInformationResponse;
import com.example.demo.dto.response.StudentInformationResponse;
import com.example.demo.dto.response.UgsInformationResponse;
import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.Student;
import com.example.demo.entity.UgsStaff;
import com.example.demo.service.interfaces.IAdminService;
import com.example.demo.utils.ExcelHelper;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

	private static Logger log = Logger.getLogger(AdminController.class);

	@Autowired
	private ExcelHelper helper;

	@Autowired
	private IAdminService service;

	@PostMapping("/register/student/single")
	public ResponseEntity<String> registerSingleStudent(@Valid @RequestBody StudentSingleRegister registerStudent) {
		log.info("Registration of Single Student called, Index:" + registerStudent.getIndexNumber());
		String index = service.saveStudent(registerStudent);
		if(index == null) {
			return ResponseEntity.ok("User Account Already exists with email or index number\n Email : " + registerStudent.getEmail() + "\n Index Number:  "+registerStudent.getIndexNumber());
		}else {
			return ResponseEntity.ok("Registration Successfull Index Number: " + index);
		}
	}

//	TODO: register multiple student user
	@PostMapping("/register/student/batch/upload")
	public ResponseEntity<List<StudentBatchRequest>> registerBatchStudent(@RequestPart("file") MultipartFile file) {
		log.info("Registration of batch students called");
		List<StudentBatchRequest> users;
		if (helper.isExcel(file)) {
			try {
				users = helper.getAllUsers(file.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException("fail to store excel data: " + e.getMessage());
			}
		} else {
			users = null;
		}
		return ResponseEntity.ok(users);

	}
	
	@RequestMapping(value="/register/student/batch/check-header") 
	public ResponseEntity<List<String>> checkFileHeader(@RequestPart("file") MultipartFile file) {
		List<String> rowHeaders;
		try {
			 rowHeaders = helper.getFileHeading(file.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
				
		return ResponseEntity.ok(rowHeaders);
	}
	
	
	@PostMapping("/register/student/batch/saveAll")
	public ResponseEntity<String> saveAllUsers(@RequestBody List<StudentBatchRequest> students){
		int size = service.saveAll(students);
		return ResponseEntity.ok("No of Students registered: " + size);
	}

//	TODO: register FAC members user
	@PostMapping("/register/fac")
	public ResponseEntity<String> registerFAC(@RequestBody FACMember member) {
		log.info("Registration of FACMember called, Email:" + member.getEmail());
		String email = service.saveFacMember(member);
		return ResponseEntity.ok("Registration Completed with  Email: " + email);
	}

//	TODO: register Assistant registrater user
	@PostMapping("/register/ar")
	public ResponseEntity<String> registerAssistantReg(@RequestBody AssistentRegistrar ar) {
		log.info("Registration of Assistant Registrar called, Email:" + ar.getEmail());
		String email = service.saveAR(ar);
		return ResponseEntity.ok("Registration Completed with  Email: " + email);
	}

//	TODO: register ugs staff user
	@PostMapping("/register/ugs")
	public ResponseEntity<String> registerAssistantReg(@RequestBody UgsStaff ugs) {
		log.info("Registration of FACMember called, Email:" + ugs.getEmail());
		String email = service.saveUGS(ugs);
		return ResponseEntity.ok("Registration Completed with  Email: " + email);
	}
	
	
//	Get All FAC Members who are Academic Advisors
	@GetMapping("/academicAdvisors")
	public ResponseEntity<AcademicAdvisorListResponse> getAllAcademicAdvisor() {
		AcademicAdvisorListResponse academicAdvisors = new AcademicAdvisorListResponse();
		academicAdvisors.setAcademicAdvisors(service.getAllAcademicAdvisors());
		return ResponseEntity.ok(academicAdvisors);
	}
	
//	For Students TODO:add antMatcher as RoleStudent
	@GetMapping("/student/getUserInfo/{id}")
	public ResponseEntity<StudentInformationResponse> getStudentInformation(@PathVariable("id") Long id) {
		Student student = service.getStudent(id);
		StudentInformationResponse res  = new StudentInformationResponse();
		res.setFirstName(student.getFirstName());
		res.setLastName(student.getLastName());
		res.setNameToBeAppeared(student.getNameToBeAppeared());
		res.setContactNo(student.getContactNo());
		res.setBatchYear(student.getBatchYear());
		res.setCourseTitle(student.getCourseTitle());
		res.setEmail(student.getEmail());
		res.setIndexNo(student.getIndexNo());
		return ResponseEntity.ok(res);
	}
	
//	For Students TODO:add antMatcher as Role_FAC
	@GetMapping("/fac/getUserInfo/{id}")
	public ResponseEntity<FacInformationResponse> getFacInformation(@PathVariable("id") Long id) {
//		MRoles roleName;
//
//		switch(request.getRole()) {
//		case "ROLE_STUDENT":
//			roleName = MRoles.ROLE_STUDENT;
//			break;
//		case "ROLE_UGS":
//			roleName = MRoles.ROLE_UGS;
//			break;
//		case "ROLE_AR":
//			roleName = MRoles.ROLE_AR;
//			break;
//		case "ROLE_FAC_MEMBER":
//			roleName = MRoles.ROLE_FAC_MEMBER;
//			break;
//		default: 
//			roleName = null;
//		}
//		
//		Roles role = service.getRole(roleName);
		FACMember facMember = service.getFacMember(id);
		FacInformationResponse res  = new FacInformationResponse();
		res.setFirstName(facMember.getFirstName());
		res.setLastName(facMember.getLastName());
		res.setNameToBeAppeared(facMember.getNameToBeAppeared());
		res.setContactNo(facMember.getContactNo());
		res.setEmail(facMember.getEmail());
		return ResponseEntity.ok(res);
	}
	
//	For Students TODO:add antMatcher as AR
	@GetMapping("/ar/getUserInfo/{id}")
	public ResponseEntity<ArInformationResponse> getArInformation(@PathVariable("id") Long id) {
		service.getAr(id);
		return ResponseEntity.ok(null);
	}
//	For Students TODO:add antMatcher as UGS @Valid @RequestBody UserInformationRequest request
	@GetMapping("/ugs/getUserInfo/{id}")
	public ResponseEntity<UgsInformationResponse> getUgsInformation(@PathVariable("id") Long id) {
		UgsStaff ugs = service.getUgsStaff(id);
		UgsInformationResponse res  = new UgsInformationResponse();
		res.setFirstName(ugs.getFirstName());
		res.setLastName(ugs.getLastName());
		res.setNameToBeAppeared(ugs.getNameToBeAppeared());
		res.setContactNo(ugs.getContactNo());
		res.setEmail(ugs.getEmail());
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@Valid @RequestBody ChangePasswordRequest req) {
		int updatePassword = service.updatePassword(req);
		System.out.println(updatePassword);
		if (updatePassword != 0) {
			return ResponseEntity.ok("Password updated sucessfully");
		}
		else {
			return ResponseEntity.ok("Sorry Try Again: Old Password may be incorrect");
		}
	}
	
	
}
