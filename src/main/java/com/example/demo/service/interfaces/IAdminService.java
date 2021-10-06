package com.example.demo.service.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.request.AcadAdvisorBatchRequest;
import com.example.demo.dto.request.ChangePasswordRequest;
import com.example.demo.dto.request.FACRequest;
import com.example.demo.dto.request.FacAcademicRequest;
import com.example.demo.dto.request.ModuleAdd;
import com.example.demo.dto.request.RegistrationRequestAcademic;
import com.example.demo.dto.request.StudentBatchRequest;
import com.example.demo.dto.request.StudentSingleRegister;
import com.example.demo.dto.response.StaffAdminResponse;
import com.example.demo.dto.response.StudentAdminRequestResponse;
import com.example.demo.dto.response.StudentInformationResponse;
import com.example.demo.entity.*;


public interface IAdminService {
	String saveStudent(StudentSingleRegister student);
//	TODO: List<Student> saveAll(file);
//	TODO: UGS STAFF || ADMIN 

	
	Optional<User> findUserByUserName(String email);

	String saveFacMember(FACRequest request);

	String saveAR(RegistrationRequestAcademic  request);

	String saveUGS(RegistrationRequestAcademic request);

	List<Object> saveAll(List<StudentBatchRequest> students);
	
	List<FACMember> getAllAcademicAdvisors();

		
	void changePassword(User user);
	
	Roles getRole(MRoles role);
	
	Student getStudent(long id);
	FACMember getFacMember(long id);
	AssistentRegistrar getAr(long id);
	UgsStaff getUgsStaff(long id);
	
//	Student
	List<String> getAllStudentByBatch(String batch);
	String setAcademicAdvisor(AcadAdvisorBatchRequest request);
	
	
//	Add Module
	String setModule(ModuleAdd request);
	String updateModule(ModuleAdd request);
	List<Module1> getAllModules();

	int updatePassword(ChangePasswordRequest req);

	//Add News Page
	//Add Semester Start date

	//FAC role Allocation
		
	String setFacRoleAcademicAdvisor(FacAcademicRequest request);

	StudentAdminRequestResponse searchUserInformation(String index);

	StaffAdminResponse getFacDetails(FacAcademicRequest request);


}

