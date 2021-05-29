package com.example.demo.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.request.ChangePasswordRequest;
import com.example.demo.dto.request.StudentBatchRequest;
import com.example.demo.dto.request.StudentSingleRegister;
import com.example.demo.entity.AssistentRegistrar;
import com.example.demo.entity.FACMember;
import com.example.demo.entity.MRoles;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Student;
import com.example.demo.entity.UgsStaff;
import com.example.demo.entity.User;


public interface IAdminService {
	String saveStudent(StudentSingleRegister student);
//	TODO: List<Student> saveAll(file);
//	TODO: UGS STAFF || ADMIN 
	
	////	Student getStudentDetail(Long id);	
//	Student deleteStudentDetails(Student student);
////	Student updateStudentDetails(Student student); //this will be implemented from the serviceImpl  class
//	Student getStudentDetail(String indexNumber);
	
	Optional<User> findUserByUserName(String email);

	String saveFacMember(FACMember member);

	String saveAR(AssistentRegistrar ar);

	String saveUGS(UgsStaff ugs);

	List<Object> saveAll(List<StudentBatchRequest> students);
	
	List<FACMember> getAllAcademicAdvisors();
//	TODO: add repo method to get the academic advisors
		
	void changePassword(User user);
	
	Roles getRole(MRoles role);
	
	Student getStudent(long id);
	FACMember getFacMember(long id);
	AssistentRegistrar getAr(long id);
	UgsStaff getUgsStaff(long id);


	int updatePassword(ChangePasswordRequest req);
}
