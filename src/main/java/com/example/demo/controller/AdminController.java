package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.request.AcadAdvisorBatchRequest;
import com.example.demo.dto.request.ChangePasswordRequest;
import com.example.demo.dto.request.FACRequest;
import com.example.demo.dto.request.FacAcademicRequest;
import com.example.demo.dto.request.ModuleAdd;
import com.example.demo.dto.request.RegistrationRequestAcademic;
import com.example.demo.dto.request.StudentBatchRequest;
import com.example.demo.dto.request.StudentSingleRegister;
import com.example.demo.dto.response.AcademicAdvisorListResponse;
import com.example.demo.dto.response.ArInformationResponse;
import com.example.demo.dto.response.FacInformationResponse;
import com.example.demo.dto.response.StaffAdminResponse;
import com.example.demo.dto.response.StudentAdminRequestResponse;
import com.example.demo.dto.response.StudentInformationResponse;
import com.example.demo.dto.response.UgsInformationResponse;
import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.Student;
import com.example.demo.entity.UgsStaff;
import com.example.demo.service.interfaces.IAdminService;
import com.example.demo.utils.ExcelHelper;

//Rest API start with  /admin/*
@RestController
@CrossOrigin("*")
//@RequestMapping("/admin")
public class AdminController {

	private static Logger log = Logger.getLogger(AdminController.class);

	@Autowired
	private ExcelHelper helper;

	@Autowired
	private IAdminService service;

//	Single Student Registration
	@PostMapping("/admin/register/student/single")
	public ResponseEntity<String> registerSingleStudent(@Valid @RequestBody StudentSingleRegister registerStudent) {
		log.info("Registration of Single Student called, Index:" + registerStudent.getIndexNumber());
		String index = service.saveStudent(registerStudent);
		if (index == null) {
			return ResponseEntity.badRequest().body(
					"User Account Already exists with email or index number\n Email : " + registerStudent.getEmail() + "\n Index Number:  "+registerStudent.getIndexNumber());
		} else {
			return ResponseEntity.ok("Registration Successfull Index Number: " + index);
		}
	}

//Multiple Student Regstration 

//	Check Header --> 
	@RequestMapping(value = "/admin/register/student/batch/check-header")
	public ResponseEntity<List<String>> checkFileHeader(@RequestPart("file") MultipartFile file) {
		List<String> rowHeaders;
		try {
			rowHeaders = helper.getFileHeading(file.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}

		return ResponseEntity.ok(rowHeaders);
	}

//	upload File --> 
	@PostMapping("/admin/register/student/batch/upload")
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

//	Register all students in the sent array by frontEnd
	@PostMapping("/admin/register/student/batch/saveAll")
	public ResponseEntity<List<Object>> saveAllUsers(@RequestBody List<StudentBatchRequest> students) {
		List<Object> response = service.saveAll(students);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/admin/register/fac")
	public ResponseEntity<String> registerFAC(@RequestBody FACRequest request) {
		log.info("Registration of FACMember called, Email:" + request.getEmail());
		String email = service.saveFacMember(request);
		if (email != null) {
			return ResponseEntity.ok("Registration Completed with  Email: " + email);
		} else {
			return ResponseEntity.badRequest()
					.body("Registration Failed with  Email: " + email + " .User Already exists");
		}
	}

	@PostMapping("/admin/register/ar")
	public ResponseEntity<String> registerAssistantReg(@RequestBody RegistrationRequestAcademic request) {
		log.info("Registration of Assistant Registrar called, Email:" + request.getEmail());
		String email = service.saveAR(request);
		if (email != null) {
			return ResponseEntity.ok("Registration Completed with  Email: " + email);
		} else {
			return ResponseEntity.badRequest()
					.body("Registration Failed with  Email: " + email + " .User Already exists");
		}
	}

	@PostMapping("/admin/register/ugs")
	public ResponseEntity<String> registerUgsStaff(@RequestBody RegistrationRequestAcademic request) {
		log.info("Registration of FACMember called, Email:" + request.getEmail());
		String email = service.saveUGS(request);
		if (email != null) {
			return ResponseEntity.ok("Registration Completed with  Email: " + email);
		} else {
			return ResponseEntity.badRequest()
					.body("Registration Failed with  Email: " + request.getEmail() + " .User Already exists");
		}
	}

//	Get All FAC Members who are Academic Advisors
	@GetMapping("/admin/academicAdvisors")
	public ResponseEntity<AcademicAdvisorListResponse> getAllAcademicAdvisor() {
		AcademicAdvisorListResponse academicAdvisors = new AcademicAdvisorListResponse();
		academicAdvisors.setAcademicAdvisors(service.getAllAcademicAdvisors());
		return ResponseEntity.ok(academicAdvisors);
	}

	@GetMapping("/student/getUserInfo/{id}")
	public ResponseEntity<StudentInformationResponse> getStudentInformation(@PathVariable("id") Long id) {
		Student student = service.getStudent(id);
		StudentInformationResponse res = new StudentInformationResponse();
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
		FACMember facMember = service.getFacMember(id);
		FacInformationResponse res = new FacInformationResponse();
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
		UgsInformationResponse res = new UgsInformationResponse();
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
		} else {
			return ResponseEntity.badRequest().body("Sorry Try Again: Old Password may be incorrect");
		}
	}

	@GetMapping("/admin/students/{batch}")
	public ResponseEntity<List<String>> getAllBatchStudents(@PathVariable("batch") String batch) {
		List<String> studentsIndexNumbers = service.getAllStudentByBatch(batch);
		return ResponseEntity.ok(studentsIndexNumbers);
	}

	@PostMapping("/admin/students/advisor/batch")
	public ResponseEntity<String> setAcademicAdvisor(@RequestBody AcadAdvisorBatchRequest request) {
		String index = service.setAcademicAdvisor(request);
		return ResponseEntity.ok("Academic Advisor set successfully for index " + index);
	}

	@PostMapping("/module/add")
	public ResponseEntity<String> addModule(@RequestBody ModuleAdd request) {
		String moduleCode = service.setModule(request);
		if (moduleCode != null) {
			return ResponseEntity.ok("Module Added Successfully with module code :" + moduleCode);
		} else {
			return ResponseEntity.badRequest().body("Module Already exists");

		}
	}

	@PostMapping("/fac/role/academic")
	public ResponseEntity<String> setAcademicAdvisorRole(@RequestBody FacAcademicRequest request) {
		String email = service.setFacRoleAcademicAdvisor(request);
		return ResponseEntity.ok("Role Added Successfully with for MR/MRS :" + email);
	}

	@GetMapping("/admin/student/{index}")
	public ResponseEntity<StudentAdminRequestResponse> getStudentDetails(@PathVariable("index") String index) {
		StudentAdminRequestResponse response = service.searchUserInformation(index);
		if (response == null) {
			return ResponseEntity.badRequest().body(response);
		} else {
			return ResponseEntity.ok(response);
		}
	}
	
	
	@PostMapping("/admin/fac")
	public ResponseEntity<StaffAdminResponse> getFacDetails(@RequestBody FacAcademicRequest request) {
		StaffAdminResponse response = service.getFacDetails(request);
		if (response == null) {
			return ResponseEntity.badRequest().body(response);
		} else {
			return ResponseEntity.ok(response);
		}
	}

}
