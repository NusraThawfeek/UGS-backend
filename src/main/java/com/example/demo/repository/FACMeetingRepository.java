package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.FACMeeting;



public interface FACMeetingRepository extends JpaRepository<FACMeeting, Integer> {
	FACMeeting getById(Integer Id);

	List<FACMeeting> findAll();

	boolean existsById(Integer integer);

	@Query(value = "select * from fac_meeting fc where fc.id=?1", nativeQuery = true)
	FACMeeting getByMeetingIDS(Integer id);

	FACMeeting findFirstByOrderByIdDesc();
	
	@Query(value= "select * from fac_meeting fc where fc.date>CURDATE() and fc.minute_link1 is null", nativeQuery = true)
	FACMeeting findUpcomingMeeting();
	
	@Query(value= "select * from fac_meeting where date <= CURDATE() order by id desc", nativeQuery = true)
	List<FACMeeting> pastMeeting();
	
}
