package com.example.demo.dto.response;

import java.util.List;

import com.example.demo.entity.FACMember;

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

	public List<FACMember> getAcademicAdvisors() {
		return academicAdvisors;
	}

	public void setAcademicAdvisors(List<FACMember> academicAdvisors) {
		this.academicAdvisors = academicAdvisors;
	}
	
}
