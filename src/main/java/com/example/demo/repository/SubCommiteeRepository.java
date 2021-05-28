package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.SubComittee;

@Repository
public interface SubCommiteeRepository extends JpaRepository<SubComittee, Long> {
	List<SubComittee> getByfacmeeting_id(Integer id);
	List<SubComittee> getByreportSubmittedFacMeeting_id(Integer id);
	
	@Query(value= "select * from subcommittees where report_submitted_fac_meeting_id is null and subcomitteefile_name is not null", nativeQuery = true)
	List<SubComittee> getSubcomiteeForAddMeeting();
}
