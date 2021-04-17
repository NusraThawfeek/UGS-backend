package com.example.demo.dto.response;

import java.util.List;

import com.example.demo.entity.FACMember;

import lombok.Data;
@Data
public class AcademicAdvisorListResponse {
	
	private List<FACMember> academicAdvisors;
	
	public void showAcademicAdvisors() {
		for(FACMember academicAdvisor:academicAdvisors) {
			System.out.println(
					"Name:\t"+academicAdvisor.getNameToBeAppeared() +
					"\nDepartment:\t"+academicAdvisor.getDepartment()+
					"\nE-mail:"+academicAdvisor.getEmail());
		}
		
	}
}
